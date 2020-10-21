package com.today.house.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.today.house.code.UseCd;
import com.today.house.model.ModelBase;
import com.today.house.model.view.json.JsonViewFrontEnd;
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
    @JsonView({JsonViewFrontEnd.class})
    private Long urlSeq;

    @Column(name = "MENU_NM")
    @JsonView({JsonViewFrontEnd.class})
    private String menuNm;

    @Column(name = "MENU_NM_EN")
    @JsonView({JsonViewFrontEnd.class})
    private String menuNmEn;

    @Column(name = "I18N_CD")
    @JsonView({JsonViewFrontEnd.class})
    private String i18nCd;

    @Column(name = "MENU_LVL")
    @JsonView({JsonViewFrontEnd.class})
    private Integer menuLvl;

    @Column(name = "MENU_ICON")
    @JsonView({JsonViewFrontEnd.class})
    private String menuIcon;

    @Column(name = "MENU_ORD")
    @JsonView({JsonViewFrontEnd.class})
    private Integer menuOrd;

    @Column(name = "URL")
    @JsonView({JsonViewFrontEnd.class})
    private String url;

    @Column(name = "URL_XPLN")
    @JsonView({JsonViewFrontEnd.class})
    private String urlXpln;

    @Column(name = "URL_XPLN_EN")
    @JsonView({JsonViewFrontEnd.class})
    private String urlXplnEn;

    @Column(name = "PRNT_URL_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private String prntUrlSeq;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_CD")
    @JsonView({JsonViewFrontEnd.class})
    private UseCd useCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "HDDN_CD")
    @JsonView({JsonViewFrontEnd.class})
    private UseCd hddnCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "REGEXP_CD")
    @JsonView({JsonViewFrontEnd.class})
    private UseCd regexp_cd;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
