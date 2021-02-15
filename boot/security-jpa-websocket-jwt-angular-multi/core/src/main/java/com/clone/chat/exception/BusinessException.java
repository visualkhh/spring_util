package com.clone.chat.exception;

import com.clone.chat.code.MsgCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3269944136608201287L;
	MsgCode code;
	String function;
	
	public BusinessException(MsgCode code, String function) {
		this.code = code;
		this.function = function;
	}
	public BusinessException(MsgCode code) {
		this.code = code;
		this.function = code.name();
	}
}
