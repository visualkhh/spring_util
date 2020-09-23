package com.genome.dx.gmp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.domain.Custom;
import com.genome.dx.core.domain.PtntInfo;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.gmp.domain.Evidence;
import com.genome.dx.gmp.service.EvidencesService;
import com.genome.dx.gmp.service.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(EvidencesController.URI_PREFIX)
public class EvidencesController {

    public static final String URI_PREFIX = "/evidences";

    @Autowired
    EvidencesService evidencesService;



    @GetMapping(value="/{evidenceId}")
    @JsonView({JsonViewFrontEnd.class})
    public Custom patientEvidences(@PathVariable(name = "evidenceId") Long evidenceId) {
        return  evidencesService.findCustomById(evidenceId);
    }

}
