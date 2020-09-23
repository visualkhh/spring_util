package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.AdmBase;
import com.genome.dx.core.domain.base.PtntInfoBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="T_PTNT_INFO")
public class PtntInfo extends PtntInfoBase {

//    @OneToMany(mappedBy = "ptntInfo")
    @OneToMany
    @JoinColumn(name="PTNT_SEQ" , referencedColumnName  = "PTNT_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private List<PtntPntype> ptntPntypes;

}
