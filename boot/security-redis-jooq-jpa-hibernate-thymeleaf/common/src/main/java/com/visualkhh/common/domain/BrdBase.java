package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class BrdBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "BRD_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer brdSeq;

    @Column(name = "CATE_CD")
    private String cateCd;

    @Column(name = "TITL")
    private String titl;

    @Column(name = "TITL_EN")
    private String titlEn;

    @Column(name = "CONT")
    private String cont;

    @Column(name = "CONT_EN")
    private String contEn;

    @Column(name = "GRP_NO")
    private int grpNo;

    @Column(name = "GRP_ORD")
    private int grpOrd;

    @Column(name = "LVL")
    private int lvl;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @Column(name = "USER_DVC_SEQ")
    private Integer userDvcSeq;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
