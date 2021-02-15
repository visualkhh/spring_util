package com.clone.chat.controller.api.anon;

import com.clone.chat.code.MsgCode;
import com.clone.chat.controller.api.anon.model.RequestSignUp;
import com.clone.chat.domain.User;
import com.clone.chat.exception.BusinessException;
import com.clone.chat.exception.ErrorTrace;
import com.clone.chat.repository.UserRepository;
import com.clone.chat.service.TokenService;
import com.clone.chat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(AnonApisController.URI_PREFIX)
@Slf4j
@Api(tags = "유저")
public class AnonApisController {
    public static final String URI_PREFIX = "/anon-apis";

    @Autowired
    private final UserService userService;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    public void join(@Valid @ModelAttribute RequestSignUp requestSignUp) {

        userService.signUp(requestSignUp);
    }

    @ApiOperation(value = "회원찾기")
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable("id") String id) {
        Optional<User> user = userService.find(id);
        user.orElseThrow(() -> new BusinessException(MsgCode.ERROR_ENTITY_NOT_FOUND, ErrorTrace.getName()));
        return user.get();
    }
}
