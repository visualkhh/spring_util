package com.visualkhh.cms.domain.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Slf4j @IdClass(AuthId.class)
@Subselect("" +
		" select "+
		" ...."+
		" 		and     C.AUTH_ID       =   D.AUTH_ID "+
		") X LEFT JOIN T_URL U ON X.URL_SEQ = U.URL_SEQ WHERE U.USE_YN='Y' "+
		"")
public class Auth implements Serializable{
	@Id
	@Column
	Integer admSeq;
	@Id
	@Column
	String authId;
	@Id
	@Column
	Integer authUrlSeq;
	@Id
	@Column
	Integer urlSeq;

	@Column
	String admLginId;
	@Column
	String authNm;
	@Column
	String crudType;
	@Column
	String menuNm;
	@Column
	String menuNmEn;
	@Column
	String menuLvl;
	@Column
	String url;
	@Column
	String urlXpln;
	@Column
	String urlXplnEn;
	@Column
	String useYn;
	@Column
	String hddnYn;
}
