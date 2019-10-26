package com.omnicns.medicine.exception;

import com.omnicns.medicine.code.MsgCode;
import com.omnicns.medicine.model.error.Error;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class ErrorMsgException extends RuntimeException{
	private Error error;
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


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

	public ErrorMsgException(Error error) {
		this.error = error;
	}
	public ErrorMsgException(MsgCode msgCode) {
		this.error = new Error(msgCode.name());
	}
	public ErrorMsgException(Error error, Throwable cause) {
		super(cause);
		this.error = error;
	}



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
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
