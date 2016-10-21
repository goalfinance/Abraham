package pan.utils.ca;

/**
 * Created by panqingrong on 04/10/2016.
 */
public enum PrivateKeyTypeEnum {

    RSA("RSA"), DSA("DSA"), EC("EC");

    private String keyType;

    PrivateKeyTypeEnum(String keyType) {
        this.keyType = keyType;
    }

    public String getName() {
        return keyType;
    }
}
