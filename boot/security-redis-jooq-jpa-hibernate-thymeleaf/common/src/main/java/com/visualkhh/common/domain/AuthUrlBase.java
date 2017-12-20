package com.visualkhh.common.domain;

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
public class AuthUrlBase extends DomainBase implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer authUrlSeq;

	@Column
	private String authId;

	@Column
	private Integer urlSeq;

	@Column
	private String crud_type;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime regDt;


	@PrePersist
	protected void onCreate() {
		if (regDt == null) {
			regDt = LocalDateTime.now();
		}
	}
}
