/**
 *
 */
package abraham.core.web.security.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author panqingrong
 */
@Entity
@Table(name = "t_security_resource_group")
@Data
public class SecurityResourceGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1451217053541246534L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sid;

    @Column(name = "sort_idx")
    private int sortIdx = 0;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "desc")
    private String description;

}
