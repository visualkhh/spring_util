package com.ceragem.iot.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.domain.base.AdmBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="T_ADM")
//@NamedEntityGraph(name = "Brd.brds", attributeNodes = @NamedAttributeNode("brds"))
public class CoreAdm extends AdmBase {

	@ManyToOne
	@JoinColumn(name = "CORP_GRP_SEQ", referencedColumnName = "CORP_GRP_SEQ", insertable = false, updatable = false)
	@JsonView({JsonViewFrontEnd.class})
	private CoreCorpGrp corpGrp;

	@OneToMany
	@JsonView({JsonViewFrontEnd.class})
	@JoinColumn(name="ADM_SEQ" , referencedColumnName  = "ADM_SEQ",   insertable = false, updatable = false)
	private List<CoreAdmAuth> admAuths;

}
