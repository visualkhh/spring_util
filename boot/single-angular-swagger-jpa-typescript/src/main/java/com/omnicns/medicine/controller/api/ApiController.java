package com.omnicns.medicine.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Api controller.
 */
@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX)
//@Api(hidden = true)
public class ApiController {
    public static final String URI_PREFIX = "/api";

    @ApiOperation(value = "", hidden = true)
    @GetMapping(value="")
    public String index(){
        return "medicine api";
    }

}
