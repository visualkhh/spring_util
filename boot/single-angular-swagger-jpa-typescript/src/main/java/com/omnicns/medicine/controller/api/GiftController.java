package com.omnicns.medicine.controller.api;

import com.omnicns.medicine.domain.Gift;
import com.omnicns.medicine.domain.base.AuthBase;
import com.omnicns.medicine.domain.base.GiftBase;
import com.omnicns.medicine.model.MedicineHeader;
import com.omnicns.medicine.repository.GiftRepository;
import com.omnicns.medicine.validate.ValidatedApiHeadGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX + GiftController.URI_PREFIX)
@Api(tags = "Gift")
public class GiftController {
    public static final String URI_PREFIX = "/gifts";
    @Autowired
    private GiftRepository giftRepository;

    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    @ApiOperation(value = "기프트 요청 조회")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "header", required = true, dataType = "string", name = MedicineHeader.HEADER_userSeq, value = "휴대폰 아이디")
//    })
    @GetMapping(value = "")
    //@ApiParam(required = true,  name = MedicineHeader.HEADER_userSeq, value = "휴대폰 아이디")
    public List<Gift> gifts() {
        return giftRepository.findAll();
    }
    @ApiOperation(value = "기프트 요청 조회")
    @GetMapping(value = "/{giftSeq}")
    public Gift gifts(
            @ApiParam(required = true, name = "giftSeq", value = "기프트 SEQ", defaultValue = "1")
            @PathVariable("giftSeq") Integer giftSeq
    ) {
        return giftRepository.findOne(giftSeq);
    }

    @ApiOperation(value = "기프트 요청")
    @PostMapping(value = "")
    public Gift gifts(
            @RequestBody GiftBase giftBase,
            MedicineHeader header
            ) {
        giftBase.setUserSeq(header.getUserSeq());
        return giftRepository.save(giftBase.map(Gift.class));
    }

}
