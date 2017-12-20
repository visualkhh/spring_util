package com.khh.project.config.web.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsChecker implements org.springframework.security.core.userdetails.UserDetailsChecker{
	@Override
	public void check(UserDetails toCheck) {
	}
}
