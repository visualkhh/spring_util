package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.PtntPntypeBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="T_PTNT_PNTYPE")
public class PtntPntype extends PtntPntypeBase {


//    @ManyToOne
//    @JoinColumn(name="PTNT_SEQ" , referencedColumnName  = "PTNT_SEQ", nullable = false, updatable = false, insertable = false)
//    PtntInfo ptntInfo;

    @Builder
    public PtntPntype(Long ptntSeq, Long termId, ZonedDateTime regDt) {
//        setPtntInfo(ptntInfo);
        setPtntSeq(ptntSeq);
        setTermId(termId);
        setRegDt(regDt);
    }

}
