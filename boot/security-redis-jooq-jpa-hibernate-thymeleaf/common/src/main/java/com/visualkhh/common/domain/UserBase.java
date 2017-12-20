package com.visualkhh.common.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)  @NoArgsConstructor @AllArgsConstructor
public class UserBase extends DomainBase implements Serializable {
	@Id
	@Column(name = "USER_SEQ")
	@GeneratedValue(strategy= GenerationType.AUTO)
	protected Integer userSeq;

	@Column(name = "CPON_ID")
	protected String cponId;

	@Column(name = "USE_YN")
	protected String useYn;

	@Column(name = "LST_LGIN_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	protected ZonedDateTime lstLginDt;

	@Column(name = "REG_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	protected ZonedDateTime regDt;

	@PrePersist
	protected void onCreate() {
		if (regDt == null) { regDt = ZonedDateTime.now(); }
	}
}
