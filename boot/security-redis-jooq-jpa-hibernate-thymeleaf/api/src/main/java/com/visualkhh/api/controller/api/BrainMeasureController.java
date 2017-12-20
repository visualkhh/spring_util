package com.visualkhh.api.controller.api;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.domain.BrainMeasure;
import com.visualkhh.api.model.ApiHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableCaching
@RestController
@RequestMapping(BrainMeasureController.URI_PREFIX)
public class BrainMeasureController {
    public static final String URI_PREFIX 		= ApiController.URI_PREFIX+"/measures";

    @PostMapping(value={"/me", "/me/"}, produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public void setMeasureMe(@RequestBody BrainMeasure brainMeasure, @Validated ApiHeader header){
        brainMeasure.setUserDvcSeq(1);


        return ;
    }

    @PostMapping(value={"/guest", "/guest/"}, produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public void setMeasureGuest(){

        return ;
    }
}
