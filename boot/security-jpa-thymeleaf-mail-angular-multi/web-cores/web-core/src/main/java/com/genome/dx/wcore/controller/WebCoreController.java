package com.genome.dx.wcore.controller;

import com.genome.dx.core.repository.AdmRepository;
import com.genome.dx.wcore.config.security.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j

@Controller
public class WebCoreController {

    @Autowired
    private AdmRepository userRepository;
    @Autowired
    private MessageSourceAccessor messageSource;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AdmRepository admRepository;

    @RequestMapping(value = {"", "/"})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "index.html";
    }



}
