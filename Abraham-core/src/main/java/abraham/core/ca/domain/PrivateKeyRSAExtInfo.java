package abraham.core.ca.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Entity
@Data
@Table(name="t_ca_pk_rsa_extinfo")
public class PrivateKeyRSAExtInfo {
    @Id
    @Column(name="pkei_sid")
    private Long sid;

    @Column(name="pkei_public_exponent")
    private String publicKeyExponent;

    @Column(name="pkei_public_modulus")
    private String publicKeyModulus;

    @Column(name="pkei_private_exponent")
    private String privateKeyExponent;

    @Column(name="pkei_private_modulus")
    private String privateKeyModulus;
}
