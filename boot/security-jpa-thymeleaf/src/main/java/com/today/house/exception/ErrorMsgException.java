package com.today.house.exception;

import com.today.house.code.MsgCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import com.today.house.model.error.Error;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorMsgException extends RuntimeException {

    private Error error;

    public ErrorMsgException(String message) {
        super(message);
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

    public ErrorMsgException(MsgCode msgCode) {
        this.error = new Error(msgCode.name());
    }

    public ErrorMsgException(Error error) {
        this.error = error;
    }

    public ErrorMsgException(Error error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(super.getMessage()).orElseGet(() -> {
            if (null != this.getError()) {
                return this.getError().getMessage();
            } else {
                return null;
            }

        });
    }
}
