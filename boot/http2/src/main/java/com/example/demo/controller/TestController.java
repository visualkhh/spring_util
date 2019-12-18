package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;

@Controller
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(ServletRequest servletRequest, Model model) {
        model.addAttribute("ip", servletRequest.getRemoteAddr());
        return "index";
    }
}
