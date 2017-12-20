package com.khh;

import com.omnicns.java.reflection.ReflectionUtil;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class DefaultVO   extends Default implements Serializable{
	private BindingResult errors;
	private boolean convert=true;
//	String id    = null;          				//----id
	public DefaultVO() {
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public boolean isConvert() {
		return convert;
	}
	public void convert(boolean convert) {
		this.convert = convert;
	}
	public DefaultVO importMyNotNullField(Object sourceObject){
		ReflectionUtil.importTargetNotNullField(sourceObject, this);
		return this;
	}
	public DefaultVO importMyNullField(Object sourceObject){
		ReflectionUtil.importTargetNullField(sourceObject, this);
		return this;
	}
	public DefaultVO importSourceNotNullField(Object sourceObject){
		ReflectionUtil.importSourceNotNullField(sourceObject, this);
		return this;
	}
	public <T> T copyField(Class<T> targetClass) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		T t = ReflectionUtil.copyField(this, targetClass);
		return t;
	}
//	public Object exportField(Object targetObject){
//		ReflectionUtil.importField(this, targetObject);
//		return targetObject;
//	}
	
//	public void convert() throws Exception{
//		convert(isConvert());
//	}
//	public void convert(boolean wantConvert) throws Exception{
//		if(wantConvert){
//			SecurityManager.getInstance().privacyAnnoConvertByDefaultVO(this);
//		}
//	}
	
	public void validate(BindingResult errors){
		
	}
	
	public BindingResult getErrors() {
		return errors;
	}
	public void setErrors(BindingResult errors) {
		this.errors = errors;
	}

	
}