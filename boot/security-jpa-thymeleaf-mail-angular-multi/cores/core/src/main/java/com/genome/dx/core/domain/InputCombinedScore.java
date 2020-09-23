package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.AuthUrlBase;
import com.genome.dx.core.domain.base.InputCombinedScoreBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="INPUT_CombinedScore")
public class InputCombinedScore extends InputCombinedScoreBase {

	@ManyToOne
	@JoinColumn(name="INPUT_PID" , referencedColumnName  = "INPUT_PID",   insertable = false, updatable = false)
	private InputInfo inputInfo;

	@ManyToOne
	@JoinColumn(name="COMPAREDEVIDENCE" , referencedColumnName  = "CUSTOM_ID",   insertable = false, updatable = false)
	private Custom custom;
}
