package com.today.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
public class HouseController {

    @RequestMapping(value = {"", "/"})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "index.html";
    }
    @RequestMapping(value = "/home")
    public String home(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "home.html";
    }
}
