package com.visualkhh.common.model.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.visualkhh.common.model.error.Error;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class FieldError<T> extends Error<T>  {
	String field;

}
