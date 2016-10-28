package pan.utils.ca;

/**
 * Created by panqingrong on 04/10/2016.
 */
public enum KeyPairTypeEnum {

    RSA("RSA"), DSA("DSA"), EC("EC");

    private String keyType;

    KeyPairTypeEnum(String keyType) {
        this.keyType = keyType;
    }

    public String getName() {
        return keyType;
    }
}
