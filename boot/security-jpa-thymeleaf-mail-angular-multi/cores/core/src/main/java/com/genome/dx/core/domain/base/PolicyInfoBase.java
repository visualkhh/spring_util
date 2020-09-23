package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.PolicyCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class PolicyInfoBase extends ModelBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLCY_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long plcySeq;

    @Column(name = "PLCY_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    PolicyCd plcyCd;

    @Column(name = "PLCY_CONT")
    @JsonView({JsonViewFrontEnd.class})
    String plcyCont;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @Column(name = "UPD_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
