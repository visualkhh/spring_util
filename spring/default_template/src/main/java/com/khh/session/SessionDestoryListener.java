package com.khh.session;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.khh.login.vo.LoginUserVO;

@Component
public class SessionDestoryListener implements ApplicationListener<SessionDestroyedEvent> {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override//session destory될때
	public void onApplicationEvent(SessionDestroyedEvent event) {
		List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        //UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext){
        	LoginUserVO o = (LoginUserVO)securityContext.getAuthentication().getPrincipal();
        	log.debug(o.toString());
            // ...
        }
        
//        Content.getRequest().  setAttribute("expired", "vvvvvv");
		
	}

}
