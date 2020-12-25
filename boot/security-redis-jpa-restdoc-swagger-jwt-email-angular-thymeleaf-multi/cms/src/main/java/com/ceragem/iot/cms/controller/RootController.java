package com.ceragem.iot.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RootController {
    @RequestMapping(value = {"", "/"})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "index.html";
    }
}
