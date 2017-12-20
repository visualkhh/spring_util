package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;

//전화번호	전화번호 국번 뒤2글자, 번호 앞1글자 (예) 0***0772393
public class PrivacyPhoneNumberFormater  extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	char mask = '*';
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			int convertStart = 1;	// 시작위치
			int convertLen = 3; // 몇 글자를 convert 할 것인가?
			attribute = StringUtils.overlay(attribute, StringUtils.repeat(mask, convertLen),convertStart,convertStart+convertLen);
		}
		return attribute;
	}

}
