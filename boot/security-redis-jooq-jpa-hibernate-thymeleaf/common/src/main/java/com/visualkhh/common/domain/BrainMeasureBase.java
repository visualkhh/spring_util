package com.visualkhh.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrainMeasureBase extends  DomainBase implements Serializable {
    @Id
    @Column(name = "BRN_MSMT_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int brnMsmtSeq;

    @Column(name = "USER_DVC_SEQ")
    private int userDvcSeq;

    @Column(name = "ST_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime stDt;

    @Column(name = "END_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDt;

    @Column(name = "CHAR_CD")
    private String charCd;

    @Column(name = "BWAV_TOT")
    private float bwavTot;

    @Column(name = "CENT")
    private float cent;

    @Column(name = "CENT_CD")
    private String centCd;

    @Column(name = "BRN_STRS")
    private float brnStrs;

    @Column(name = "BRN_STRS_CD")
    private String brnStrsCd;

    @Column(name = "BRN_ATIV_LVL")
    private float brnAtivLvl;

    @Column(name = "BRN_ATIV_LVL_CD")
    private String brnAtivLvlCd;

    @Column(name = "BIMB")
    private float bimb;

    @Column(name = "BIMB_CD")
    private String bimbCd;

    @Column(name = "AGE_CD")
    private String ageCd;

    @Column(name = "GEN_CD")
    private String genCd;

    @Column(name = "MSMT_TYPE")
    private String msmtType;

    @Column(name = "REG_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
