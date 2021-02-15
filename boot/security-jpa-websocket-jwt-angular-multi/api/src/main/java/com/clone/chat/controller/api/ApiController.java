package com.clone.chat.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Api controller.
 */
@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX)
public class ApiController {
    public static final String URI_PREFIX = "/apis";

    @GetMapping(value={"","/"})
    public String index(){
        return "api welcom";
    }

}
