package com.visualkhh.api.controller.api;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.domain.Code;
import com.visualkhh.api.service.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@EnableCaching
@RestController
@RequestMapping(CodeController.URI_PREFIX)
public class CodeController {
    public static final String URI_PREFIX 		= ApiController.URI_PREFIX+"/codes";

    @Autowired private CodeService codeService;

    @RequestMapping(value = {"","/"}, method = GET, produces = VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public Collection<Code> getCodes(){
        Collection<Code> codes = codeService.codes();
        return codes;
    }
}
