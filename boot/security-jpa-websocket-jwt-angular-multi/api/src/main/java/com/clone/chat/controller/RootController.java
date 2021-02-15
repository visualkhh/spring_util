package com.clone.chat.controller;

import com.clone.chat.config.security.WebSecurityConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Api controller.
 */
@Slf4j
@RestController
public class RootController {


    @RequestMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("index");
        return model;
    }


    @GetMapping(value = {"", "/"}, params = {"type=admin"} )
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("login");
        model.addObject("username", WebSecurityConfigurerAdapter.USERNAME_PARAMETER);
        model.addObject("password", WebSecurityConfigurerAdapter.USERPWD_PARAMETER);
        model.addObject("login_processing_url", WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL);
        model.addObject("logout_url", WebSecurityConfigurerAdapter.LOGOUT_URL);
        return model;
    }

}
