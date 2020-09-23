package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.PhenoTypeBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Subselect(PhenoTypeBase.SUB_SELECT)
@Entity
public class PhenoType extends PhenoTypeBase {
}
