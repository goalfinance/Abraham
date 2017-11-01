package abraham.web.restcontroller.security.maintaining.beans;

import lombok.Data;

@Data
public class SecurityResourceBean {
    private String name;

    private String location;

    private Long groupSid;

    private String status;

    private String description;
}

