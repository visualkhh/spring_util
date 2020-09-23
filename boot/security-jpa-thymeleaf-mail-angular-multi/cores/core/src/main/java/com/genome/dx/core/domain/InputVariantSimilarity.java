package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.InputPhenotypeSimilarityBase;
import com.genome.dx.core.domain.base.InputVariantSimilarityBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
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
@Table(name="INPUT_Variant_similarity")
public class InputVariantSimilarity extends InputVariantSimilarityBase {

	@ManyToOne
	@JsonView({JsonViewFrontEnd.class})
	@JoinColumn(name="INPUT_PID" , referencedColumnName  = "INPUT_PID",   insertable = false, updatable = false)
	private InputInfo inputInfo;

	@ManyToOne
	@JsonView({JsonViewFrontEnd.class})
	@JoinColumn(name="COMPAREDEVIDENCE" , referencedColumnName  = "CUSTOM_ID",   insertable = false, updatable = false)
	private Custom custom;
}
