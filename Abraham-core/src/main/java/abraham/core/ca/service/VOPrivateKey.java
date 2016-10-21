package abraham.core.ca.service;

import lombok.Data;
import pan.utils.ca.PrivateKeySizeEnum;
import pan.utils.ca.PrivateKeyTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Data
public class VOPrivateKey {
    @NotNull(message = "Key name is null!")
    private String keyName;

    @NotNull(message = "Key type is null!")
    private PrivateKeyTypeEnum keyType;

    @NotNull(message = "Key size is null!")
    private PrivateKeySizeEnum keySize;

    private String keyPassword;
}
