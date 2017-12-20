package com.visualkhh.api.controller;

import com.visualkhh.api.domain.Brd;
import com.visualkhh.api.service.BrdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@EnableCaching
@RestController
@RequestMapping("/api"+ BrdController.URI_PREFIX)
public class BrdController {
    public static final String URI_PREFIX 		= "/bbs";

    @Autowired
    private BrdService brdService;

    /* 공지사항 */
    @GetMapping(value="/notice")
    public List<Brd> getNotice() {
        return brdService.notice();
    }

    /* 도움말 */
    @GetMapping(value="/faq")
    public List<Brd> getFaq() {
        return brdService.faq();
    }

    /* 가이드 */
    @GetMapping(value="/guide")
    public List<Brd> getGuide() {
        return brdService.guide();
    }
}
