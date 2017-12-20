package com.khh.api.resource.domain.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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