package com.genome.dx.thesis.controller;

import com.genome.dx.core.domain.Brd;
import com.genome.dx.core.repository.BrdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(TestController.URI_PREFIX)
public class TestController {

    public static final String URI_PREFIX = "/test";

    @Autowired
    BrdRepository coreBrdRepository;

    @GetMapping({"", "/"})
    public String index() {
        return "test index";
    }

    @GetMapping("/sleep")
    public String sleep(@RequestParam("s") int s) {
        log.info("sleep --> {}", s);
        return "--";
    }

    @GetMapping("/brd")
    public List<Brd> brd() {
        return coreBrdRepository.findAll();
    }
    @GetMapping("/throw")
    public String t() throws Exception {
        throw new Exception("---------");
    }

}
