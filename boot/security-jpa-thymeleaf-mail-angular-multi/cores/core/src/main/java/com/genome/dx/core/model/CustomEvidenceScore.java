package com.genome.dx.core.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.Custom;
import com.genome.dx.core.domain.InputVariantInfo;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CustomEvidenceScore extends Custom {

    @JsonView({JsonViewFrontEnd.class})
    Double score;

}
