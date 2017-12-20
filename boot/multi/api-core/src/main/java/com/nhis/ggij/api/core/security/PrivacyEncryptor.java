package com.nhis.ggij.api.core.security;


import com.omnicns.java.function.Encryptor;

public class PrivacyEncryptor implements Encryptor<String,String> {
	private String key;
	public PrivacyEncryptor(String key){
		this.key = key;
	}
	@Override
	public String  encode(String str) throws Exception {
		if(null!=str && str.length()>0) {
			return str;
//			return AES256Coder.encode(this.key, str);
		}else {
			return str;
		}
	}

	@Override
	public String decode(String str) throws Exception {
		if(null!=str && str.length()>0) {
			return str;
//			return AES256Coder.decode(this.key, str);
		}else {
			return str;
		}
	}
}
