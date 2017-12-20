package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class DvcInfoBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "DVC_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer dvcSeq;

    @Column(name = "SERIAL_NO")
    private String serialNo;

    @Column(name = "CORP_GRP_SEQ")
    private Integer corpGrpSeq;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
