package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class PlaylistBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "PLAYLIST_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer playlistSeq;

    @Column(name = "TITL")
    private String titl;

    @Column(name = "TITL_EN")
    private String titlEn;

    @Column(name = "XPLN")
    private String xpln;

    @Column(name = "XPLN_EN")
    private String xplnEn;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "USE_TYPE")
    private String useType;

    @Column(name = "PLAYLIST_ORD")
    private int playlistOrd;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
