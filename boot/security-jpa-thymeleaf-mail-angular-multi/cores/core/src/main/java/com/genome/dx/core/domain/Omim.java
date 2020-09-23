package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.AdmAuthBase;
import com.genome.dx.core.domain.base.OmimBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="OMIM")
public class Omim extends OmimBase {
}
