package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.AuthBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_AUTH")
public class Auth extends AuthBase {

	@OneToMany
	@JoinColumn(name="authId" , referencedColumnName  = "authId",   insertable = false, updatable = false)
	List<AuthUrl> authUrls;
}
