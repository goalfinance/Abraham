package abraham.core.ca.service;

import lombok.Data;

/**
 * Created by panqingrong on 01/11/2016.
 */
@Data
public class KeyExportRequest {
    private Long keySid;
    private String keyFileName;
    private String keyExportFormat;
}
