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
@RequestMapping(DocsController.URI_PREFIX)
public class DocsController {
    public static final String URI_PREFIX = "/docs";

    @GetMapping(value={"","/"})
    public String index(){
        return "docs/index";
    }

}
