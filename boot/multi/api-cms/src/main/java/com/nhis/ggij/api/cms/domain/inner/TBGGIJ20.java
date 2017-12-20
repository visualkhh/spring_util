package com.nhis.ggij.api.cms.domain.inner;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TBGGIJ20")
public class TBGGIJ20 {
	@Id
	@Column(name="USER_NUM")
	String USER_NUM;
	@Column(name="USER_NM")
	String USER_NM;
}