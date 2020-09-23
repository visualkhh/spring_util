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
public class OmimBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "OMIM_ID")
    @JsonView({JsonViewFrontEnd.class})
    Long omimId;

    @Column(name = "OMIM_NUMBER")
    @JsonView({JsonViewFrontEnd.class})
    String omimNumber;

    @Column(name = "DISEASE_NAME")
    @JsonView({JsonViewFrontEnd.class})
    String diseaseName;

    @Column(name = "GENE_SYMBOL")
    @JsonView({JsonViewFrontEnd.class})
    String geneSymbol;

    @Column(name = "INHERITANCE")
    @JsonView({JsonViewFrontEnd.class})
    String inheritance;

    @Column(name = "LOCATION")
    @JsonView({JsonViewFrontEnd.class})
    String location;

    @Column(name = "OMIM_MAPPING_KEY")
    @JsonView({JsonViewFrontEnd.class})
    String omimMappingKey;

    @Column(name = "PHENOTYPES")
    @JsonView({JsonViewFrontEnd.class})
    String phenotypes;
}
