package com.ceragem.iot.cms.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Autowired
	MessageSourceAccessor msgSource;

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			log.info("User '" + auth.getName()+ "' attempted to access the protected URL: " + httpServletRequest.getRequestURI());
//		}
//		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
		httpServletResponse.sendError(403, msgSource.getMessage("msg.error.unauth.user"));

	}

}
