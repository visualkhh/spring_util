//package com.khh.project.config.web.security;
//
//import com.libqa.web.domain.User;
//import com.libqa.web.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Yong on 2016-05-17.
// *
// * @author Yong
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByEmailAndIsCertification(username);
//
//        if(user == null) {
//            throw new UsernameNotFoundException("Not Exist User");
//        }
//        return new UserDetailsImpl(user);
//    }
//
//}
