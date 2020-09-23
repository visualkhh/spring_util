package com.genome.dx.thesis.controller;

import com.genome.dx.wcore.config.security.WebSecurityConfigurerAdapter;
import com.genome.dx.wcore.service.AnonsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(AnonController.URI_PREFIX)
public class AnonController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH;

    @Autowired
    private AnonsService anonService;


    @GetMapping(value="/wow")
    public Object detail(){
        return  "wow";
    }


}
