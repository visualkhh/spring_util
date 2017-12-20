package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class MusicContsBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "MUSIC_CONTS_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer musicContsSeq;

    @Column(name = "MUSIC_NM")
    private String musicNm;

    @Column(name = "MUSIC_NM_EN")
    private String musicNmEn;

    @Column(name = "MUSIC_ORD")
    private int musicOrd;

    @Column(name = "XPLN")
    private String xpln;

    @Column(name = "XPLN_EN")
    private String xplnEn;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
