package com.khh.api.auth.config.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


//@Transactional //http://jdm.kr/blog/141
@Service
@Slf4j
public class LoginUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired
	MessageSourceAccessor messageSource;

	@Autowired
	LoginUserRepository userRepository;

	@Override
	public LoginUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<LoginUserDetails> list = userRepository.findAll();
		//log.debug(list.size()+"");
		LoginUserDetails userDetails = userRepository.findByUsername(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(messageSource.getMessage("error.login.fail"));
		}
		return userDetails;
	}
}
