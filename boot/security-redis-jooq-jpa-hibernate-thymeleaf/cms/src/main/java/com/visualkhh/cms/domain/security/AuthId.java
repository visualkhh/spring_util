package com.visualkhh.cms.domain.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @NoArgsConstructor @AllArgsConstructor
public class AuthId implements Serializable{
	Integer admSeq;
	String authId;
	Integer authUrlSeq;
	Integer urlSeq;

//	@Builder
//	public AuthId(Integer admSeq, Integer authId, Integer authUrlSeq, Integer urlSeq) {
//		this.admSeq = admSeq;
//		this.authId = authId;
//		this.authUrlSeq = authUrlSeq;
//		this.urlSeq = urlSeq;
//	}
}
