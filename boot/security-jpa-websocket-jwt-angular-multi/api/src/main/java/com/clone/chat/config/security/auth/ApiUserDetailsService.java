package com.clone.chat.config.security.auth;

import com.clone.chat.code.UserRole;
import com.clone.chat.domain.User;
import com.clone.chat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Optional<User> userOptional = userRepository.findById(username);
            User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(("msg.error.login.fail")));


        ApiUserDetails rUserDetails = ApiUserDetails.builder()
                    .username(user.getId())
                    .password(user.getPassword())
                    .grantedAuthorities(user.getRole().getGrantedAuthorities())
                    .use(true)
                    .build();
            log.debug("find normal user" + rUserDetails.getAuthorities());

        return rUserDetails;
    }

}
