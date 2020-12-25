package com.ceragem.iot.api.config.security.auth;

import com.ceragem.iot.api.domain.User;
import com.ceragem.iot.api.repository.UserRepository;
import com.ceragem.iot.core.domain.CoreUser;
import com.ceragem.iot.core.domain.CoreUserHasProduct;
import com.ceragem.iot.core.repository.CoreUserRepository;
import com.ceragem.iot.api.code.UserRole;
import com.ceragem.iot.wcore.domain.security.AuthDetail;
import com.ceragem.iot.wcore.repository.security.WebCoreUserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebCoreUserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ceragem.iot.wcore.domain.security.UserDetails userDetails = userDetailsRepository.findByAdmLginId(username);
        UserDetails rUserDetails = null;
        if (null == userDetails) {
            Optional<User> userOptional = userRepository.findByEmail(username);
            User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(("msg.error.login.fail")));


            rUserDetails = ApiUserDetails.builder()
                    .username(user.getNo().toString())
                    .password(user.getId())
                    .grantedAuthorities(user.getRoles())
                    .use(true)
                    .build();
            log.debug("find normal user" + rUserDetails.getAuthorities());
        } else {
            Set<SimpleGrantedAuthority> grantedAuthorities = UserRole.AUTH.getGrantedAuthorities();
            List<AuthDetail> list = Optional.ofNullable(userDetails.getAuthDetails()).orElse(Collections.EMPTY_LIST);
            list.stream().forEach(it -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(it.getAuthId()));
            });
            rUserDetails = ApiUserDetails.builder()
                    .username(userDetails.getUsername())
                    .password(userDetails.getPassword())
                    .grantedAuthorities(grantedAuthorities)
                    .use(userDetails.isEnabled())
                    .build();
            log.debug("find admin user" + rUserDetails.getAuthorities());
        }
        return rUserDetails;
    }

}
