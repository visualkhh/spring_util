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
public class CustomBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "CUSTOM_ID")
    @JsonView({JsonViewFrontEnd.class})
    Long customId;

    @Column(name = "OMIM_NUMBER")
    @JsonView({JsonViewFrontEnd.class})
    Long omimNumber;

    @Column(name = "DISEASE_NAME")
    @JsonView({JsonViewFrontEnd.class})
    String diseaseName;

    @Column(name = "GENE_SYMBOL")
    @JsonView({JsonViewFrontEnd.class})
    String geneSymbol;

    @Column(name = "INHERITANCE")
    @JsonView({JsonViewFrontEnd.class})
    String inheritance;

    @Column(name = "PHENOTYPES_HPO_TERMS")
    @JsonView({JsonViewFrontEnd.class})
    String phenotypesHpoTerms;

    @Column(name = "PHENOTYPES_TEXT")
    @JsonView({JsonViewFrontEnd.class})
    String phenotypesText;
}
