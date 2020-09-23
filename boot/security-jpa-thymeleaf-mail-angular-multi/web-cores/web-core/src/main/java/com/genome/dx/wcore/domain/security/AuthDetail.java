package com.genome.dx.wcore.domain.security;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.CrudTypeCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@IdClass(AuthDetail.AuthId.class)
@Subselect("" +
        " SELECT" +
        "    X.ADM_SEQ, X.ADM_LGIN_ID, X.AUTH_ID, X.AUTH_NM, X.AUTH_URL_SEQ, X.CRUD_TYPE_CD, X.URL_SEQ," +
        "    U.MENU_NM, U.I18N_CD, U.MENU_LVL, U.MENU_ICON, U.MENU_ORD, U.URL,U. URL_XPLN, U.USE_CD, U.HDDN_CD, U.REGEXP_CD, U.PRNT_URL_SEQ" +
        " from   (" +
        "           select  A.ADM_SEQ, A.ADM_LGIN_ID, D.AUTH_ID, D.AUTH_NM, E.AUTH_URL_SEQ, E.CRUD_TYPE_CD, E.URL_SEQ" +
        "           from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ" +
        "                   from    T_ADM" +
//		"               #                  where   ADM_LGIN_ID =   'omnifit' "+
        "           ) A" +
        "              , T_CORP_GRP                         B" +
        "              , T_CORP_GRP_AUTH                    C" +
        "              , T_AUTH                             D" +
        "              , T_AUTH_URL                         E" +
        "           where   1 = 1" +
        "             and     A.CORP_GRP_SEQ  =   B.CORP_GRP_SEQ" +
        "             and     A.CORP_GRP_SEQ  =   C.CORP_GRP_SEQ" +
        "             and     C.AUTH_ID       =   D.AUTH_ID" +
        "             and     C.AUTH_ID       =   E.AUTH_ID" +
        "           union" +
        "           select  A.ADM_SEQ, A.ADM_LGIN_ID, C.AUTH_ID, C.AUTH_NM, D.AUTH_URL_SEQ, D.CRUD_TYPE_CD, D.URL_SEQ" +
        "           from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ" +
        "                   from    T_ADM" +
//		"               #                  where   ADM_LGIN_ID =   'omnifit' "+
        "           ) A" +
        "              , T_ADM_AUTH                         B" +
        "              , T_AUTH                             C" +
        "              , T_AUTH_URL                         D" +
        "           where   1 = 1" +
        "             and     A.ADM_SEQ       =   B.ADM_SEQ" +
        "             and     B.AUTH_ID       =   C.AUTH_ID" +
        "             and     C.AUTH_ID       =   D.AUTH_ID" +
        "       ) X LEFT JOIN T_URL U ON X.URL_SEQ = U.URL_SEQ WHERE U.USE_CD='USE001'  ORDER BY U.MENU_LVL, U.PRNT_URL_SEQ, U.MENU_ORD" +
        "")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class AuthDetail extends ModelBase implements Serializable {

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthId implements Serializable {
        private Long admSeq;
        private String authId;
        private Long authUrlSeq;
        private Long urlSeq;
    }

    public AuthDetail() {
    }

    @Builder
    public AuthDetail(Long admSeq, String authId, Long authUrlSeq, Long urlSeq, String admLginId, String authNm, CrudTypeCd crudTypeCd, String menuNm, String i18nCd, Long prntUrlSeq, Long menuLvl, String menuIcon, Long menuOrd, String url, String urlXpln, UseCd useCd, UseCd hddnCd, UseCd regexpCd) {
        this.admSeq = admSeq;
        this.authId = authId;
        this.authUrlSeq = authUrlSeq;
        this.urlSeq = urlSeq;
        this.admLginId = admLginId;
        this.authNm = authNm;
        this.crudTypeCd = crudTypeCd;
        this.menuNm = menuNm;
        this.i18nCd = i18nCd;
        this.prntUrlSeq = prntUrlSeq;
        this.menuLvl = menuLvl;
        this.menuIcon = menuIcon;
        this.menuOrd = menuOrd;
        this.url = url;
        this.urlXpln = urlXpln;
        this.useCd = useCd;
        this.hddnCd = hddnCd;
        this.regexpCd = regexpCd;
    }

    @Id
    @Column(name = "ADM_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long admSeq;

    @Id
    @Column(name = "AUTH_ID")
    @JsonView({JsonViewFrontEnd.class})
    String authId;

    @Id
    @Column(name = "AUTH_URL_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long authUrlSeq;

    @Id
    @Column(name = "URL_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long urlSeq;

    @Column(name = "ADM_LGIN_ID")
    @JsonView({JsonViewFrontEnd.class})
    String admLginId;

    @Column(name = "AUTH_NM")
    @JsonView({JsonViewFrontEnd.class})
    String authNm;

    @Column(name = "CRUD_TYPE_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    CrudTypeCd crudTypeCd;

    @Column(name = "MENU_NM")
    @JsonView({JsonViewFrontEnd.class})
    String menuNm;

    @Column(name = "I18N_CD")
    @JsonView({JsonViewFrontEnd.class})
    String i18nCd;

    @Column(name = "PRNT_URL_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long prntUrlSeq;

    @Column(name = "MENU_LVL")
    @JsonView({JsonViewFrontEnd.class})
    Long menuLvl;

    @Column(name = "MENU_ICON")
    @JsonView({JsonViewFrontEnd.class})
    String menuIcon;

    @Column(name = "MENU_ORD")
    @JsonView({JsonViewFrontEnd.class})
    Long menuOrd;

    @Column(name = "URL")
    @JsonView({JsonViewFrontEnd.class})
    String url;

    @Column(name = "URL_XPLN")
    @JsonView({JsonViewFrontEnd.class})
    String urlXpln;

    @Enumerated(EnumType.STRING)
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "USE_CD")
    UseCd useCd;

    @Enumerated(EnumType.STRING)
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "HDDN_CD")
    UseCd hddnCd;

    @Enumerated(EnumType.STRING)
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "REGEXP_CD")
    UseCd regexpCd;

}
