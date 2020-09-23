package com.genome.dx.core.domain.base;

import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public class UrlBase extends ModelBase implements Serializable {
    @Id
    @Column(name = "URL_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long urlSeq;

    @Column(name = "MENU_NM")
    private String menuNm;

    @Column(name = "MENU_NM_EN")
    private String menuNmEn;

    @Column(name = "I18N_CD")
    private String i18nCd;

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

    @Column(name = "URL_XPLN_EN")
    private String urlXplnEn;

    @Column(name = "PRNT_URL_SEQ")
    private String prntUrlSeq;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_CD")
    private UseCd useCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "HDDN_CD")
    private UseCd hddnCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "REGEXP_CD")
    private UseCd regexp_cd;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
