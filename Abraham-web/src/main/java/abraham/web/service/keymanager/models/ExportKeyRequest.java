package abraham.web.service.keymanager.models;

import lombok.Data;

@Data
public class ExportKeyRequest {
    private Long keySid;
    private String keyFileName;
    private String keyExportFormat;
}
