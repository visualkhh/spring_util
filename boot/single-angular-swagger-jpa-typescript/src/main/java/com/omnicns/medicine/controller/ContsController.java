package com.omnicns.medicine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(ContsController.URI_PREFIX)
public class ContsController {
    public static final String URI_PREFIX 		= "/conts";
}
