package com.today.house.model.msg;

import com.today.house.code.MsgCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
