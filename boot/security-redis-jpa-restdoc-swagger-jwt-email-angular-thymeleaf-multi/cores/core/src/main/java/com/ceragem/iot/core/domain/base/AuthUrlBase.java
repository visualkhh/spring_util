package com.ceragem.iot.core.domain.base;

import com.ceragem.iot.core.code.CrudTypeCd;
import com.ceragem.iot.core.model.ModelBase;
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
public class AuthUrlBase extends ModelBase implements Serializable {

	@Id
	@Column(name = "AUTH_URL_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer authUrlSeq;

	@Column(name = "AUTH_ID")
	private String authId;

	@Column(name = "URL_SEQ")
	private Integer urlSeq;

	@Column(name = "CRUD_TYPE_CD")
	@Enumerated(EnumType.STRING)
	private CrudTypeCd crudTypeCd;

	@Column(name = "REG_DT")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime regDt;


	@PrePersist
	protected void onCreate() {
		if (regDt == null) {
			regDt = LocalDateTime.now();
		}
	}
}
