package abraham.core.ca.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by panqingrong on 04/10/2016.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "v_rsakey_info")
public class RSAKeyExtInfo extends KeyExtInfo {
    @Id
    @Column(name = "key_sid", insertable = false, updatable = false)
    private Long sid;

    @Column(name = "key_name", insertable = false, updatable = false)
    private String name;
    @Column(name = "key_type", insertable = false, updatable = false)
    private String type;
    @Column(name = "key_size", insertable = false, updatable = false)
    private int size;
    @Column(name = "key_password", insertable = false, updatable = false)
    private String password;

    @Column(name = "kpei_public_exponent", insertable = false, updatable = false)
    private String publicKeyExponent;

    @Column(name = "kpei_public_modulus", insertable = false, updatable = false)
    private String publicKeyModulus;

    @Column(name = "kpei_private_exponent", insertable = false, updatable = false)
    private String privateKeyExponent;

    @Column(name = "kpei_private_modulus", insertable = false, updatable = false)
    private String privateKeyModulus;
}
