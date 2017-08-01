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
@Table(name = "t_security_user_permission")
@Data
public class SecurityUserPermission implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1871371868319462625L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sId;

    @Column(name = "user_sid")
    private Long userSid;

    @Column(name = "resource_sid")
    private Long resourceSid;

    @Column(name = "resource_group_sid")
    private Long resourceGroupSid;

    @Column(name = "permission_string")
    private String permission;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "status")
    private String status;
}
