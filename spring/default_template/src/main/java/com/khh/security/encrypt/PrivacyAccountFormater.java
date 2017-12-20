package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;



//주소	동 이후, 도로명 이후 모두 적용 (예) 서울특별시 구로구 구로3동 ****
public class PrivacyAccountFormater extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	String regex = ".$";
	String mask = "#";
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			attribute = attribute.replaceAll(regex, mask);
		}
		return attribute;
	}

}
