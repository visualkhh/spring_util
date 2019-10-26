package com.omnicns.medicine.controller;

import com.omnicns.medicine.service.MeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j

@RestController
@RequestMapping(MeasureController.URI_PREFIX)
public class MeasureController {
    public static final String URI_PREFIX = "/measure";

    @Autowired
    MeasureService measureService;

//    /* 퀵트레이닝 결과 */
//    @RequestMapping(value = "/qtraining-result")
//    public Page<QtrainingResult> qtrainingResult(DataTablePageRequest pageable) {
//        Page<QtrainingResult> page = measureService.qtrainingResult(pageable);
//        DataTablePage dataTablePage = new DataTablePage(page);
//        return dataTablePage;
//    }
//
//    /* 두뇌측정 결과 */
//    @GetMapping(value = "/brain-result")
//    public Page<BrainMeasure> brainResult(DataTablePageRequest pageable) {
//        Page<BrainMeasure> page = measureService.brainResult(pageable);
//        DataTablePage dataTablePage = new DataTablePage(page);
//        return dataTablePage;
//    }
//
//    /* 두뇌측정 결과 */
//    @GetMapping(value = "/study-result")
//    public Page<StudyResult> studyResult(DataTablePageRequest pageable) {
//        Page<StudyResult> page = measureService.studyResult(pageable);
//        DataTablePage dataTablePage = new DataTablePage(page);
//        return dataTablePage;
//    }
//
//    /* 두뇌 훈련 측정 결과 */
//    @GetMapping(value = "/fitbrain-result")
//    public Page<FitBrainResult> fitbrainResult(DataTablePageRequest pageable) {
//        Page<FitBrainResult> page = measureService.fitbrainResult(pageable);
//        DataTablePage dataTablePage = new DataTablePage(page);
//        return dataTablePage;
//    }
}
