package com.visualkhh.cms.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnicns.web.spring.security.GrantedObjAuthority;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Slf4j
@Subselect("SELECT A.*, B.CORP_GRP_NM FROM T_ADM A LEFT JOIN T_CORP_GRP B ON A.CORP_GRP_SEQ = B.CORP_GRP_SEQ")
@NamedEntityGraph(name = "UserDetails.auths",attributeNodes = @NamedAttributeNode("auths"))
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

	@Id
	@Column
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer admSeq;

	@Column
	private String admLginId;

	@JsonIgnore
	@Column
	private String admLginPw;

	@Column
	private String admNm;

	@Column
	private String useYn;

	@Column
	private Integer lginFailCnt;

	@Column
	private Integer corpGrpSeq;

	@Column
	private String corpGrpNm;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime LginWaitDt;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime regDt;

	@PrePersist
	protected void onCreate() {
		if (regDt == null) { regDt = ZonedDateTime.now(); }
	}




//	@OneToMany(fetch = FetchType.EAGER)
	@OneToMany
	@JoinColumn(name="admSeq" , referencedColumnName  = "admSeq",   insertable = false, updatable = false)
	List<Auth> auths;

	@Override
	public Collection<? extends GrantedObjAuthority<List<Auth>>> getAuthorities() {

		Map<String, GrantedObjAuthority<List<Auth>>> contain = new HashMap<>();
		for (Auth auth: ListUtils.emptyIfNull(auths)) {
			GrantedObjAuthority<List<Auth>> auths = Optional.ofNullable(contain.get(auth.getAuthId())).orElseGet(()->{
				GrantedObjAuthority<List<Auth>> newAuths = new GrantedObjAuthority<List<Auth>>(auth.getAuthId(), new ArrayList<Auth>());
				contain.put(auth.getAuthId(), newAuths);
				return newAuths;
			});
			auths.getAuth().add(auth);
		}

		contain.put("ROLE_AUTH",        new GrantedObjAuthority<List<Auth>>("ROLE_AUTH",         null));
//		contain.put("ROLE_ANONYMOUS",   new GrantedObjAuthority<List<Auth>>("ROLE_ANONYMOUS",    null));

		return contain.values();
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return getAdmLginPw();
	}

	@Override
	public String getUsername() {
		return getAdmNm();
	}

	@Override
	public boolean isAccountNonExpired() {
//		getLginWaitDt().toEpochSecond(ZoneOffset.UTC);
//				LocalDateTime.ofInstant(offset).toEpochMillis()
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}