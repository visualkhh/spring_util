package com.clone.chat.model;

import java.util.LinkedHashMap;

import com.clone.chat.code.MsgCode;
import lombok.Getter;

@Getter
@Deprecated
public class ResponseForm extends LinkedHashMap<String, Object>{

	private static final long serialVersionUID = -4098281931429200073L;

	public ResponseForm() {
		put("result", "success");
	}
	
	public ResponseForm(String key, Object obj) {
		put(key, obj);
	}
	
	public ResponseForm(MsgCode code, String funcName) {
		put("errMsg", code);
		put("errClass", funcName);
	}
}
