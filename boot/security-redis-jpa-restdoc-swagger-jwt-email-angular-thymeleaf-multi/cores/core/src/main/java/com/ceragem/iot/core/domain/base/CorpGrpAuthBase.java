package com.ceragem.iot.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.model.ModelBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public class CorpGrpAuthBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "CORP_GRP_AUTH_SEQ")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({JsonViewFrontEnd.class})
    private Long corpGrpAuthSeq;

    @Column(name = "CORP_GRP_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private Long corpGrpSeq;

    @Column(name = "AUTH_ID")
    @JsonView({JsonViewFrontEnd.class})
    private String authId;
}
