package com.khh.project.config.web.security;

import com.khh.project.config.web.security.user.LoginUserDetails;
import com.khh.project.config.web.security.user.LoginUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	@Autowired
	LoginUserDetailsService userDetailsService;
	@Autowired
	MessageSourceAccessor messageSource;

	@Autowired
	PasswordEncoder encoder;
	@Override
//	@Transactional //http://jdm.kr/blog/141
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		//detail에는 Object가 넘어온다  oauth  일경우에는 맵이 들어온다
//		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
//		String remoteIP = detail.getRemoteAddress();
		String username = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		LoginUserDetails userDetails = userDetailsService.loadUserByUsername(username);
		log.info("Login try ip :  ->  input id("+username+") info:"+userDetails);
		if(null == userDetails || userDetails.isAccountNonLocked()==false || userDetails.isAccountNonExpired() == false || userDetails.isEnabled() == false || userDetails.isCredentialsNonExpired() == false) {
			throw new UsernameNotFoundException(messageSource.getMessage("error.login.fail"));
		}


		if(!encoder.matches(encoder.encode(password),userDetails.getPassword())){    //실패
			throw new BadCredentialsException(messageSource.getMessage("error.login.fail"));
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
//		token.setDetails(userDetails);
		return token;
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
