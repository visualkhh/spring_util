package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class EventBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer eventSeq;

    @Column
    private String titl;

    @Column
    private String titlEn;

    @Column
    private String cont;

    @Column
    private String contEn;

    @Column
    private String url;

    @Column
    private String useYn;

    @Column
    private Integer admSeq;

    @Column
    private ZonedDateTime stDt;

    @Column
    private ZonedDateTime endDt;

    @Column
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
