package com.genome.dx.core.domain.base;

import com.genome.dx.core.model.ModelBase;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class PhenoTypeBase extends ModelBase {
    public static final String SUB_SELECT =  " select  term1_id as PARENT_TERMID, term2_id as TERMID, lvl as LVL from  T_TERM2TERM_ORDER" +
            " order by ordkey";
    @Id
    @Column(name = "TERMID")
    public Long termid;

    @Column(name = "PARENT_TERMID")
    public Long parentTermid;

    @Column(name = "LVL")
    public Long lvl;
}
