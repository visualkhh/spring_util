package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.AdmAuthBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="T_ADM_AUTH")
public class AdmAuth extends AdmAuthBase{
	@ManyToOne
	@JoinColumn(name="authId" , referencedColumnName  = "authId",   insertable = false, updatable = false)
	Auth auth;
}
