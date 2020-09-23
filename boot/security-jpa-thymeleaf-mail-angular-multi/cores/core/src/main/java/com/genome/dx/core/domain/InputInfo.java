package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.InputInfoBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "INPUT_info")
public class InputInfo extends InputInfoBase {

    @OneToMany
    @JoinColumn(name = "INPUT_PID", referencedColumnName = "INPUT_PID", insertable = false, updatable = false)
    private List<InputVariantInfo> variantInfos;

}
