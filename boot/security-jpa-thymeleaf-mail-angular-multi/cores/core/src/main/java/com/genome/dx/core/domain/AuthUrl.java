package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.AuthUrlBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="T_AUTH_URL")
public class AuthUrl extends AuthUrlBase {

	@ManyToOne
	@JoinColumn(name="URL_SEQ" , referencedColumnName  = "URL_SEQ",   insertable = false, updatable = false)
	@OrderBy("MENU_ORD ASC")
	private Url url;
}
