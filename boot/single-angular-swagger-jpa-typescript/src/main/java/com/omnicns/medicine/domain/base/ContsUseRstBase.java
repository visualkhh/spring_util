package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper = false)
public class ContsUseRstBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "CONTS_USE_SEQ")
    private Integer contsUseSeq;

    @Column(name = "CPON_ID")
    protected String cponId;

    @Column(name = "SERIAL_NO")
    protected String serialNo;

    @Column(name = "OS_TYPE_CD")
    private String osTypeCd;

    @Column(name = "PKG_VER")
    private String pkgVer;

    @Column(name = "OS_VER")
    private String osVer;

    @Column(name = "AGE_CD")
    private String ageCd;

    @Column(name = "GEN_CD")
    private String genCd;

    @Column(name = "PLAYLIST_SEQ")
    private Integer playlistSeq;

    @Column(name = "MUSIC_CONTS_SEQ")
    private Integer musicContsSeq;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "ST_DT")
    private ZonedDateTime stDt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "END_DT")
    private ZonedDateTime endDt;

    @Column(name = "USE_TIME")
    private long useTime;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
