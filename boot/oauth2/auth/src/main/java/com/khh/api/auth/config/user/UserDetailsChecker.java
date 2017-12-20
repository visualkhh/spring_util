package com.khh.api.auth.config.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsChecker implements org.springframework.security.core.userdetails.UserDetailsChecker{
	@Override
	public void check(UserDetails toCheck) {
	}
}
