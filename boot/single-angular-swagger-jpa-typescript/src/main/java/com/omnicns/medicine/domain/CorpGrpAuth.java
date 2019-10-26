package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.CorpGrpAuthBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_CORP_GRP_AUTH")
public class CorpGrpAuth extends CorpGrpAuthBase {
	@ManyToOne
	@JoinColumn(name="AUTH_ID" , referencedColumnName  = "AUTH_ID",   insertable = false, updatable = false)
	private Auth auth;
}
