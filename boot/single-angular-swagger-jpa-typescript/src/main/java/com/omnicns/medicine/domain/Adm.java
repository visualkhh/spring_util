package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.AdmBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="T_ADM")
public class Adm extends AdmBase implements Serializable{
//	@ManyToOne
//	@JoinColumn(name = "CORP_GRP_SEQ", referencedColumnName = "CORP_GRP_SEQ", insertable = false, updatable = false)
//	private CorpGrp corpGrp;
//
//	@OneToMany
//	@JoinColumn(name="ADM_SEQ" , referencedColumnName  = "ADM_SEQ",   insertable = false, updatable = false)
//	private List<AdmAuth> admAuths;
}
