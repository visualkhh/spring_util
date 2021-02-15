package com.clone.chat.controller.api.auths;

import com.clone.chat.annotation.ModelAttributeMapping;
import com.clone.chat.controller.api.ApiController;
import com.clone.chat.model.UserToken;
import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping(AuthsController.URI_PREFIX)
@Slf4j
@Api(tags = "권한")
public class AuthsController {
    public static final String URI_PREFIX = ApiController.URI_PREFIX + "/auths";

//    @Autowired
//    TokenService tokenService;

    @PostMapping(value = "/details")
    @JsonView({JsonViewApi.class})
//    public User refresh(HttpServletRequest request, HttpServletResponse response, @RequestHeader(value= HttpHeaders.AUTHORIZATION) String authorization_header) {
    public UserToken refresh(HttpServletRequest request, HttpServletResponse response, @ModelAttributeMapping UserToken userToken) {
        return userToken;
        //        return tokenService.getUserFromJwtHeader(authorization_header);
    }

}
