package com.khh.project.security.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;

@Slf4j
@Entity
@Table(name = "USER")
@Data
public class LoginUser implements UserDetails,Serializable {

    @Id
    private String id;
    private String pwd;
    private String name;
    private String addr;

    private boolean auth_account_nonExpired;
    private boolean auth_account_nonLocked;
    private boolean auth_credentials_nonExpired;
    private boolean auth_enabled;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getPwd();
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAuth_account_nonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAuth_account_nonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }
}
