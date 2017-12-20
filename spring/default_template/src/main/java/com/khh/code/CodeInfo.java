package com.khh.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.google.gson.annotations.Expose;

import lombok.Data;


public @Data class CodeInfo {
	@Expose
	String code;
	@Expose
	String msg;
	@Expose
	Object data;
	
	FieldInfo field;
	Map<String,Code> childs;
	
	public CodeInfo() {
		init();
	}
	public CodeInfo(String code, String msg) {
		this.code = code;
		this.msg = msg;
		init();
	}
	private void init() {
		childs 	= new LinkedHashMap<String,Code>();
//		db 		= new DBInfo();
		field	= new FieldInfo();
	}
	public void setCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public void setCode(String code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public CodeInfo field(FieldInfo field){
		this.field=field;
		return this;
	}
	public CodeInfo putChild(Code... codes){
		if(null!=codes){
			for(Code atCode : codes){
				childs.put(atCode.cd(), atCode);
			}
		}
		return this;
	}
	public void forEechChilds(Consumer<Code> consumer){
		childs.forEach((key,val)->{
			consumer.accept(val);
		});
	}
	public Code child(String code){
		Code atCode = childs.get(code);
		if(null==atCode){
			atCode=Code.UNKNOW;
		}
		return atCode;
		
	}
	@Override
	public String toString() {
		return code+" "+msg;
	}
	
	
}