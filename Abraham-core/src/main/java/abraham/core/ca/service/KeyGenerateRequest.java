package abraham.core.ca.service;

import lombok.Data;
import pan.utils.ca.KeyPairSizeEnum;
import pan.utils.ca.KeyPairTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Data
public class KeyGenerateRequest {
    @NotNull(message = "Key name is null!")
    private String keyName;

    @NotNull(message = "Key type is null!")
    private KeyPairTypeEnum keyType;

    @NotNull(message = "Key size is null!")
    private KeyPairSizeEnum keySize;

    private String keyPassword;
}
