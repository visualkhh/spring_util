package com.visualkhh.api.controller.api;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.domain.UserDvc;
import com.visualkhh.api.model.ApiHeader;
import com.visualkhh.api.service.UserService;
import com.visualkhh.api.validate.ValidatedApiHeadGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @EnableCaching @RestController
@RequestMapping(SettingController.URI_PREFIX)
public class SettingController {
    public static final String URI_PREFIX 		= ApiController.URI_PREFIX+"/setting";

    @Autowired
	UserService userService;

    @PostMapping(value="/user", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public void headerCheckValidSerialCpon(@RequestBody UserDvc userDvc, @Validated({ValidatedApiHeadGroup.ValidSerialNoAndCponId.class}) ApiHeader header){
        userService.setUserDvcAgeCdAndGenCd(header.getUserDvcSeq(), userDvc.getAgeCd(), userDvc.getGenCd());
    }
}
