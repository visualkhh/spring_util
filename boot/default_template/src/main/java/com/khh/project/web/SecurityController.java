package com.khh.project.web;

import com.khh.project.config.web.WebSecurityConfigurerAdapter;
import com.khh.project.service.security.SecurityService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping(SecurityController.PATH_ROOT)
public class SecurityController {

    public static final String PATH_ROOT = WebSecurityConfigurerAdapter.SECURITY_PATH;

    @Autowired
    SecurityService service;
    @RequestMapping({"",PATH_ROOT})
    String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "security/login";
    }


}
