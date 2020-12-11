package com.company.service.monitor.controllers.mindcares.kr;

import com.company.service.monitor.repository.mindcares.kr.MindcareKRMeasureRelationshipRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MindcareKRController.URI_PREFIX)
public class MindcareKRController {
    public static final String URI_PREFIX = "/mindcares/kr";

    @Autowired
    MindcareKRMeasureRelationshipRepositry mindcareKRMeasureRelationshipRepositry;


}
