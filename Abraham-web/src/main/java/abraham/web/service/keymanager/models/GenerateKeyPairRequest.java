package abraham.web.service.keymanager.models;

import lombok.Data;
import pan.utils.ca.KeyPairSizeEnum;
import pan.utils.ca.KeyPairTypeEnum;

@Data
public class GenerateKeyPairRequest {
    private String keyName;
    private KeyPairTypeEnum keyType;
    private KeyPairSizeEnum keySize;
    private String keyPassword;
}
