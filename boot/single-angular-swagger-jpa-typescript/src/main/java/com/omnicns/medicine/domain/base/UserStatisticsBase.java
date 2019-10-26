package com.omnicns.medicine.domain.base;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)  @NoArgsConstructor
public class UserStatisticsBase extends DomainBase implements Serializable {
	@Id
	@Column(name = "USER_STT_SEQ")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer userSttSeq;

	@Column(name = "TOT")
	private Integer tot;

	@Column(name = "NEW_TOT")
	private Integer newTot;

	@Column(name = "M_TOT")
	private Integer mTot;

	@Column(name = "F_TOT")
	private Integer fTot;

	@Column(name = "AGC001")
	private Integer agc001;

	@Column(name = "AGC002")
	private Integer agc002;

	@Column(name = "AGC003")
	private Integer agc003;

	@Column(name = "AGC004")
	private Integer agc004;

	@Column(name = "AGC005")
	private Integer agc005;

	@Column(name = "AGC006")
	private Integer agc006;

	@Column(name = "AGC007")
	private Integer agc007;

	@Column(name = "AGC008")
	private Integer agc008;

	@Column(name = "AGC009")
	private Integer agc009;

	@Column(name = "AGC010")
	private Integer agc010;

	@Column(name = "AGC011")
	private Integer agc011;

	@Column(name = "AGC012")
	private Integer agc012;

	@Column(name = "AGC013")
	private Integer agc013;

	@Column(name = "AGC014")
	private Integer agc014;

	@Column(name = "AGC015")
	private Integer agc015;

	@Column(name = "AGC016")
	private Integer agc016;

	@Column(name = "AGC017")
	private Integer agc017;

	@Column(name = "AGC018")
	private Integer agc018;

	@Column(name = "AGC019")
	private Integer agc019;

	@Column(name = "AGC020")
	private Integer agc020;

	@Column(name = "AGC021")
	private Integer agc021;

	@Column(name = "AGC022")
	private Integer agc022;

	@Column(name = "AGC023")
	private Integer agc023;

	@Column(name = "AGC024")
	private Integer agc024;

	@Column(name = "AGC025")
	private Integer agc025;

	@Column(name = "AGC026")
	private Integer agc026;

	@Column(name = "AGC027")
	private Integer agc027;

	@Column(name = "AGC028")
	private Integer agc028;

	@Column(name = "AGC029")
	private Integer agc029;

	@Column(name = "AGC030")
	private Integer agc030;

	@Column(name = "AGC031")
	private Integer agc031;

	@Column(name = "AGC032")
	private Integer agc032;

	@Column(name = "AGC033")
	private Integer agc033;

	@Column(name = "WEEKLY")
	private Integer weekly;

	@Column(name = "MONTHLY")
	private Integer monthly;

	@Column(name = "REG_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime regDt;

	@PrePersist
	private void onCreate() {
		if (regDt == null) { regDt = ZonedDateTime.now(); }
	}
}
