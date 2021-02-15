package com.clone.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Api controller.
 */
@Slf4j
@Controller
@RequestMapping(SwaggerController.URI_PREFIX)
public class SwaggerController {
    public static final String URI_PREFIX = "/swagger";

    @GetMapping(value={"","/"})
    public String index(){
        return "redirect:/swagger/swagger-ui.html";
    }

}
