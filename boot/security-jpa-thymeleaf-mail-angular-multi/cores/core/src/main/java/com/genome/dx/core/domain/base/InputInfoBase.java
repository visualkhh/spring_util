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
public class InputInfoBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "INPUT_PID")
    @JsonView({JsonViewFrontEnd.class})
    Long inputPid;

    @Column(name = "NAME")
    @JsonView({JsonViewFrontEnd.class})
    String name;

    @Column(name = "GENDER")
    @JsonView({JsonViewFrontEnd.class})
    String gender;

    @Column(name = "AGE")
    @JsonView({JsonViewFrontEnd.class})
    Long age;

    @Column(name = "CLINICALMODIFIER")
    @JsonView({JsonViewFrontEnd.class})
    String clinicalModifier;

    @Column(name = "ONSET")
    @JsonView({JsonViewFrontEnd.class})
    Long onset;

    @Column(name = "PHENOTYPE")
    @JsonView({JsonViewFrontEnd.class})
    String phenotype;

    @Column(name = "PTNT_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long ptntSeq;

}
