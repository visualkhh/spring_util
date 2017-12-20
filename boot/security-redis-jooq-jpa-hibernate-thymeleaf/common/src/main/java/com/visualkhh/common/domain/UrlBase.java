package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class UrlBase extends DomainBase implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer urlSeq;

    @Column
    private String menuNm;

    @Column
    private String menuNmEn;

    @Column
    private Integer menuLvl;

    @Column
    private String url;

    @Column
    private String urlXpln;

    @Column
    private String urlXplnEn;

    @Column
    private String useYn;

    @Column
    private String hddnYn;

    @Column
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
