package com.visualkhh.cms.config.security;


import com.visualkhh.cms.domain.security.UserDetails;
import com.visualkhh.cms.repository.security.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AuthRepository authRepository;
	@Autowired
	MessageSourceAccessor messageSource;
	@Autowired
	ShaPasswordEncoder shaPasswordEncoder;
	@Autowired
	HttpServletRequest httpServletRequest;
	@Value("${project.properties.salt}")
	String salt;

    @Transactional //http://jdm.kr/blog/141
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
		String remoteIP = detail.getRemoteAddress();
//	    RequestUtil.getRequest();
		String username = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
	    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		log.info("Login try ip :  -> "+remoteIP+" input id("+username+") info:"+userDetails);
		if(null == userDetails || userDetails.isAccountNonLocked()==false || userDetails.isAccountNonExpired() == false || userDetails.isEnabled() == false || userDetails.isCredentialsNonExpired() == false) {
			throw new UsernameNotFoundException(messageSource.getMessage("msg.error.login.fail"));
		}

		if(!shaPasswordEncoder.isPasswordValid(userDetails.getPassword(), password, salt)){    //실패
			throw new BadCredentialsException(messageSource.getMessage("msg.error.login.fail"));
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
