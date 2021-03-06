package abraham.core.ca.service;

import abraham.core.ca.domain.KeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.KeyPairRSAExtInfo;
import abraham.core.ca.repository.KeyPairRSAExtInfoRepository;
import abraham.core.ca.repository.RSAKeyExtInfoRepository;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.*;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class RSAKeyService extends AbstractKeyService {
    @Autowired
    private KeyPairRSAExtInfoRepository keyPairRSAExtInfoRepository;
    @Autowired
    private RSAKeyExtInfoRepository rsaKeyExtInfoRepository;
    @Autowired
    private RSAKeyService rsaKeyService;

    @Override
    public KeyExtInfo findKeyExtInfoBySid(long sid) {
        return rsaKeyExtInfoRepository.findById(Long.valueOf(sid)).orElse(null);
    }

    @Override
    @Transactional
    public void deleteKeyPair(long sid) {
        this.getKeyPairInfoRepository().deleteById(sid);
        keyPairRSAExtInfoRepository.deleteById(sid);
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

        if (this.getKeyPairInfoRepository().findByName(request.getKeyName()).isPresent()) {
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

        //When a method which is delared with 'transactional' is invoked by another one within the same class,
        // it is not enhanced by spring. So, it is need some special coding, these codes below is the best one
        // I can think out.
        rsaKeyService.saveKeyInfo(keyPairInfo, keyPairRSAExtInfo);
    }

    @Transactional
    public void saveKeyInfo(KeyPairInfo keyPairInfo, KeyPairRSAExtInfo keyPairRSAExtInfo) {
        keyPairInfo = this.getKeyPairInfoRepository().save(keyPairInfo);
        keyPairRSAExtInfo.setSid(keyPairInfo.getSid());
        keyPairRSAExtInfoRepository.save(keyPairRSAExtInfo);
    }

    @Override
    public void exportKey(KeyExportRequest req, OutputStream outputStream) throws AppBizException {
        KeyPairRSAExtInfo keyPairRSAExtInfo = keyPairRSAExtInfoRepository.findById(req.getKeySid()).orElseThrow(() -> {
            Object[] args = new Object[1];
            args[0] = req.getKeySid();
            return new AppBizException(AppExceptionCodes.CA_KEYPAIR_NOT_EXIST[0],
                    AppExceptionCodes.CA_KEYPAIR_NOT_EXIST[1], args);
        });

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] pubKeyModulus = Encodes.decodeHex(keyPairRSAExtInfo.getPublicKeyModulus());
            byte[] pubKeyExponent = Encodes.decodeHex(keyPairRSAExtInfo.getPublicKeyExponent());
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(pubKeyModulus), new BigInteger(pubKeyExponent));

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            PemWriter pemWriter = new PemWriter(writer);
            PemObject pemObject = new PemObject("PUBLIC KEY", rsaPublicKey.getEncoded());
            pemWriter.writeObject(pemObject);
            pemWriter.flush();
        } catch (Exception e) {
            throw new AppBizException(AppExceptionCodes.CA_KEY_EXPORT_ERROR[0],
                    AppExceptionCodes.CA_KEY_EXPORT_ERROR[1], e);
        }


    }
}
