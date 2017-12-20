package com.khh.security.encrypt;

import javax.persistence.AttributeConverter;

import com.omnicns.java.convert.ConvertUtil;
import com.omnicns.java.jpa.convert.AttributeViewControllConverter;
import com.omnicns.java.string.StringUtil;

//몸무게	앞 1글자 까지만 표시 - 여러 건 노출 시 적용 (예) 6*.**	
public class PrivacyWeightFormater  extends PrivacyEncryptor implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	char mask = '*';
	@Override
	public String convertToViewAttribute(String attribute) {
		if(null!=attribute){
			String[] list = StringUtil.getGroup(attribute, "(.*)\\.(.*)");
			String r ="";
			for (int i = 0; i < list.length; i++) {
				String sr ="";
				if(i==0){
					String regex = "(?<=^.).*";
					char[] c = new char[StringUtil.find(list[i], regex).group().length()];
					ConvertUtil.fill(c, mask);
					list[i] = list[i].replaceAll(regex, new String(c));
				}else{
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
