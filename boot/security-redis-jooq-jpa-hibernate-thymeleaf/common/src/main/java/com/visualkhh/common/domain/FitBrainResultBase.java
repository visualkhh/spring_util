package com.visualkhh.common.domain;

import com.visualkhh.common.validate.ValidateGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class FitBrainResultBase extends DomainBase implements Serializable {
	@Id
	@Column(name = "BRN_FIT_SEQ")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer brnFitSeq;

	@Column(name = "CPON_ID")
	@Max(value = 255, groups = ValidateGroup.CheckFitBrainResult.class)
	private String cponId;

	@Column(name = "SERIAL_NO")
	@Max(value = 255,groups = ValidateGroup.CheckFitBrainResult.class)
	private String serialNo;

	@Column(name = "AGE_CD")
	@Size(max = 6, min = 6, groups = ValidateGroup.CheckFitBrainResult.class)
	private String ageCd;

	@Column(name = "GEN_CD")
	private String genCd;

	@Column(name = "OS_TYPE")
	private String osType;

	@Column(name = "PKG_NM")
	private String pkgNm;

	@Column(name = "FIT_TYPE")
	private String fitType;

	@Column(name = "BWAV_TOT")
	private float bwavTot;

	@Column(name = "CENT")
	private float cent;

	@Column(name = "CENT_CD")
	private String centCd;

	@Column(name = "BRN_STRS")
	private float brnStrs;

	@Column(name = "BRN_STRS_CD")
	private String brnStrsCd;

	@Column(name = "BRN_ATIV_LVL")
	private float brnAtivLvl;

	@Column(name = "BRN_ATIV_LV_LCD")
	private String brnAtivLvlCd;

	@Column(name = "BIMB")
	private float bimb;

	@Column(name = "BIMB_CD")
	private String bimbCd;

	@Column(name = "ST_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime stDt;

	@Column(name = "END_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime endDt;

	@Column(name = "REG_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime regDt;

	@PrePersist
	protected void onCreate() {
		if (regDt == null) { regDt = ZonedDateTime.now(); }
	}

}
