package com.visualkhh.common.exception;

import com.visualkhh.common.code.MsgCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import com.visualkhh.common.model.error.Error;
import java.util.Optional;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class ErrorMsgException extends RuntimeException{
	Error error;
	HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public ErrorMsgException(MsgCode error, HttpStatus status) {
		this.error = new Error(error);
		this.httpStatus = status;
	}

	public ErrorMsgException(String message, Error error) {
		super(message);
		this.error = error;
	}

	public ErrorMsgException(String message, Throwable cause, Error error) {
		super(message, cause);
		this.error = error;
	}

	public ErrorMsgException(Throwable cause, Error error) {
		super(cause);
		this.error = error;
	}

	public ErrorMsgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.error = error;
	}

	public ErrorMsgException(Error error, MsgCode code) {
		this.error = error;
	}
	public ErrorMsgException(Error error, Throwable cause) {
		super(cause);
		this.error = error;
	}

	@Override
	public String getMessage() {
		return Optional.ofNullable(super.getMessage()).orElseGet(()->{
			if(null!=this.getError()){
				return this.getError().getMessage();
			}else{
				return null;
			}

		});
	}
}
