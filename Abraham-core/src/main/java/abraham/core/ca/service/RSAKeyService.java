package abraham.core.ca.service;

import abraham.core.ca.domain.KeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.KeyPairRSAExtInfo;
import abraham.core.ca.repository.KeyPairRSAExtInfoRepository;
import abraham.core.ca.repository.RSAKeyExtInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.BeanValidator;
import pan.utils.Encodes;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RSAKeyService extends AbstractKeyService {
    @Autowired
    private KeyPairRSAExtInfoRepository keyPairRSAExtInfoRepository;
    @Autowired
    private RSAKeyExtInfoRepository rsaKeyExtInfoRepository;

    @Override
    public KeyExtInfo findKeyExtInfoBySid(long sid) {
        return rsaKeyExtInfoRepository.findOne(sid);
    }

    @Override
    @Transactional
    public void deleteKeyPair(long sid) {
        this.getKeyPairInfoRepository().delete(sid);
        keyPairRSAExtInfoRepository.delete(sid);
    }

    @Override
    public void generateKeyPair(KeyGenerateRequest request) throws AppBizException {
        if (request == null) {
            throw new IllegalArgumentException("The argument is not allowed to be null!");
        }

        if (this.getKeyPairUtils() == null) {
            throw new IllegalArgumentException("Object[KeyPairUtils] is null!");
        }

        BeanValidator.validate(request);

        if (null != this.getKeyPairInfoRepository().findByName(request.getKeyName())) {
            Object[] args = new Object[1];
            args[0] = request.getKeyName();
            throw new AppBizException(AppExceptionCodes.CA_KEYPAIR_NAME_EXIST[0],
                    AppExceptionCodes.CA_KEYPAIR_NAME_EXIST[1], args);
        }


        KeyPair keyPair = this.getKeyPairUtils().newRSAKeyPair(request.getKeySize().getSizeValue());
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyExponent = Encodes.encodeHex(rsaPublicKey.getPublicExponent().toByteArray());
        String publicKeyModulus = Encodes.encodeHex(rsaPublicKey.getModulus().toByteArray());
        String privateKeyExponent = Encodes.encodeHex(rsaPrivateKey.getPrivateExponent().toByteArray());
        String privateKeyModulus = Encodes.encodeHex(rsaPrivateKey.getModulus().toByteArray());

        KeyPairInfo keyPairInfo = new KeyPairInfo();
        keyPairInfo.setName(request.getKeyName());
        keyPairInfo.setType(request.getKeyType().getName());
        keyPairInfo.setSize(request.getKeySize().getSizeValue());
        keyPairInfo.setPassword(request.getKeyPassword());

        KeyPairRSAExtInfo keyPairRSAExtInfo = new KeyPairRSAExtInfo();

        keyPairRSAExtInfo.setPrivateKeyExponent(privateKeyExponent);
        keyPairRSAExtInfo.setPrivateKeyModulus(privateKeyModulus);
        keyPairRSAExtInfo.setPublicKeyExponent(publicKeyExponent);
        keyPairRSAExtInfo.setPublicKeyModulus(publicKeyModulus);

        saveKeyInfo(keyPairInfo, keyPairRSAExtInfo);
    }

    @Transactional
    public void saveKeyInfo(KeyPairInfo keyPairInfo, KeyPairRSAExtInfo keyPairRSAExtInfo){
        keyPairInfo = this.getKeyPairInfoRepository().save(keyPairInfo);
        keyPairRSAExtInfo.setSid(keyPairInfo.getSid());
        keyPairRSAExtInfoRepository.save(keyPairRSAExtInfo);
    }
}
