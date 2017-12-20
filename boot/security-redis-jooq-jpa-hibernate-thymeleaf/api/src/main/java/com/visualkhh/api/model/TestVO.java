package com.visualkhh.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@AllArgsConstructor @NoArgsConstructor
public class TestVO {
	@NotEmpty
	public String name;
	@NotEmpty
	public Integer age;
}
