package pan.utils.ca;

import org.bouncycastle.crypto.generators.DSAKeyPairGenerator;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by panqingrong on 26/10/2016.
 */
public class KeyPairUtils {
    private KeyPairGenerator rsaKeyPairGenerator;
    private KeyPairGenerator dsaKeyPairGenerator;
    private KeyPairGenerator ecKeyPairGenerator;

    public KeyPairUtils() throws NoSuchAlgorithmException{
        rsaKeyPairGenerator = KeyPairGenerator.getInstance(KeyPairTypeEnum.RSA.getName(),
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        dsaKeyPairGenerator = KeyPairGenerator.getInstance(KeyPairTypeEnum.DSA.getName(),
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        ecKeyPairGenerator = KeyPairGenerator.getInstance(KeyPairTypeEnum.EC.getName(),
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public KeyPair newRSAKeyPair(int keySize){
        rsaKeyPairGenerator.initialize(keySize, new SecureRandom());
        return rsaKeyPairGenerator.genKeyPair();
    }

    public KeyPair newDSAKeyPair(int keySize){
        dsaKeyPairGenerator.initialize(keySize, new SecureRandom());
        return dsaKeyPairGenerator.genKeyPair();
    }


}
