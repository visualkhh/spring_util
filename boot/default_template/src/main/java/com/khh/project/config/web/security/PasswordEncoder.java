package com.khh.project.config.web.security;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
