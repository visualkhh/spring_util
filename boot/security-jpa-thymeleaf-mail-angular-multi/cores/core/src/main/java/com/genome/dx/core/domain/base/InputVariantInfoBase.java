package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.CrudTypeCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class InputVariantInfoBase extends ModelBase implements Serializable {

	@Id
	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="VARIANTINFO_ID")
	Long variantinfoId;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="INPUT_PID")
	Long inputPid;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="TIER_ID")
	Long tierId;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="VARIANT_ID")
	Long variantId;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="GENE")
	String gene;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="REFREAD")
	Long refread;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ALTREAD")
	Long altread;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="TOTALREAD")
	Long totalread;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="HET_HOM")
	String hetHom;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="EXTRA1")
	String extra1;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="EXTRA2")
	String extra2;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="EXTRA3")
	String extra3;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="EXTRA4")
	String extra4;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="PATHOGENICPROBABILITY")
	Double pathogenicProbability;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ISEXPECTEDDISEASECUSINGVARIANT")
	Long isExpectedDiseaseCusingVariant;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ISEDCVARIANTFROMSNU")
	Long isEDCVariantFromsnu;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ISEDCVARIANTFROMDDG2P")
	Long isEDCVariantFromddg2p;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ISEDCVARIANTFROMOMIM")
	Long isEDCVariantFromomim;

	@JsonView({JsonViewFrontEnd.class})
	@Column(name ="ISEDCVARIANTFROMCUSTOM")
	String isEDCVariantFromcustom;

}
