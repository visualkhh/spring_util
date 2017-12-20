package com.visualkhh.api.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @EnableCaching @RestController
@RequestMapping(ApiController.URI_PREFIX)
public class ApiController {
    public static final String URI_PREFIX 		= "/api";

    @GetMapping(value={"","/"})
    public void index(){
    }

}
