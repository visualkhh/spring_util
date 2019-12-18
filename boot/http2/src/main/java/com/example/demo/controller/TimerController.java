package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TimerController {
    @RequestMapping(value = "/timer", method = RequestMethod.GET)
    public String timer() {
        return LocalDateTime.now().toString();
    }
}
