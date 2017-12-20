package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;


//아이디	아이디 뒤 3글자 (예) pppc***
public class PrivacyIDFormater  extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	String regex = "...$";
	String mask = "###";
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			attribute = attribute.replaceAll(regex, mask);
		}
		return attribute;
	}

}
