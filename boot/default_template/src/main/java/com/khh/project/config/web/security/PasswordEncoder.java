package com.khh.project.config.web.security;

/**
 * Created by Administrator on 2016-11-30.
 */
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}
}
