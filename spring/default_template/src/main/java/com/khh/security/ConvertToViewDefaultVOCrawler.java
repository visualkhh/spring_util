package com.khh.security;

import java.lang.reflect.Field;

import javax.persistence.Convert;

import com.khh.DefaultVO;
import com.omnicns.java.function.FieldFilter;
import com.omnicns.java.jpa.convert.ConvertManager;
import com.omnicns.java.jpa.convert.ConverterAttribute;
import com.omnicns.java.reflection.ReflectionUtil;

public class ConvertToViewDefaultVOCrawler extends FieldFilter{
	Object target;
	Class targetClass = Object.class;
	public ConvertToViewDefaultVOCrawler(Object target) {
		this.target = target;
	}
	public ConvertToViewDefaultVOCrawler() {
	}
	public ConvertToViewDefaultVOCrawler(Class targetClass) {
		this.target = targetClass;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}

	public void startCrawling(Object target) throws Exception{
		setTarget(target);
		ReflectionUtil.findFieldFullDepth(target,this);
	}


	public boolean ownerAccept(Object ownerObject) {
		boolean sw = true;
		if(null!=ownerObject && DefaultVO.class.isAssignableFrom(ownerObject.getClass())){
			DefaultVO dv = (DefaultVO)ownerObject;
			sw = dv.isConvert();
		}
		return sw;
	};
	
	public Object setField(Object ownerObject, Field ownerSubField) throws Exception {
		Object o = getField(ownerObject, ownerSubField);
		if(null!=o && ownerSubField.isAnnotationPresent(Convert.class)){
			ConverterAttribute ca = ConvertManager.getInstance().getConvertor(ownerSubField);
			if(null!=ca){
				o = ca.convertToViewAttribute((String) o);
			}
		}else if(null!=o && DefaultVO.class.isAssignableFrom(ownerSubField.getType())){ //일반 defaultVO쪽에 안쪽또다시 필드가 있을것이다.. o는 defaultVO다
			ReflectionUtil.findFieldFullDepth(o,this);
		}
		return o;
	};
}
