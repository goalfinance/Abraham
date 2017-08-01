package abraham.core.ca.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by panqingrong on 30/05/2017.
 */
@Entity
@Data
@Table(name="t_ca_kp_dsa_extinfo")
public class KeyPairDSAExtInfo {
    @Id
    @Column(name="kpdei_sid")
    private Long sid;

    @Column(name="kpdei_x")
    private String x;

    @Column(name="kpdei_y")
    private String y;

    @Column(name="kpdei_p")
    private String p;

    @Column(name="kpdei_q")
    private String q;

    @Column(name="kpdei_g")
    private String g;
}
