package com.genome.dx.gmp.domain;


import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;
import org.springframework.ui.ModelMap;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
//@Table(name = "T_BRD")
public class Evidence extends ModelBase {
    @Id
    @Column(name = "CASEID")
    @JsonView({JsonViewFrontEnd.class})
    public Long caseId;

//    @Column(name = "GEN")
//    @JsonView({JsonViewFrontEnd.class})
//    @Enumerated(EnumType.STRING)
//    public GenderCd gen;

//    @Column(name = "AGE")
//    @JsonView({JsonViewFrontEnd.class})
//    public Long age;

    @Column(name = "SCORE")
    @JsonView({JsonViewFrontEnd.class})
    public Double score;

    @Column(name = "DIAGNOSIS")
    @JsonView({JsonViewFrontEnd.class})
    public String diagnosis;

    @Column(name = "OMIM")
    @JsonView({JsonViewFrontEnd.class})
    public Long omim;
}
