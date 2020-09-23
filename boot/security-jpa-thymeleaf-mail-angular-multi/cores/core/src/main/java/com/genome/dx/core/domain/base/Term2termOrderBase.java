package com.genome.dx.core.domain.base;

import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class Term2termOrderBase extends ModelBase implements Serializable {

    @Column(name = "TERM1_ID")
    Long term1Id;

    @Column(name = "TERM2_ID")
    Long term2Id;

    @Column(name = "LVL")
    Long lvl;

    @Column(name = "TERM2_NM")
    String term2Nm;

    @Id
    @Column(name = "ORDKEY")
    String ordkey;

}
