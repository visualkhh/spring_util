package com.khh.project.web;

import com.khh.project.config.web.WebSecurityConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(AuthController.PATH_ROOT)
public class AuthController {
    public static final String PATH_ROOT = WebSecurityConfigurerAdapter.AUTH_PATH;
    @RequestMapping({"","/"})
    String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "auth";
    }
}
