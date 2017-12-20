package com.visualkhh.common.model.error;

import com.visualkhh.common.code.Code;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class Error<T> extends Msg<T> {
	List<Error> errors;
	public Error(String code) {
		this(code, null);
	}
	public Error(String code, String message) {
		this(code, message, null, null);
	}

	public Error(String code, String message, List<Error> errors, T data) {
		super(code, message, data);
		this.errors = errors;
	}

	public Error(Code code, String message) {
		super(code, message);
	}
	public Error(Code code) {
		super(code);
	}
	public Error(Code code, String message, List<Error> errors) {
		super(code, message);
		this.errors = errors;
	}

	public void addError(Error e){
		if(null==errors){
			errors = new ArrayList<>();
		}
		errors.add(e);
	}
}
