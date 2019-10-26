package com.omnicns.medicine.model.error;

import com.omnicns.medicine.code.MsgCode;
import com.omnicns.medicine.model.msg.Msg;
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

	public Error(MsgCode code, String message) {
		super(code, message);
	}
	public Error(MsgCode code) {
		super(code);
	}
	public Error(MsgCode code, String message, List<Error> errors) {
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
