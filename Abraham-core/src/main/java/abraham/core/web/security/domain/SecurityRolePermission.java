/**
 *
 */
package abraham.core.web.security.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author panqingrong
 */
@Entity
@Table(name = "t_security_role_permission")
@Data
public class SecurityRolePermission implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6691190042051402003L;

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_DISABLE = "D";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sId;

    @Column(name = "role_sid")
    private Long roleSid;

    @Column(name = "resource_sid")
    private Long resourceSid;

    @Column(name = "resource_group_sid")
    private Long resourceGroupSid;

    @Column(name = "permission_string")
    private String permission;

    @Column(name = "create_time")
    private Date createtime;

    @Column(name = "status")
    private String status;
}
