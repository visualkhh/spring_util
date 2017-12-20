package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class FitBrainBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "FIT_BRAIN_SEQ")
    private Integer fitBrainSeq;

    @Column(name = "APP_NM")
    private String appNm;

    @Column(name = "APP_NM_EN")
    private String appNmEn;

    @Column(name = "PKG_NM")
    private String pkgNm;

    @Column(name = "SMRY")
    private String smry;

    @Column(name = "SMRY_EN")
    private String smryEn;

    @Column(name = "CONT")
    private String cont;

    @Column(name = "CONT_EN")
    private String contEn;

    @Column(name = "PLAYSTORE")
    private String playstore;

    @Column(name = "APPSTORE")
    private String appstore;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @Column(name = "CORP_GRP_SEQ")
    private Integer corpGrpSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
