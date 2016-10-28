package abraham.web.restcontroller.keymanager.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by panqingrong on 26/10/2016.
 */
@Data
public class KeyPairBean implements Serializable{
    private String name;
    private String type;
    private String size;
}
