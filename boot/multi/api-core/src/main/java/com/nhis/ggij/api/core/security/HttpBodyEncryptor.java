package com.nhis.ggij.api.core.security;


import com.omnicns.java.function.Encryptor;
import com.omnicns.java.security.Base64Coder;

public class HttpBodyEncryptor implements Encryptor<byte[],String> {
	private String key;
	public HttpBodyEncryptor(String key) {
		this.key = key;
	}

	@Override
	public String encode(byte[] bytes) throws Exception {
		if(null!=bytes && bytes.length>0) {
//			return Base64Coder.encodeString(bytes);
//			return AES256Coder.encode(this.key, bytes);
			return new String(bytes);
		}else {
			return null;
		}
	}

	@Override
	public byte[] decode(String str) throws Exception {
		if(null!=str && str.length()>0) {
//			return Base64Coder.decode(str);
//			return AES256Coder.decode(this.key, bytes);
			return str.getBytes();
		}else {
			return null;
		}
	}


}
