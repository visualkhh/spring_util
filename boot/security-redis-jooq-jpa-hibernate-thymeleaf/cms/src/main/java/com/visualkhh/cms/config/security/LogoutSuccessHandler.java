package com.visualkhh.cms.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		log.debug("onLogoutSuccess:"+request+"     "+response+ "   "+authentication);

		if (authentication != null && authentication.getDetails() != null) {
			try {
				request.getSession().invalidate();
				System.out.println("User Successfully Logout");
				//you can add more codes here when the admin successfully logs out,
				//such as updating the database for last active.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.setStatus(HttpServletResponse.SC_OK);
		//redirect to login
		response.sendRedirect(WebSecurityConfigurerAdapter.ROOT_PATH);
		//super.onLogoutSuccess(request,response,authentication);
	}
}
