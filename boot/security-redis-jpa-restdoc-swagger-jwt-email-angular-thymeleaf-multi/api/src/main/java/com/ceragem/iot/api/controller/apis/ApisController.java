package com.ceragem.iot.api.controller.apis;

import com.ceragem.iot.core.domain.CoreCode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApisController.URI_PREFIX)
@Api(tags = "apis")
public class ApisController {
    public static final String URI_PREFIX = "/apis";

    @Autowired
    CacheManager cacheManager;

    @GetMapping(value = {"", "/"})
    public String apis() {
        return "apis";
    }


}
