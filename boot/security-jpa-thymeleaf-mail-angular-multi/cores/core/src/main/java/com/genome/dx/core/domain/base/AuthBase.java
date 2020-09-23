package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AuthBase extends ModelBase implements Serializable {

    @Id
    @Column(name="AUTH_ID")
    @JsonView({JsonViewFrontEnd.class})
    protected String authId;

    @Column(name="AUTH_NM")
    @JsonView({JsonViewFrontEnd.class})
    protected String authNm;

    @Column(name="XPLN")
    @JsonView({JsonViewFrontEnd.class})
    protected String xpln;
}
