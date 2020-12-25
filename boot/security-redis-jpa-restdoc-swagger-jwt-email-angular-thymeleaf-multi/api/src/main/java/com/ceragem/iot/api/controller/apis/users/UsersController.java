package com.ceragem.iot.api.controller.apis.users;

import com.ceragem.iot.api.controller.apis.ApisController;
import com.ceragem.iot.api.controller.apis.brds.BrdsController;
import com.ceragem.iot.core.domain.CoreArticleContent;
import com.ceragem.iot.core.service.CoreBrdsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "users")
public class UsersController {
    public static final String URI_PREFIX = ApisController.URI_PREFIX+"/users";

    @Autowired
    CoreBrdsService coreBrdsService;


    @GetMapping(value = {"", "/"})
    public List<CoreArticleContent> brds() {
        return coreBrdsService.findAll();
    }

    @PutMapping(value = {"", "/"})
    public List<CoreArticleContent> putBrds() {
        return coreBrdsService.findAll();
    }
    @PutMapping(value = "/{no}")
    public Optional<CoreArticleContent> getBrds(@PathParam("no") Long no) {
        return coreBrdsService.findById(no);
    }
}
