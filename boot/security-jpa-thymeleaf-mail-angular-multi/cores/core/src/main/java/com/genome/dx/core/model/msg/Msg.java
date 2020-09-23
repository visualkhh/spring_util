package com.genome.dx.core.model.msg;

import com.genome.dx.core.code.MsgCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Msg<T> {
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
