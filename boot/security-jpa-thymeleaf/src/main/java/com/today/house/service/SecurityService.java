package com.today.house.service;

import com.today.house.domain.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {
    public Optional<UserDetails> getUserDetils() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null!=authentication && null!= authentication.getDetails() && UserDetails.class.isAssignableFrom(authentication.getDetails().getClass())) {
            return Optional.of((UserDetails)authentication.getDetails());
        } else {
            return Optional.empty();
        }
    }

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
}
