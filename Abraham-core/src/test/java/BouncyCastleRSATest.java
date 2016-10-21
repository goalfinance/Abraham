import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.*;
import java.security.spec.ECGenParameterSpec;

/**
 * Created by panqingrong on 01/10/2016.
 */
public class BouncyCastleRSATest {

    @Test
    public void testRSAKeyGenerator() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        byte[] pubModBytes = rsaPublicKey.getModulus().toByteArray();
        byte[] pubExpBytes = rsaPublicKey.getPublicExponent().toByteArray();

        byte[] priModBytes = rsaPrivateKey.getModulus().toByteArray();
        byte[] priExpBytes = rsaPrivateKey.getPrivateExponent().toByteArray();

        System.out.println("The size of public key's modulus is [" + pubModBytes.length + "]");
        System.out.println("The size of public key's exponent is [" + pubExpBytes.length + "]");
        System.out.println("The size of private key's modulus is [" + priModBytes.length + "]");
        System.out.println("The size of private key's exponent is [" + priExpBytes.length + "]");
        System.out.println("Security Provider:[" + keyPairGenerator.getProvider().getName() + "]");

    }

    @Test
    public void testDSAKeyGenerator() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        keyPairGenerator.initialize(4096, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();
        byte[] pubG = dsaPublicKey.getParams().getG().toByteArray();
        byte[] pubP = dsaPublicKey.getParams().getP().toByteArray();
        byte[] pubQ = dsaPublicKey.getParams().getQ().toByteArray();

        byte[] priG = dsaPrivateKey.getParams().getG().toByteArray();
        byte[] priP = dsaPrivateKey.getParams().getP().toByteArray();
        byte[] priQ = dsaPrivateKey.getParams().getQ().toByteArray();

        System.out.println("The size of public key'G is [" + pubG.length + "]");
        System.out.println("The size of public key'P is [" + pubP.length + "]");
        System.out.println("The size of public key'Q is [" + pubQ.length + "]");
        System.out.println("The size of private key'G is [" + priG.length + "]");
        System.out.println("The size of private key'P is [" + priP.length + "]");
        System.out.println("The size of private key'Q is [" + priQ.length + "]");

    }

    @Test
    public void testECKeyGenerator() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        ECGenParameterSpec param = new ECGenParameterSpec("prime256v1");  // I also used "P-256" here and it works on Windows
        keyPairGenerator.initialize(param, SecureRandom.getInstance("SHA1PRNG"));
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
        System.out.println(ecPublicKey.getAlgorithm());
        System.out.println(ecPublicKey.getFormat());
        System.out.println(ecPrivateKey.getAlgorithm());
        System.out.println(ecPrivateKey.getFormat());
    }
}
