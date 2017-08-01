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
@Table(name = "t_ca_kp_rsa_extinfo")
public class KeyPairRSAExtInfo {
    @Id
    @Column(name = "kpei_sid")
    private Long sid;

    @Column(name = "kpei_public_exponent")
    private String publicKeyExponent;

    @Column(name = "kpei_public_modulus")
    private String publicKeyModulus;

    @Column(name = "kpei_private_exponent")
    private String privateKeyExponent;

    @Column(name = "kpei_private_modulus")
    private String privateKeyModulus;
}
