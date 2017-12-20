package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AdmBase extends DomainBase implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer admSeq;

    @Column
    private String admLginId;

    @Column
    private String admLginPw;

    @Column
    private String admNm;

    @Column
    private String useYn;

    @Column
    private Integer lginFailCnt;

    @Column
    private Integer corpGrpSeq;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime LginWaitDt;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
