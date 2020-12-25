package com.ceragem.iot.core.model.msg;

import com.ceragem.iot.core.code.MsgCode;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Msg<T> {
    String code;
    String message;
    T data;

    public Msg() {
    }

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
