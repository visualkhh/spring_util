package com.khh.project.config.web.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


//@Transactional //http://jdm.kr/blog/141
public class LoginUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired
	MessageSourceAccessor messageSource;

	@Autowired
	LoginUserRepository userRepository;

	@Override
	public LoginUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUserDetails userDetails = userRepository.findByUsername(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(messageSource.getMessage("error.login.fail"));
		}
		return userDetails;
	}
}
