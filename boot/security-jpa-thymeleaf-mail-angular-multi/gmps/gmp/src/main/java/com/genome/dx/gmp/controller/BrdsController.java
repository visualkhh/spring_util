package com.genome.dx.gmp.controller;

import com.genome.dx.wcore.controller.WebCoreBrdsController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(WebCoreBrdsController.URI_PREFIX)
public class BrdsController {

//    @Autowired
//    BrdRepository coreBrdRepository;
//
//    @GetMapping({"", "/"})
//    public String index() {
//        return "bbs index";
//    }
//
//    @GetMapping("/sleep")
//    public String sleep(@RequestParam("s") int s) {
//        log.info("sleep --> {}", s);
//        return "--";
//    }
//
//    @GetMapping("/brd")
//    public List<Brd> brd() {
//        return coreBrdRepository.findAll();
//    }
//    @GetMapping("/throw")
//    public String t() throws Exception {
//        throw new Exception("---------");
//    }

}
