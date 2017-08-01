/**
 *
 */
package abraham.core.web.security.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author panqingrong
 */
@Entity
@Table(name = "t_security_role")
@Data
public class SecurityRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2846969983297889088L;

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_DISABLE = "D";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "status")
    private String status = STATUS_ACTIVE;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

}
