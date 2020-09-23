package com.genome.dx.core.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.InputInfo;
import com.genome.dx.core.domain.InputVariant;
import com.genome.dx.core.domain.InputVariantInfo;
import com.genome.dx.core.domain.base.InputVariantInfoBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class InputVariantInfoSameCount extends InputVariantInfo {

    @JsonView({JsonViewFrontEnd.class})
    Long sameCount;

}
