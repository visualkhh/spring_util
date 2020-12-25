package com.ceragem.iot.cms.controller;

import com.ceragem.iot.core.domain.CoreArticleContent;
import com.ceragem.iot.core.service.CoreBrdsService;
import com.omnicns.web.spring.security.SecurityUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(UsersController.URI_PREFIX)
@Api(tags = "brds")
public class UsersController {
    public static final String URI_PREFIX = "/users";

    @Autowired
    CoreBrdsService coreBrdsService;

    @GetMapping(value = {"", "/"})
    public List<CoreArticleContent> brds() {
        return coreBrdsService.findAll();
    }

    @PutMapping(value = {"", "/"})
    public List<CoreArticleContent> putBrds() {
        Authentication authentication = SecurityUtil.getAuthentication();
        return coreBrdsService.findAll();
    }

    @PutMapping(value = "/{no}")
    public Optional<CoreArticleContent> getBrds(@PathParam("no") Long no) {
        return coreBrdsService.findById(no);
    }


}
