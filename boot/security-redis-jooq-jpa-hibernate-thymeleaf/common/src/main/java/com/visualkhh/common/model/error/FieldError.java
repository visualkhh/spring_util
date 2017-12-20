package com.visualkhh.common.model.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class FieldError<T> extends com.visualkhh.common.model.error.Error<T> {
	String field;

}
