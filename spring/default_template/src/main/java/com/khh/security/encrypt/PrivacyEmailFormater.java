package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;


//이메일	아이디 뒤 3글자 (예) pppc***@omnicns.co.kr
public class PrivacyEmailFormater  extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	
	String regex = ".{3}@";
	String mask = "***@";

	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			attribute = attribute.replaceAll(regex, mask);
		}
		return attribute;
	}


}
