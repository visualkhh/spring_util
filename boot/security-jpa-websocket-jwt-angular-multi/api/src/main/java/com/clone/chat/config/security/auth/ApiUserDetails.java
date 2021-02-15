package com.clone.chat.config.security.auth;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
public class ApiUserDetails implements UserDetails {
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private boolean use;
//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentialsNonExpired;
//    private boolean isEnabled;

    @Builder
    public ApiUserDetails(String username,
                           String password,
                           Set<? extends GrantedAuthority> grantedAuthorities,
                           boolean use
                           ) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.use = use;
    }

//    public <T extends GrantedAuthority> void addGrantedAuthorities(T data) {
//        this.grantedAuthorities.add(data);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return use;
    }

    @Override
    public boolean isAccountNonLocked() {
        return use;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return use;
    }

    @Override
    public boolean isEnabled() {
        return use;
    }
}
