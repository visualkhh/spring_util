package com.visualkhh.api.controller;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.domain.FitBrain;
import com.visualkhh.api.domain.FitBrainResult;
import com.visualkhh.api.model.ApiHeader;
import com.visualkhh.api.service.FitBrainService;
import com.visualkhh.common.code.Code;
import com.visualkhh.common.validate.ValidateGroup;
import com.visualkhh.common.domain.FitBrainResultBase;
import com.visualkhh.common.exception.ErrorException;
import com.visualkhh.common.model.error.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController @Slf4j
//@Transactional
@RequestMapping("/api" + FitBrainController.URI_PREFIX)
public class FitBrainController {
    public static final String URI_PREFIX 		= "/fitbrain";
    private static int PAGE_SIZE = 20;

    @Autowired
    FitBrainService fitBrainService;

    /* 두뇌훈련 콘텐츠 리스트 */
    @GetMapping(value={"","/"})
    public Collection<FitBrain> getList() {
        return fitBrainService.getList();
    }

    /* 두뇌훈련 콘텐츠 상세 */
    @GetMapping(value="/{fitBrainSeq}")
    public FitBrain getDetail(@PathVariable(name = "fitBrainSeq") int fitBrainSeq) {
        return fitBrainService.getDetail(fitBrainSeq);
    }

    /* 두뇌훈련 콘텐츠 결과 */
    @RequestMapping(value = "/history", method = GET, produces = VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public Page<FitBrainResult> getHistory(){
        int pageNumber = 1;
        PageRequest pageRequest =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "brnFitSeq");
        return fitBrainService.findAll(pageRequest);
    }

    @RequestMapping(value = "/history", method = POST, produces = VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public void putHistory(@RequestBody @Validated(ValidateGroup.NotCheckFitBrainResult.class) FitBrainResultBase fitBrainResult, ApiHeader header){
        FitBrainResult t =  fitBrainResult.map(FitBrainResult.class);
        t.setCponId(header.getCponId());
        t.setSerialNo(header.getSerialNo());
        t.setPkgNm(header.getPkgNm());
        t.setOsType(header.getOs());

        FitBrainResult retFitBrainResult = fitBrainService.createFitBrainResult(t);
        if(retFitBrainResult==null) {
            throw  new ErrorException(new Error(Code.E30001));
        }
    }
}