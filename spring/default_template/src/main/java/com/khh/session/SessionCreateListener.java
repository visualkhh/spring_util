package com.khh.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionCreateListener implements ApplicationListener<SessionCreationEvent> {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override//Session생성될때
	public void onApplicationEvent(SessionCreationEvent event) {
		Object o = event.getSource();
//		
//		Object  oo = SecurityManager.getInstance().getSecurityUser();
        //UserDetails ud;
		
	}

}
