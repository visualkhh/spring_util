package com.khh.security.spring;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.khh.security.SecurityManager;


public class CustomPasswordEncoder implements PasswordEncoder{
	
	String key="";
	public CustomPasswordEncoder() {
	}
	public CustomPasswordEncoder(String key) {
		this.key = key;
	}
	

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String encode(CharSequence rawPassword) {
		String r = "";
		try {
			r = SecurityManager.getInstance().encrypt(getKey(), rawPassword.toString());
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean matches(CharSequence inputPassword, String encodedPasswordByDB) {
		inputPassword = encode(inputPassword);
		return inputPassword.toString().equals(encodedPasswordByDB);
	}

}
