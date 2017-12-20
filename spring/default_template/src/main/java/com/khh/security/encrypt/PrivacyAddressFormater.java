package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.convert.ConvertUtil;
import com.omnicns.java.jpa.convert.AttributeViewControllConverter;
import com.omnicns.java.jpa.convert.ConverterAttribute;
import com.omnicns.java.string.StringUtil;

//주소	동 이후, 도로명 이후 모두 적용 (예) 서울특별시 구로구 구로3동 ****
public class PrivacyAddressFormater extends ConverterAttribute implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	char mask = '*';

	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			String[] list = attribute.split(" ");
			String r ="";
			for (int i = 0; i < list.length; i++) {
				String sr ="";
				if(i>=3){
					char[] c = new char[list[i].length()];
					ConvertUtil.fill(c, mask);
					list[i] = new String(c);
				}
			}
			
			attribute = StringUtil.join(list, " ");
		}
		return attribute;
	}



}
