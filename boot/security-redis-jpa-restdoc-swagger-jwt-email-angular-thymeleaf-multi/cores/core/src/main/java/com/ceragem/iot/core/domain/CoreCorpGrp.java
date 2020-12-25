package com.ceragem.iot.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.domain.base.CorpGrpBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_CORP_GRP")
@NamedEntityGraph(name = "CorpGrp.corpGrpAuths", attributeNodes = @NamedAttributeNode("corpGrpAuths"))
public class CoreCorpGrp extends CorpGrpBase {

    @OneToMany
    @JoinColumn(name = "CORP_GRP_SEQ", referencedColumnName = "CORP_GRP_SEQ", insertable = false, updatable = false)
    @JsonView({JsonViewFrontEnd.class})
    private List<CorpGrpAuth> corpGrpAuths;
}
