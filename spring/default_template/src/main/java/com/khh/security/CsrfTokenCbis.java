package com.khh.security;

import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenCbis implements CsrfToken {

	@Override
	public String getHeaderName() {
		return "_csrf_header";
	}

	@Override
	public String getParameterName() {
		return "_csrf";
	}

	@Override
	public String getToken() {
		return "_csrf_token";
	}

}
