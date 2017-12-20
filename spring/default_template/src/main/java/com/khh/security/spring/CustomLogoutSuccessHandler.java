package com.khh.security.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public class CustomLogoutSuccessHandler extends com.omnicns.web.spring.security.CustomLogoutSuccessHandler<Authentication> {
	public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
		String refererUrl = request.getHeader("Referer");
		// auervice.track("Logout from: " + refererUrl);

		super.onLogoutSuccess(request, response, authentication);
	}
}
