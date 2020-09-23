package com.genome.dx.wcore.service;

import com.genome.dx.core.code.CrudTypeCd;
import com.genome.dx.wcore.domain.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SecurityService {
    public UserDetails getUserDetils() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null!=authentication && null!= authentication.getDetails() && UserDetails.class.isAssignableFrom(authentication.getDetails().getClass())) {
            return (UserDetails)authentication.getDetails();
        } else {
            return null;
        }
    }

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
}
