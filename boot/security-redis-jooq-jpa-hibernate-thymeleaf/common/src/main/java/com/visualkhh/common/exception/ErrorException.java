package com.visualkhh.common.exception;

import com.visualkhh.common.code.Code;
import com.visualkhh.common.model.error.Error;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class ErrorException extends RuntimeException{
	Error error;

	public ErrorException(Code error) {
		this.error = new Error(error);
	}

	public ErrorException(String message, Error error) {
		super(message);
		this.error = error;
	}

	public ErrorException(String message, Throwable cause, Error error) {
		super(message, cause);
		this.error = error;
	}

	public ErrorException(Throwable cause, Error error) {
		super(cause);
		this.error = error;
	}

	public ErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.error = error;
	}

	public ErrorException(Error error) {
		this.error = error;
	}
	public ErrorException(Error error, Throwable cause) {
		super(cause);
		this.error = error;
	}



}
