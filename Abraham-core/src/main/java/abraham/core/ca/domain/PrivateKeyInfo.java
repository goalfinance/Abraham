package abraham.core.ca.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Entity
@Data
@Table(name="t_ca_private_key")
public class PrivateKeyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="key_sid")
    private Long sid;

    @Column(name="key_name")
    private String name;

    @Column(name="key_type")
    private String type;

    @Column(name="key_size")
    private int size;

    @Column(name="key_password")
    private String password;

}
