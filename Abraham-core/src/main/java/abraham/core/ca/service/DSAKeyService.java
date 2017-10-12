package abraham.core.ca.service;

import abraham.core.ca.domain.KeyExtInfo;
import abraham.core.ca.domain.KeyPairDSAExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.repository.DSAKeyExtInfoRepository;
import abraham.core.ca.repository.KeyPairDSAExtInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.BeanValidator;
import pan.utils.Encodes;

import java.security.KeyPair;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

@Component
public class DSAKeyService extends AbstractKeyService {
    @Autowired
    private DSAKeyExtInfoRepository dsaKeyExtInfoRepository;

    @Autowired
    private KeyPairDSAExtInfoRepository keyPairDSAExtInfoRepository;

    @Override
    public KeyExtInfo findKeyExtInfoBySid(long sid) {
        return dsaKeyExtInfoRepository.findById(Long.valueOf(sid)).orElse(null);
    }

    @Override
    @Transactional
    public void deleteKeyPair(long sid) {
        this.getKeyPairInfoRepository().deleteById(sid);
        this.keyPairDSAExtInfoRepository.deleteById(sid);
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
        KeyPair keyPair = this.getKeyPairUtils().newDSAKeyPair(request.getKeySize().getSizeValue());
        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();
        String x = Encodes.encodeHex(dsaPrivateKey.getX().toByteArray());
        String y = Encodes.encodeHex(dsaPublicKey.getY().toByteArray());
        String p = Encodes.encodeHex(dsaPrivateKey.getParams().getP().toByteArray());
        String q = Encodes.encodeHex(dsaPrivateKey.getParams().getQ().toByteArray());
        String g = Encodes.encodeHex(dsaPrivateKey.getParams().getG().toByteArray());

        KeyPairInfo keyPairInfo = new KeyPairInfo();
        keyPairInfo.setName(request.getKeyName());
        keyPairInfo.setSize(request.getKeySize().getSizeValue());
        keyPairInfo.setType(request.getKeyType().getName());
        keyPairInfo.setPassword(request.getKeyPassword());

        KeyPairDSAExtInfo keyPairDSAExtInfo = new KeyPairDSAExtInfo();
        keyPairDSAExtInfo.setSid(keyPairInfo.getSid());
        keyPairDSAExtInfo.setP(p);
        keyPairDSAExtInfo.setQ(q);
        keyPairDSAExtInfo.setG(g);
        keyPairDSAExtInfo.setX(x);
        keyPairDSAExtInfo.setY(y);

        this.saveKeyInfo(keyPairInfo, keyPairDSAExtInfo);
    }

    @Transactional
    public void saveKeyInfo(KeyPairInfo keyPairInfo, KeyPairDSAExtInfo keyPairDSAExtInfo){
        keyPairInfo = this.getKeyPairInfoRepository().save(keyPairInfo);
        keyPairDSAExtInfo.setSid(keyPairInfo.getSid());
        keyPairDSAExtInfoRepository.save(keyPairDSAExtInfo);
    }
}
