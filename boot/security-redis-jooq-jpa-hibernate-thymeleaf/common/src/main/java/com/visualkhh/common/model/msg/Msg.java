package com.visualkhh.common.model.msg;

import com.visualkhh.common.code.MsgCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class Msg<T>  {
	String code;
	String message;
	T data;


	public Msg(String code) {
		this(code, null);
	}
	public Msg(String code, String message) {
		this(code, message, null);
	}

	public Msg(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Msg(MsgCode code, String message) {
		this(code.name(), message, null);
	}
	public Msg(MsgCode code) {
		this(code.name(), null, null);
	}

}
