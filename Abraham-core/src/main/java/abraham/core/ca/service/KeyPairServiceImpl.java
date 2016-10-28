package abraham.core.ca.service;

import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.KeyPairRSAExtInfo;
import abraham.core.ca.domain.RSAKeyInfo;
import abraham.core.ca.repository.KeyPairInfoRepository;
import abraham.core.ca.repository.KeyPairRSAExtInfoRepository;
import abraham.core.ca.repository.RSAKeyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.BeanValidator;
import pan.utils.Encodes;
import pan.utils.ca.KeyPairTypeEnum;
import pan.utils.ca.KeyPairUtils;
import pan.utils.data.Order;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Component
public class KeyPairServiceImpl implements KeyPairService {
    @Autowired
    private KeyPairInfoRepository keyPairInfoRepository;
    @Autowired
    private KeyPairRSAExtInfoRepository keyPairRSAExtInfoRepository;
    @Autowired
    private RSAKeyInfoRepository rsaKeyInfoRepository;

    private KeyPairUtils keyPairUtils;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        keyPairUtils = new KeyPairUtils();
    }

    @Override
    public Page<KeyPairInfo> findAllKeyPairInfo(int pageNumber, int pageSize, List<Order> orders) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Order.newSort(orders));
        return keyPairInfoRepository.findAll(pageRequest);
    }

    @Override
    public RSAKeyInfo findKeyPairRSAInfoBySid(long sid) {
        return rsaKeyInfoRepository.findOne(sid);

    }

    @Override
    @Transactional
    public void deleteKeyPair(long sid) {
        keyPairInfoRepository.delete(sid);
        keyPairRSAExtInfoRepository.delete(sid);
    }

    @Override
    @Transactional
    public void saveKeyPairRSA(VOKeyPair voKeyPair) throws AppBizException{
        if (voKeyPair == null){
            throw new IllegalArgumentException("The argument is not allowed to be null!");
        }

        if (keyPairUtils == null){
            throw new IllegalArgumentException("Object[KeyPairUtils] is null!");
        }

        BeanValidator.validate(voKeyPair);

        if (null != keyPairInfoRepository.findByName(voKeyPair.getKeyName())){
            Object[] args = new Object[1];
            args[0] = voKeyPair.getKeyName();
            throw new AppBizException(AppExceptionCodes.CA_KEYPAIR_NAME_EXIST[0],
                    AppExceptionCodes.CA_KEYPAIR_NAME_EXIST[1], args);
        }

        KeyPair keyPair = keyPairUtils.newRSAKeyPair(voKeyPair.getKeySize().getSizeValue());
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyExponent = Encodes.encodeHex(rsaPublicKey.getPublicExponent().toByteArray());
        String publicKeyModulus = Encodes.encodeHex(rsaPublicKey.getModulus().toByteArray());
        String privateKeyExponent = Encodes.encodeHex(rsaPrivateKey.getPrivateExponent().toByteArray());
        String privateKeyModulus = Encodes.encodeHex(rsaPrivateKey.getModulus().toByteArray());

        KeyPairInfo keyPairInfo = new KeyPairInfo();
        keyPairInfo.setName(voKeyPair.getKeyName());
        keyPairInfo.setType(voKeyPair.getKeyType().getName());
        keyPairInfo.setSize(voKeyPair.getKeySize().getSizeValue());
        keyPairInfo.setPassword(voKeyPair.getKeyPassword());
        keyPairInfo = keyPairInfoRepository.save(keyPairInfo);


        KeyPairRSAExtInfo keyPairRSAExtInfo = new KeyPairRSAExtInfo();
        keyPairRSAExtInfo.setSid(keyPairInfo.getSid());
        keyPairRSAExtInfo.setPrivateKeyExponent(privateKeyExponent);
        keyPairRSAExtInfo.setPrivateKeyModulus(privateKeyModulus);
        keyPairRSAExtInfo.setPublicKeyExponent(publicKeyExponent);
        keyPairRSAExtInfo.setPublicKeyModulus(publicKeyModulus);
        keyPairRSAExtInfoRepository.save(keyPairRSAExtInfo);
    }
}
