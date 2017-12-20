package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class TermsInfoBase extends DomainBase implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer termsSeq;

    @Column
    private String termsType;

    @Column
    private String termsDtlInfo;

    @Column
    private String termsDtlInfoEn;

    @Column
    private int admSeq;

    @Column
    private ZonedDateTime updDt;

    @Column
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
