package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping({"","/"})
    public String heallo() {
        return "helloaa";
    }
    @GetMapping("/whatever")
    public String whatever() {
        return "whatevessssr";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
