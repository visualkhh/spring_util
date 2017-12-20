package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.convert.ConvertUtil;
import com.omnicns.java.jpa.convert.AttributeViewControllConverter;
import com.omnicns.java.string.StringUtil;

//IP	첫 3글자, 셋째 3글자만 표시 (예시) 112.***.**.**
public class PrivacyIPFormater  extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	char mask = '*';
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			String[] list = attribute.split("(\\.|\\:)");
			String r ="";
			for (int i = 0; i < list.length; i++) {
				String sr ="";
				if(i!=0){
					char[] c = new char[list[i].length()];
					ConvertUtil.fill(c, mask);
					list[i] = new String(c);
				}
			}
			
			attribute = StringUtil.join(list, ".");
		}
		return attribute;
	}

}
