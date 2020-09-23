package com.genome.dx.wcore.config.security;

import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.domain.ActionHistory;
import com.genome.dx.core.domain.base.ActionHistoryBase;
import com.genome.dx.core.repository.ActionHistoryRepository;
import com.genome.dx.wcore.domain.security.UserDetails;
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

	@Autowired
	ActionHistoryRepository actionHIstoryRepository;

	public static final String LOGOUT_SUCCESS_URL = WebSecurityConfigurerAdapter.LOGIN_PAGE+"?type=sign-out";
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		log.debug("onLogoutSuccess:"+request+"     "+response+ "   "+authentication);
		UserDetails details = (UserDetails)authentication.getDetails();
		ActionHistory actionHistory = ActionHistoryBase
				.builder()
				.admSeq(details.getAdmSeq())
				.actCd(ActionCd.ACT003)
				.ip(RequestUtil.getRemoteAddr(request))
				.url(request.getRequestURI())
				.build().map(ActionHistory.class);
		this.actionHIstoryRepository.save(actionHistory);
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
