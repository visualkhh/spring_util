//package com.genome.dx.gmp.domain;
//
//
//import com.fasterxml.jackson.annotation.JsonView;
//import com.genome.dx.core.model.ModelBase;
//import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
//import lombok.*;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = false)
//@Entity
////@Table(name = "T_BRD")
//public class Variant extends ModelBase {
//    @Id
//    @Column(name = "SEQ")
//    @JsonView({JsonViewFrontEnd.class})
//    public Long seq;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="TIERID")
//    String tierId;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="GENE")
//    String gene;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="DISEASE")
//    String disease;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="PHENOTYPEMIMNUMBER")
//    String phenotypeMIMnumber;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="INHERITANCE")
//    String inheritance;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="CHR")
//    String chr;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="START")
//    String start;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="END")
//    String end;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="REF")
//    String ref;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="ALT")
//    String alt;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="REFREAD")
//    String refread;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="ALTREAD")
//    String altread;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="TOTALREAD")
//    String totalread;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="HETHOM")
//    String hetHom;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="FUNCREFGENE")
//    String funcRefgene;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXONICFUNCREFGENE")
//    String exonicfuncRefgene;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="AACHANGEREFGENE")
//    String aachangeRefgene;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="ESP6500ALL")
//    String esp6500All;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACALL")
//    String exacAll;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACAFR")
//    String exacAfr;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACAMR")
//    String exacAmr;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACEAS")
//    String exacEas;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACFIN")
//    String exacFin;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACNFE")
//    String exacNfe;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACOTH")
//    String exacOth;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="EXACSAS")
//    String exacSas;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="G2015AUGALL")
//    String g2015augAll;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="ISEXPECTEDDISEASECUSINGVARIANT")
//    String isExpectedDiseaseCusingVariant;
//
//    @JsonView({JsonViewFrontEnd.class})
//    @Column(name="ISSAME")
//    String isSame;
//}
