package com.genome.dx.wcore.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

//    @Autowired
//    private LoginAttemptService loginAttemptService;
//    @Autowired private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public void onApplicationEvent(AuthenticationSuccessEvent e) {
//        log.debug("onApplicationEvent loginSuccess {}",e);
        String admLginId = (String)e.getAuthentication().getPrincipal();
        userDetailsService.pulseLginFailCntByLginId(admLginId);
//        jAdmRepository.setLginFailCnt(admLginId, new Integer(0));

        //        WebAuthenticationDetails auth = (WebAuthenticationDetails)
//                e.getAuthentication().getDetails();
//
//        loginAttemptService.loginSucceeded(auth.getRemoteAddress());
    }
}
