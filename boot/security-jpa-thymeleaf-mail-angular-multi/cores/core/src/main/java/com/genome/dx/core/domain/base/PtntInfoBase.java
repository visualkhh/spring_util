package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class PtntInfoBase extends ModelBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PTNT_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long ptntSeq;

    @Column(name = "PTNT_NM")
    @JsonView({JsonViewFrontEnd.class})
    String ptntNm;

    @Column(name = "GEN")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    GenderCd gen;

    @Column(name = "AGE")
    @JsonView({JsonViewFrontEnd.class})
    Long age;

    @Column(name = "USE_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    UseCd useCd;

    @Column(name = "HPO_PROC_STAT_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    ProcStatCd hpoProcStatCd;

    @Column(name = "MRI_FILE_PATH")
    @JsonView({JsonViewFrontEnd.class})
    String mriFilePath;

    @Column(name = "MRI_PROC_STAT_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    ProcStatCd mriProcStatCd;

    @Column(name = "VCF_FILE_PATH")
    @JsonView({JsonViewFrontEnd.class})
    String vcfFilePath;

    @Column(name = "VCF_PROC_STAT_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    ProcStatCd vcfProcStatCd;

    @Column(name = "REG_ADM_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long regAdmSeq;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }
}
