package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.AdmBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @Entity //@Table(name="T_ADM")
public class Adm extends AdmBase implements Serializable{
	@ManyToOne
	@JoinColumn(name = "corpGrpSeq", referencedColumnName = "corpGrpSeq", insertable = false, updatable = false)
	CorpGrp corpGrp;

	@OneToMany
	@JoinColumn(name="admSeq" , referencedColumnName  = "admSeq",   insertable = false, updatable = false)
	List<AdmAuth> admAuths;
}