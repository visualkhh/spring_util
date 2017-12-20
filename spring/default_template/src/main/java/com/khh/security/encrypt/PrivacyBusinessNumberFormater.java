package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;


//사업자등록번호	앞 5글자 까지만 표시 (예) 113-86-*****
public class PrivacyBusinessNumberFormater extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	String regex = ".{5}$";
	String mask = "*****";
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			attribute = attribute.replaceAll(regex, mask);
		}
		return attribute;
	}

}
