package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.InputVariantInfoBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "INPUT_variant_info")
public class InputVariantInfo extends InputVariantInfoBase {

    @ManyToOne
    @JoinColumn(name="INPUT_PID" , referencedColumnName  = "INPUT_PID",   insertable = false, updatable = false)
    private InputInfo inputInfo;

    @OneToOne
    @JoinColumn(name = "VARIANT_ID", referencedColumnName = "VARIANT_ID", insertable = false, updatable = false)
    @JsonView({JsonViewFrontEnd.class})
    InputVariant inputVariant;


}
