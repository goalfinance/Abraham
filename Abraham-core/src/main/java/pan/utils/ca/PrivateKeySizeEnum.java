package pan.utils.ca;

/**
 * Created by panqingrong on 04/10/2016.
 */
public enum PrivateKeySizeEnum {
    SIZE_1024(1024), SIZE_2048(2048), SIZE_4096(4096);

    private int keySize;
    PrivateKeySizeEnum(int keySize) {
        this.keySize = keySize;
    }

    public int getSizeValue() {
        return keySize;
    }
}