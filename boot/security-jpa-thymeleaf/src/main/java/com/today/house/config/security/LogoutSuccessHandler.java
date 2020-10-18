package com.today.house.config.security;

import com.omnicns.web.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

	@Autowired
    private SessionRegistry sessionRegistry;


	public static final String LOGOUT_SUCCESS_URL = WebSecurityConfigurerAdapter.LOGIN_PAGE+"?type=sign-out";
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		log.debug("onLogoutSuccess:"+request+"     "+response+ "   "+authentication);
//		final HttpSession session = request.getSession();
//		if (session != null) {
//			session.removeAttribute("user");
//		}

//		if (authentication != null && authentication.getDetails() != null) {
//			try {
//				request.getSession().invalidate();
//				System.out.println("User Successfully Logout");
//				//you can add more codes here when the admin successfully logs out,
//				//such as updating the database for last active.
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		HttpSession session = request.getSession();
//		if (session != null){
//			session.removeAttribute("user");
//		}
//		if(null!=request.getSession()){
//			sessionRegistry.removeSessionInformation(request.getSession().getId());
//		}

//		response.setStatus(HttpServletResponse.SC_OK);
//		response.setStatus(HttpStatus.OK.value());
		//redirect to login
		response.sendRedirect(LOGOUT_SUCCESS_URL);
//		response.sendRedirect(WebSecurityConfigurerAdapter.LOGOUT_URL);
		//super.onLogoutSuccess(request,response,authentication);
	}
}
