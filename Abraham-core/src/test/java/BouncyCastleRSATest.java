import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.SecureRandom;
import java.security.interfaces.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by panqingrong on 01/10/2016.
 */
public class BouncyCastleRSATest {
    private static KeyPair RSAKeyPair;
    private static KeyPair DSAKeyPair;
    private static KeyPair ECKeyPair;

    @BeforeClass
    public static void setUpClass() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        keyPairGenerator.initialize(1024, new SecureRandom());
        RSAKeyPair = keyPairGenerator.genKeyPair();

        keyPairGenerator = KeyPairGenerator.getInstance("DSA",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        keyPairGenerator.initialize(1024, new SecureRandom());
        DSAKeyPair = keyPairGenerator.genKeyPair();

        keyPairGenerator = KeyPairGenerator.getInstance("EC",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        ECGenParameterSpec param = new ECGenParameterSpec("prime256v1");  // I also used "P-256" here and it works on Windows
        keyPairGenerator.initialize(param, SecureRandom.getInstance("SHA1PRNG"));
        KeyPair ECKeyPair = keyPairGenerator.genKeyPair();


    }

    private RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance("RSA",
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));

        return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

    }

    private RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());

        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));

        return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
    }

    private DSAPublicKey generateDSAPublicKey(byte[] y, byte[] p, byte[] q, byte[] g) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        DSAPublicKeySpec publicKeySpec = new DSAPublicKeySpec(new BigInteger(y), new BigInteger(p), new BigInteger(q), new BigInteger(g));

        return (DSAPublicKey) keyFactory.generatePublic(publicKeySpec);
    }

    private DSAPrivateKey generateDSAPrivateKey(byte[] x, byte[] p, byte[] q, byte[] g) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        DSAPrivateKeySpec privateKeySpec = new DSAPrivateKeySpec(new BigInteger(x), new BigInteger(p), new BigInteger(q), new BigInteger(g));

        return (DSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
    }
    

    private byte[] encrypt(String s, PublicKey key, byte[] data) throws Exception{
        if (s == null || s.equals("")){
            s = "RSA";
        }
        Cipher cipher = Cipher.getInstance(s,
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
        // 加密块大小为127
        // byte,加密后为128个byte;因此共有2个加密块，第一个127
        // byte第二个为1个byte
        int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
        int leavedSize = data.length % blockSize;
        int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length
                / blockSize;
        byte[] raw = new byte[outputSize * blocksSize];
        int i = 0;
        while (data.length - i * blockSize > 0) {
            if (data.length - i * blockSize > blockSize)
                cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
            else
                cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i
                        * outputSize);
            // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
            i++;
        }
        return raw;
    }

    private byte[] decrypt(String s, PrivateKey privateKey, byte[] raw) throws Exception{
        if (s == null || s.equals("")){
            s = "RSA";
        }
        Cipher cipher = Cipher.getInstance(s,
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(cipher.DECRYPT_MODE, privateKey);
        int blockSize = cipher.getBlockSize();
        ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
        int j = 0;
        while (raw.length - j * blockSize > 0) {
            bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            j++;
        }
        return bout.toByteArray();
    }

    private byte[] sign(String origData, PrivateKey privateKey) throws Exception{
        Signature signature = Signature.getInstance("DSA");
        signature.initSign(privateKey);
        signature.update(origData.getBytes());

        return signature.sign();
    }

    private boolean verify(String origData, PublicKey publicKey, byte[] sign) throws Exception{
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(publicKey);
        signature.update(origData.getBytes());
        return signature.verify(sign);
    }

    @Test
    public void testRSAKeyGenerator() throws Exception {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) RSAKeyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) RSAKeyPair.getPrivate();
        byte[] pubModBytes = rsaPublicKey.getModulus().toByteArray();
        byte[] pubExpBytes = rsaPublicKey.getPublicExponent().toByteArray();

        byte[] priModBytes = rsaPrivateKey.getModulus().toByteArray();
        byte[] priExpBytes = rsaPrivateKey.getPrivateExponent().toByteArray();

        System.out.println("The size of public key's modulus is [" + pubModBytes.length + "]");
        System.out.println("The size of public key's exponent is [" + pubExpBytes.length + "]");
        System.out.println("The size of private key's modulus is [" + priModBytes.length + "]");
        System.out.println("The size of private key's exponent is [" + priExpBytes.length + "]");
    }

    @Test
    public void testRebuildingRSAKey() throws Exception{
        String origData = "If I win, I will praise Him, if I lose, I will still praise Him.";
        RSAPublicKey rsaPublicKey = (RSAPublicKey) RSAKeyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) RSAKeyPair.getPrivate();

        byte[] priModBytes = rsaPrivateKey.getModulus().toByteArray();
        byte[] priExpBytes = rsaPrivateKey.getPrivateExponent().toByteArray();
        RSAPrivateKey rebuildedPrivateKey = this.generateRSAPrivateKey(priModBytes,priExpBytes);

        byte[] pubModBytes = rsaPublicKey.getModulus().toByteArray();
        byte[] pubExpBytes = rsaPublicKey.getPublicExponent().toByteArray();
        RSAPublicKey rebuiltPublicKey = this.generateRSAPublicKey(pubModBytes, pubExpBytes);

        //Encrypt
        byte[] encryptedByOriginalPubKey = this.encrypt("RSA", rsaPublicKey, origData.getBytes());
        byte[] encryptedByRebuildingPubKey = this.encrypt("RSA", rebuiltPublicKey, origData.getBytes());

//        assertThat(encryptedByOriginalPubKey.length, equalTo(encryptedByRebuildingPubKey.length));
//        int i = 0;
//        for(byte b:encryptedByRebuildingPubKey){
//            assertThat(encryptedByOriginalPubKey[i], equalTo(b));
//        }

        byte[] decryptedByOriginalPriKey = this.decrypt("RSA", rsaPrivateKey, encryptedByRebuildingPubKey);
        String decryptedData = new String(decryptedByOriginalPriKey);
        assertThat(origData.length(), equalTo(decryptedData.length()));
        assertThat(origData, equalTo(decryptedData));
    }

    @Test
    public void testRebuildingDSAKey() throws Exception{
        String origData = "If I win, I will praise Him, if I lose, I will still praise Him.";
        DSAPublicKey dsaPublicKey = (DSAPublicKey) DSAKeyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) DSAKeyPair.getPrivate();

        byte[] y = dsaPublicKey.getY().toByteArray();
        byte[] p = dsaPublicKey.getParams().getP().toByteArray();
        byte[] q = dsaPublicKey.getParams().getQ().toByteArray();
        byte[] g = dsaPublicKey.getParams().getG().toByteArray();

        byte[] x = dsaPrivateKey.getX().toByteArray();
        byte[] pPriKey = dsaPrivateKey.getParams().getP().toByteArray();
        byte[] qPriKey = dsaPrivateKey.getParams().getQ().toByteArray();
        byte[] gPriKey = dsaPrivateKey.getParams().getG().toByteArray();

        System.out.println(x.length);
        System.out.println(y.length);
        System.out.println(p.length);
        System.out.println(q.length);
        System.out.println(g.length);

        assertThat(p, equalTo(pPriKey));
        assertThat(q, equalTo(qPriKey));
        assertThat(g, equalTo(gPriKey));

        DSAPublicKey rebuiltPublicKey = this.generateDSAPublicKey(y, p, q, g);
        DSAPrivateKey rebuiltPrivateKey = this.generateDSAPrivateKey(x, pPriKey, qPriKey, gPriKey);

        byte[] signedByPriKey = this.sign(origData, dsaPrivateKey);
        byte[] signedByRebuiltPriKey = this.sign(origData, rebuiltPrivateKey);

        boolean result = this.verify(origData, rebuiltPublicKey, signedByPriKey);
        boolean resultByRebuiltPriKey = this.verify(origData, dsaPublicKey, signedByRebuiltPriKey);

        assertThat(true, equalTo(result));
        assertThat(true, equalTo(resultByRebuiltPriKey));

    }

    @Test
    public void testDSAKeyGenerator() throws Exception {
        DSAPublicKey dsaPublicKey = (DSAPublicKey) DSAKeyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) DSAKeyPair.getPrivate();
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
    public void testECKeyGenerator() throws Exception {
        ECPublicKey ecPublicKey = (ECPublicKey) ECKeyPair.getPublic();
        ECPrivateKey ecPrivateKey = (ECPrivateKey) ECKeyPair.getPrivate();
        System.out.println(ecPublicKey.getAlgorithm());
        System.out.println(ecPublicKey.getFormat());
        System.out.println(ecPrivateKey.getAlgorithm());
        System.out.println(ecPrivateKey.getFormat());
    }
}
