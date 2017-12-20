package com.visualkhh.cms.domain;

import com.visualkhh.common.domain.AuthUrlBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="T_AUTH_URL")
public class AuthUrl extends AuthUrlBase{

	@ManyToOne
	@JoinColumn(name="urlSeq" , referencedColumnName  = "urlSeq",   insertable = false, updatable = false)
	Url url;
}
