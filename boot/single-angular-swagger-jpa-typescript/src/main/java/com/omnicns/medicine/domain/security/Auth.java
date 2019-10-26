package com.omnicns.medicine.domain.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity @Slf4j
@IdClass(Auth.AuthId.class)
@Subselect("" +
		" SELECT "+
		" X.ADM_SEQ, X.ADM_LGIN_ID, X.AUTH_ID, X.AUTH_NM, X.AUTH_URL_SEQ, X.CRUD_TYPE_CD, X.URL_SEQ, "+
		" U.MENU_NM,U.MENU_LVL,U.MENU_ICON,U.MENU_ORD,U.URL,U.URL_XPLN,U.USE_YN,U.HDDN_YN, U.PRNT_URL_SEQ "+
		" from   ( "+
		" 		select  A.ADM_SEQ, A.ADM_LGIN_ID, D.AUTH_ID, D.AUTH_NM, E.AUTH_URL_SEQ, E.CRUD_TYPE_CD, E.URL_SEQ "+
		" 		from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ "+
		" 				from    T_ADM "+
		" 		) A "+
		" 		, T_CORP_GRP                         B "+
		" 		, T_CORP_GRP_AUTH                    C "+
		" 		, T_AUTH                             D "+
		" 		, T_AUTH_URL                         E "+
		" 		where   1 = 1 "+
		" 		and     A.CORP_GRP_SEQ  =   B.CORP_GRP_SEQ "+
		" 		and     A.CORP_GRP_SEQ  =   C.CORP_GRP_SEQ "+
		" 		and     C.AUTH_ID       =   D.AUTH_ID "+
		" 		and     C.AUTH_ID       =   E.AUTH_ID "+
		" 		union "+
		" 		select  A.ADM_SEQ, A.ADM_LGIN_ID, C.AUTH_ID, C.AUTH_NM, D.AUTH_URL_SEQ, D.CRUD_TYPE_CD, D.URL_SEQ "+
		" 		from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ "+
		" 				from    T_ADM "+
		" 		) A "+
		" 		, T_ADM_AUTH                         B "+
		" 		, T_AUTH                             C "+
		" 		, T_AUTH_URL                         D "+
		" 		where   1 = 1 "+
		" 		and     A.ADM_SEQ       =   B.ADM_SEQ "+
		" 		and     B.AUTH_ID       =   C.AUTH_ID "+
		" 		and     C.AUTH_ID       =   D.AUTH_ID "+
		") X LEFT JOIN T_URL U ON X.URL_SEQ = U.URL_SEQ WHERE U.USE_YN='Y'  ORDER BY U.MENU_LVL, U.PRNT_URL_SEQ, U.MENU_ORD "+
		"")
@Data @Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Auth implements Serializable{

	@Getter @Setter @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
	@AllArgsConstructor
	public static class AuthId implements Serializable{
		private Integer admSeq;
		private String authId;
		private Integer authUrlSeq;
		private Integer urlSeq;
	}



	@Id
	@Column(name = "ADM_SEQ")
	Integer admSeq;
	@Id
	@Column(name = "AUTH_ID")
	String authId;
	@Id
	@Column(name = "AUTH_URL_SEQ")
	Integer authUrlSeq;
	@Id
	@Column(name = "URL_SEQ")
	Integer urlSeq;

	@Column(name = "ADM_LGIN_ID")
	String admLginId;
	@Column(name = "AUTH_NM")
	String authNm;
	@Column(name = "CRUD_TYPE_CD")
	String crudTypeCd;
	@Column(name = "MENU_NM")
	String menuNm;
	@Column(name = "PRNT_URL_SEQ")
	Integer prntUrlSeq;
	@Column(name = "MENU_LVL")
	Integer menuLvl;
	@Column(name = "MENU_ICON")
	String menuIcon;
	@Column(name = "MENU_ORD")
	Integer menuOrd;
	@Column(name = "URL")
	String url;
	@Column(name = "URL_XPLN")
	String urlXpln;
	@Column(name = "USE_YN")
	String useYn;
	@Column(name = "HDDN_YN")
	String hddnYn;
}
