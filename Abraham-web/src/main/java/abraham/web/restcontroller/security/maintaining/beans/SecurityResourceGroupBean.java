package abraham.web.restcontroller.security.maintaining.beans;

import lombok.Data;

import javax.persistence.Column;

@Data
public class SecurityResourceGroupBean {
    private String name;
    private String description;
}
