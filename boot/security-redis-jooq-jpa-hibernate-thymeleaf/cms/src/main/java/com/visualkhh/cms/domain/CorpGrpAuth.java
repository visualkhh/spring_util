package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.CorpGrpAuthBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_CORP_GRP_AUTH")
public class CorpGrpAuth extends CorpGrpAuthBase {
	@ManyToOne
	@JoinColumn(name="authId" , referencedColumnName  = "authId",   insertable = false, updatable = false)
	Auth auth;
}
