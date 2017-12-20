package com.nhis.ggij.api.cms.domain.primary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Board {
	@Id
//	@GeneratedValue
	Long id;
	String title;
	String content;
	String pwd;
}