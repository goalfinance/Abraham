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
@Table(name = "t_security_resource")
@Data
public class SecurityResource implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8985743883164561587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sid;

    @Column(name = "sort_index")
    private int sortIdx = 0;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "group_sid")
    private Long groupSid;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

}
