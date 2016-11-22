//package com.khh.project.config.web.security;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.HashSet;
//
///**
// * Created by yong on 2016-05-17.
// *
// * @author yong
// */
//@Data
//public class UserDetailsImpl extends User{
//
//    @Getter
//    @Setter
//    private User user;
//
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new HashSet<>(1);
//        if(user.getRole() != null){
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//            authorities.add(authority);
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getUserPass();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUserEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
