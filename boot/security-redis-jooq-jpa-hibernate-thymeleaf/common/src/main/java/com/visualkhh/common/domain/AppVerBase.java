package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AppVerBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer appVerSeq;

    @Column
    private String verInfo;

    @Column
    private String appUpdateAftrProgYn;

    @Column
    private String appDownUrl;

    @Column
    private String appVerStat;

    @Column
    private String useYn;

    @Column
    private Integer appSeq;

    @Column
    private Integer admSeq;

    @Column
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
