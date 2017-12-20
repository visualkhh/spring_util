package com.visualkhh.cms.config.security;

import com.visualkhh.cms.domain.security.UserDetails;
import com.visualkhh.cms.repository.security.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired
	MessageSourceAccessor messageSource;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userDetailsRepository.findByAdmLginId(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(messageSource.getMessage("msg.error.login.fail"));
		}
		return userDetails;
	}
}