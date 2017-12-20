package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.CorpGrpBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="T_CORP_GRP")
public class CorpGrp extends CorpGrpBase{

	@OneToMany
	@JoinColumn(name="corpGrpSeq" , referencedColumnName  = "corpGrpSeq",   insertable = false, updatable = false)
	List<CorpGrpAuth> corpGrpAuths;
}
