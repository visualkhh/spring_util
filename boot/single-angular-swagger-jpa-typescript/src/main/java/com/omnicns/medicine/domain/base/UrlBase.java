package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class UrlBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "URL_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer urlSeq;

    @Column(name = "MENU_NM")
    private String menuNm;

    @Column(name = "MENU_LVL")
    private Integer menuLvl;

    @Column(name = "MENU_ICON")
    private String menuIcon;

    @Column(name = "MENU_ORD")
    private Integer menuOrd;

    @Column(name = "URL")
    private String url;

    @Column(name = "URL_XPLN")
    private String urlXpln;

    @Column(name = "PRNT_URL_SEQ")
    private String prntUrlSeq;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "HDDN_YN")
    private String hddnYn;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
