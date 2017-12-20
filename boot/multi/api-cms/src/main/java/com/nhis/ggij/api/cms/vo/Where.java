package com.nhis.ggij.api.cms.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class Where {
	@NotEmpty
	String name;
	String date;
	int number;
}
