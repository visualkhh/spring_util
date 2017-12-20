package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AppInfoBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer appSeq;

    @Column
    private String osType;

    @Column
    private String appXpln;

    @Column
    private String appNm;

    @Column
    private String pkgNm;

    @Column
    private String useYn;

    @Column
    private Integer admSeq;

    @Column
    private ZonedDateTime regDt;
}
