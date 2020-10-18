package com.today.house.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFailureListener  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

//    @Autowired
//    private LoginAttemptService loginAttemptService;
    @Autowired
    private UserDetailsService userDetailsService;


    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) e.getAuthentication().getDetails();
        String username = (String) e.getAuthentication().getPrincipal();

//        Adm adm = adminService.findByAdmLginId(username);
//        adm.setLginFailCnt(new Integer(55));
//        adminService.save(adm);

//        userDetailsService.setLginFailCnt(username);
//        userDetails.setLginFailCnt(new Integer(Optional.ofNullable(userDetails.getLginFailCnt()).orElse(new Integer(0)).intValue()+1));
//        userDetailsService.save(userDetails);
        log.debug("onApplicationEvent Fail {}",e);
//        loginAttemptService.loginFailed(auth.getRemoteAddress());
    }
}
