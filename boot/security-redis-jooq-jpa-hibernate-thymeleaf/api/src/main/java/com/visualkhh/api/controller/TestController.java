package com.visualkhh.api.controller;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.model.TestVO;
import com.visualkhh.api.domain.User;
import com.visualkhh.api.model.ApiHeader;
import com.visualkhh.api.model.Req;
import com.visualkhh.api.repository.JUserRepository;
import com.visualkhh.api.validate.ValidatedApiHeadGroup;
import com.visualkhh.common.domain.UserBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@Slf4j @EnableCaching
@RestController
@RequestMapping(TestController.URI_PREFIX)
public class TestController {
        public static final String URI_PREFIX 		= "/test";

    @Autowired private JUserRepository userRepository;
    @Autowired private MessageSourceAccessor messageSource;


    @PostMapping(value="/1", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String index(HttpServletResponse response, String msg){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "test "+msg;
    }
    @PostMapping(value="/2", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String i2ndex(HttpServletResponse response, String msg) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "test "+msg;
    }
    @PostMapping(value="/3", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String ia2ndex(HttpServletResponse response, String msg){
        int i=0;
        int z = 5/i;
        return "test "+msg;
    }

    @PostMapping(value="/4", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String ia24dex(@RequestBody @Valid Req req){
        return "test "+req;
    }
    @PostMapping(value="/5", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String ia24dex(ApiHeader header){
        log.debug("ia24dex");
        return "test "+header;
    }
    @PostMapping(value="/6", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String ia245dex(@Valid ApiHeader header){
        return "test "+header;
    }
    @GetMapping(value="/10", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public String ia245d1ex(@ModelAttribute @Valid Req req){
        return "test ";
    }

    @GetMapping(value="/jooq", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public Collection<User> jooqTest(){
        return userRepository.selectAllUsers();
//        return "test ";
    }


    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@Valid UserBase user, Errors errors) {
//        logger.info(tz.getDisplayName());
//        logger.info(zid.getId());
//        logger.info(locale.getDisplayCountry());
//        logger.info(locale.getDisplayLanguage());
//        logger.info(locale.toLanguageTag());
//        String disname=tz.getDisplayName(locale);
//        TimeZone timeZone = calenda.getTimeZone();git pull
//        logger.info(TimeZone.get);
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
//        User n = new User();
//        n.setName("test");
//        n.setEmail("test@test.com");
//        n.setRegDt("2017-11-24 13:59:00");
        if (errors.hasErrors()) {
            return "Dont saved.";
        }
//        userRepository.save(user);
        return "Saved";
    }





    @PostMapping(value="/valid-check", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public TestVO validCheck(@Validated @RequestBody TestVO header){
        return header;
    }


    @GetMapping(value="/header-check", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public ApiHeader headerCheck(ApiHeader header){
        return header;
    }
    @GetMapping(value="/header-check/valid", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public ApiHeader headerCheckValid(@Valid ApiHeader header){
        return header;
    }
    @GetMapping(value="/header-check/valid-serial", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public ApiHeader headerCheckValidSerial(@Validated({ValidatedApiHeadGroup.ValidSerialNo.class}) ApiHeader header){
        return header;
    }
    @GetMapping(value="/header-check/valid-cpon", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public ApiHeader headerCheckValidCpon(@Validated({ValidatedApiHeadGroup.ValidCponId.class}) ApiHeader header){
        return header;
    }
    @GetMapping(value="/header-check/valid-serial-cpon", produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
//    @GetMapping(value="/header-check/valid-serial-cpon", produces=VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE, headers = "Accept!=*/*")
//    @GetMapping(value="/header-check/valid-serial-cpon", headers = "Accept="+VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
//    @GetMapping(value="/header-check/valid-serial-cpon", produces=VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE, headers = "!Accept=*/*")
//    @GetMapping(value="/header-check/valid-serial-cpon", headers = {"!Accept=*/*","Accept:"+VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE})
    public ApiHeader headerCheckValidSerialCpon(HttpServletRequest request, @Validated({ValidatedApiHeadGroup.ValidSerialNoAndCponId.class}) ApiHeader header){
        return header;
    }
}
