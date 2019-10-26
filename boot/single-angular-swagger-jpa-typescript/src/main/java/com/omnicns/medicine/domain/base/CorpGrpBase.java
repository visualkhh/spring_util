package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class CorpGrpBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "CORP_GRP_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer corpGrpSeq;

    @Column(name = "CORP_GRP_NM")
    private String corpGrpNm;

    @Column(name ="REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
