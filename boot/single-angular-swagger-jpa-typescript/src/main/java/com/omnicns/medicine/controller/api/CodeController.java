package com.omnicns.medicine.controller.api;

import com.omnicns.medicine.domain.Code;
import com.omnicns.medicine.repository.CodeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX + CodeController.URI_PREFIX)
@Api(tags = "Code")
public class CodeController {
    public static final String URI_PREFIX = "/codes";

    enum Type {
        CHILD,
        PRNT;
    }
    @Autowired
    private CodeRepository codeRepository;

    @ApiOperation(value = "코드")
    @GetMapping(value = "")
    public List<Code> codes(@RequestParam(value = "type", required = false) Type type) {
        List<Code> codes = null;
        if (Type.PRNT == type) {
            codes = codeRepository.findByPrntCdIsNull();
        } else if (Type.CHILD == type) {
            codes = codeRepository.findByPrntCdIsNotNull();
        } else {
            codes = codeRepository.findAll();
        }
        return codes;
    }

    @ApiOperation(value = "코드")
    @GetMapping(value = "/{code}")
    public List<Code> codes(
            @ApiParam(required = true, name = "code", value = "코드", defaultValue = "USE001")
            @PathVariable("code") String code,
            @RequestParam(value = "type", required = false) Type type) {

        List<Code> codes = null;
        if (Type.PRNT == type) {
            codes = codeRepository.findByPrntCd(code);
        } else {
            codes = codeRepository.findByCd(code);
        }
        return codes;
    }
}
