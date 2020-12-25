package com.ceragem.iot.cms.controller;

import com.ceragem.iot.cms.service.MetasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(MetasController.URI_PREFIX)
public class MetasController {
    public static final String URI_PREFIX = "/metas";

    @Autowired
    MetasService metasService;

    @GetMapping(value = {"", "/"})
    public String metas() {
        return "meats";
    }

}
