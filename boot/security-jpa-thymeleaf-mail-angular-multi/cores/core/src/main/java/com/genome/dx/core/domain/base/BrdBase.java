package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import io.swagger.annotations.ApiModel;
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
public class BrdBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "BRD_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({JsonViewFrontEnd.class})
    private Long brdSeq;

    @Column(name = "CATE_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({JsonViewFrontEnd.class})
    private BrdCateCd cateCd;

    @Column(name = "TITL")
    @JsonView({JsonViewFrontEnd.class})
    private String titl;

    @Column(name = "CONT")
    @JsonView({JsonViewFrontEnd.class})
    private String cont;

    @Column(name = "PRNT_BRD_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private Long prntBrdSeq;

    @Column(name = "USE_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    private ZonedDateTime regDt;

    @Column(name = "ADM_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private Long admSeq;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
