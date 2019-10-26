package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.AuthBase;
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
	@JoinColumn(name="AUTH_ID" , referencedColumnName  = "AUTH_ID",   insertable = false, updatable = false)
	private List<AuthUrl> authUrls;
}
