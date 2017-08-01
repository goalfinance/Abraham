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
@Table(name="v_dsakey_info")
public class DSAKeyExtInfo extends KeyExtInfo {
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

    @Column(name="kpdei_x", insertable = false, updatable = false)
    private String x;

    @Column(name="kpdei_y", insertable = false, updatable = false)
    private String y;

    @Column(name="kpdei_p", insertable = false, updatable = false)
    private String p;

    @Column(name="kpdei_q", insertable = false, updatable = false)
    private String q;

    @Column(name="kpdei_g", insertable = false, updatable = false)
    private String g;
}

