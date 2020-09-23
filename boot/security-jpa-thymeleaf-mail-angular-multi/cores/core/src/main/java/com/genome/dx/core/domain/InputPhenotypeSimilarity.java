package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.InputCombinedScoreBase;
import com.genome.dx.core.domain.base.InputPhenotypeSimilarityBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="INPUT_phenotype_similarity")
public class InputPhenotypeSimilarity extends InputPhenotypeSimilarityBase {

	@ManyToOne
	@JoinColumn(name="INPUT_PID" , referencedColumnName  = "INPUT_PID",   insertable = false, updatable = false)
	private InputInfo inputInfo;

	@ManyToOne
	@JoinColumn(name="COMPAREDEVIDENCE" , referencedColumnName  = "CUSTOM_ID",   insertable = false, updatable = false)
	private Custom custom;
}
