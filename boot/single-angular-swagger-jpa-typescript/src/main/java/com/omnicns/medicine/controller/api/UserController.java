package com.omnicns.medicine.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.omnicns.java.reflection.ReflectionUtil;
import com.omnicns.java.security.AES256Coder;
import com.omnicns.medicine.code.MsgCode;
import com.omnicns.medicine.domain.Gift;
import com.omnicns.medicine.domain.User;
import com.omnicns.medicine.domain.base.GiftBase;
import com.omnicns.medicine.domain.base.UserBase;
import com.omnicns.medicine.exception.ErrorMsgException;
import com.omnicns.medicine.model.MedicineHeader;
import com.omnicns.medicine.repository.GiftRepository;
import com.omnicns.medicine.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX + UserController.URI_PREFIX)
@Api(tags = "User")
public class UserController {
    public static final String URI_PREFIX = "/users";
    @Value("${project.properties.private-key}")
    private String privateKey;
    @Autowired
    private UserRepository userRepository;

    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    @ApiOperation(value = "유저 조회(로그인)")
    @GetMapping(value = "")
    @JsonView({UserBase.Views.Api.class})
    public User users(
            @ApiParam(name = "cpon", required = true, value = "핸드폰번호(암호)urlEncoding", defaultValue = "7uDjen689undlr84nyEx5Q==")
            @RequestParam(required = true) String cpon
    ) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if(null == cpon || cpon.length() <= 0) {
            throw new ErrorMsgException(MsgCode.E10003);
        }
        cpon = AES256Coder.decode(privateKey, cpon);
        User user = userRepository.findByCpon(cpon);
        if(null == user) {
            throw new ErrorMsgException(MsgCode.E10003);
        }
        user.setUpdDt(ZonedDateTime.now());
        user.setLstLginDt(ZonedDateTime.now());
        userRepository.save(user);
        return user;
    }


}
