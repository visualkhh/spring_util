package com.ceragem.iot.api.controller;

import com.ceragem.iot.api.config.security.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RootController {
    @GetMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("login");
        model.addObject("username", WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
        model.addObject("password", WebSecurityConfigurerAdapter.USERPWD_PARAMETER);
        model.addObject("login_processing_url", WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL);
        model.addObject("logout_url", WebSecurityConfigurerAdapter.LOGOUT_URL);
        return model;
    }
//    @GetMapping(value = "/favicon.ico")
//    public ModelAndView favi(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView model = new ModelAndView("login");
//        model.addObject("username", WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
//        model.addObject("password", WebSecurityConfigurerAdapter.USERPWD_PARAMETER);
//        model.addObject("login_processing_url", WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL);
//        model.addObject("logout_url", WebSecurityConfigurerAdapter.LOGOUT_URL);
//        return model;
//    }
}
