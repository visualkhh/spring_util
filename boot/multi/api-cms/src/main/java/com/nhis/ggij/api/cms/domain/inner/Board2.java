package com.nhis.ggij.api.cms.domain.inner;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Board2 {
	@Id
//	@GeneratedValue
	Long id;
	String title;
	String content;
	String pwd;
}