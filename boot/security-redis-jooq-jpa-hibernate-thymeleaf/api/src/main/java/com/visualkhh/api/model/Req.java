package com.visualkhh.api.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class Req {
	@NotEmpty
	String name;
	@Email(message="{word.cancel}")
	String email;
	@Size(min=2, max=30)
	String phone;
	@Max(50)
	int age;
}
