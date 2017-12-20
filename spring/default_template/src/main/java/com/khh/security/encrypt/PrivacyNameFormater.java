package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;


//성명	성명 뒤 1글자 (예) 박병*
public class PrivacyNameFormater extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>  {
	String regex = ".$";
	String mask = "*";
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute && attribute.length()>0){
			attribute = attribute.replaceAll(regex, mask);
		}
		return attribute;
	}
}
