package com.ceragem.iot.cms.config.security;


import com.ceragem.iot.wcore.domain.security.UserDetails;
import com.ceragem.iot.wcore.repository.security.WebCoreAuthDetailRepository;
import com.ceragem.iot.core.service.CoreAdmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CoreAdmService adminService;

    @Autowired
    WebCoreAuthDetailRepository authRepository;

    @Autowired
    MessageSourceAccessor messageSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //@Transactional //http://jdm.kr/blog/141
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
        String remoteIP = detail.getRemoteAddress();
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info("Login try ip :  -> " + remoteIP + " input id(" + username + ") info:" + userDetails);
        if (null == userDetails || userDetails.isAccountNonLocked() == false || userDetails.isAccountNonExpired() == false || userDetails.isEnabled() == false || userDetails.isCredentialsNonExpired() == false) {
            throw new UsernameNotFoundException(("msg.error.login.fail"));
//            throw new UsernameNotFoundException(messageSource.getMessage("msg.error.login.fail"));
        }

        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {    //실패
            userDetailsService.pulseLginFailCntByLginId(userDetails.getAdmLginId());
            throw new BadCredentialsException(("msg.error.login.fail"));
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        token.setDetails(userDetails);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
