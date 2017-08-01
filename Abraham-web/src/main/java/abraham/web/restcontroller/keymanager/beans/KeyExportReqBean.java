package abraham.web.restcontroller.keymanager.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by panqingrong on 01/11/2016.
 */
@Data
public class KeyExportReqBean implements Serializable {
    private String keypairName;
    private String keypairExportFormat;
}
