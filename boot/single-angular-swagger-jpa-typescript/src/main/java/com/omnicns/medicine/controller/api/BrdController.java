package com.omnicns.medicine.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.omnicns.medicine.code.BrdCateCd;
import com.omnicns.medicine.domain.Brd;
import com.omnicns.medicine.domain.base.BrdBase;
import com.omnicns.medicine.repository.BrdRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("api-bbsController")
@RequestMapping(ApiController.URI_PREFIX + BrdController.URI_PREFIX)
@Api(tags = "Brd")
public class BrdController {
    public static final String URI_PREFIX = "/brd";
    @Autowired
    private BrdRepository brdRepository;


    @ApiOperation(value = "게시물 조회")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String::class, example = "Bearer access_token"),
//            @ApiImplicitParam(name = "X-Custom-Header", value = "A Custom Header", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String::class, example = "my header example")
//    )//@ApiImplicitParams({
//        @ApiImplicitParam(name = "My obj text",
//        value = "myObjText", required = true,
//        dataType = "com.example.MyObject", paramType = "body")
//})
    //@ApiImplicitParam(name = "id", value = "게시판 고유키", required = false, dataType = "string", paramType = "path", defaultValue = ""),
    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-cponId",     value = "휴대폰 아이디"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-serialNo",   value = "디바이스 고유 번호"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-cponModel",  value = "PHONE 모델명"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-osTypeCd",   value = "PHONE OS 정보"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-osVersion",  value = "PHONE OS VERSION"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-pkgNm",      value = "어플 패키지명"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-pkgVersion", value = "어플 VERSION"),
//            @ApiImplicitParam(paramType = "header", required = false, dataType = "int",    name = "X-userSeq",    value = "서버 회원 고유 아이디"),
    })
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "Invalid input:…"),
//            @ApiResponse(code = 200, message = "Ok" )
//    })
    @JsonView({BrdBase.Views.Brd.class})
    @GetMapping(value = "")
    public List<Brd> bbs(
            @ApiParam(required = false, name = "cateCd", value = "게시물 카테고리 코드")
            @RequestParam(value = "cateCd", required = false) BrdCateCd brdCateCd
    ) { //@Validated({ValidatedApiHeadGroup.ValidCponId.class}) MedicineHeader omnifitHeader
        if (null != brdCateCd) {
            return brdRepository.findByCateCd(brdCateCd);
        } else {
            return brdRepository.findAll();
        }
    }

//    @ApiOperation(value = "게시물 조회")
//    @JsonView({BrdBase.Views.Brd.class})
////    @GetMapping(value="/{cateCd}")
//    @GetMapping(value="", params = {"cateCd"})
//    public List<Brd> bbs(
//            @ApiParam(required = true, name = "cateCd", value = "게시물 카테고리 코드", defaultValue = "BCC001")
//            @RequestParam("cateCd") BrdCateCd brdCateCd
//    ) {
//        return brdRepository.findByCateCd(brdCateCd);
//    }


//
//    @ApiOperation(value = "도움말 조회")
//    @ApiImplicitParams({
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-cponId",     value = "휴대폰 아이디"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-serialNo",   value = "디바이스 고유 번호"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-cponModel",  value = "PHONE 모델명"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-osTypeCd",   value = "PHONE OS 정보"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-osVersion",  value = "PHONE OS VERSION"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-pkgNm",      value = "어플 패키지명"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "string", name = "X-pkgVersion", value = "어플 VERSION"),
////            @ApiImplicitParam(paramType = "header", required = false, dataType = "int",    name = "X-userSeq",    value = "서버 회원 고유 아이디")
//    })
//    @JsonView({BrdBase.Views.Brd.class})
//    @GetMapping(value="/helps")
//    public List<Brd> helps() { //@Validated({ValidatedApiHeadGroup.ValidCponId.class}) MedicineHeader omnifitHeader
////        return brdService.helpSvcCdNull();
//        return null;
//    }

}
