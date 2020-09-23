package com.genome.dx.gmp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.GenderCd;
import com.genome.dx.core.code.ProcStatCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.InputInfo;
import com.genome.dx.core.domain.PtntInfo;
import com.genome.dx.core.domain.PtntPntype;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.core.model.CustomEvidenceScore;
import com.genome.dx.core.model.InputVariantInfoSameCount;
import com.genome.dx.gmp.service.EvidencesService;
import com.genome.dx.gmp.service.PatientsService;
import com.genome.dx.gmp.service.ResultsService;
import com.genome.dx.wcore.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(PatientsController.URI_PREFIX)
public class PatientsController {

    public static final String URI_PREFIX = "/patients";

    @Autowired
    PatientsService patientsService;

    @Autowired
    EvidencesService evidencesService;

    @Autowired
    ResultsService resultsService;

    @Autowired
    SecurityService securityService;

    @Value("${project.properties.upload-path}")
    String uploadPath;

    @GetMapping(value = {"", "/"})
    @JsonView({JsonViewFrontEnd.class})
    public Page<PtntInfo> patients(@PageableDefault(sort = {"regDt"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
                                   @RequestParam(name = "ptntNm", defaultValue = "") String ptntNm,
                                   @RequestParam(name = "gen", required = false) GenderCd gen,
                                   @RequestParam(name = "fromAge", required = false) Long fromAge,
                                   @RequestParam(name = "toAge", required = false) Long toAge,
                                   @RequestParam(name = "hpoProcStatCd", required = false) ProcStatCd hpoProcStatCd,
                                   @RequestParam(name = "mriProcStatCd", required = false) ProcStatCd mriProcStatCd,
                                   @RequestParam(name = "vcfProcStatCd", required = false) ProcStatCd vcfProcStatCd
    ) {
        return patientsService.findAll(ptntNm, gen, fromAge, toAge, UseCd.USE001, hpoProcStatCd, mriProcStatCd, vcfProcStatCd, pageable);
    }

    @PostMapping(value = {"", "/"})
    @JsonView({JsonViewFrontEnd.class})
    public PtntInfo postPatient(
            @RequestParam(value = "ptntNm") String ptntNm,
            @RequestParam(value = "gen") GenderCd gen,
            @RequestParam(value = "age") Long age,
            @RequestParam(value = "hpoProcStatCd") ProcStatCd hpoProcStatCd,
            @RequestParam(value = "ptntPntypes", required = false) Collection<Long> ptntPntypes,
            @RequestPart(value = "mri", required = false) MultipartFile mri,
            @RequestPart(value = "vcf", required = false) MultipartFile vcf
    ) throws IOException {
        return putPatient(null, ptntNm, gen, age, hpoProcStatCd, ptntPntypes, mri, vcf);
    }


    @GetMapping(value = "/{ptntSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public PtntInfo patient(@PathVariable(name = "ptntSeq") Long ptntSeq) {
        return patientsService.findById(ptntSeq);
    }

    @PutMapping(value = "/{ptntSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public PtntInfo putPatient(
            @PathVariable(name = "ptntSeq") Long ptntSeq,
            @RequestParam(value = "ptntNm") String ptntNm,
            @RequestParam(value = "gen") GenderCd gen,
            @RequestParam(value = "age") Long age,
            @RequestParam(value = "hpoProcStatCd") ProcStatCd hpoProcStatCd,
            @RequestParam(value = "ptntPntypes", required = false) Collection<Long> ptntPntypes,
            @RequestPart(value = "mri", required = false) MultipartFile mri,
            @RequestPart(value = "vcf", required = false) MultipartFile vcf
    ) throws IOException {
//        log.debug("---");
        PtntInfo ptntInfo = null == ptntSeq ? new PtntInfo() : patientsService.findById(ptntSeq);
        ptntInfo.setPtntSeq(ptntSeq);
        ptntInfo.setPtntNm(ptntNm);
        ptntInfo.setAge(age);
        ptntInfo.setGen(gen);
        ptntInfo.setUseCd(UseCd.USE001);
        ptntInfo.setHpoProcStatCd(hpoProcStatCd);
        ptntInfo.setRegAdmSeq(securityService.getUserDetils().getAdmSeq());
        ptntInfo.setHpoProcStatCd(Optional.ofNullable(ptntInfo.getHpoProcStatCd()).orElse(ProcStatCd.FST001));
        ptntInfo.setMriProcStatCd(Optional.ofNullable(ptntInfo.getMriProcStatCd()).orElse(ProcStatCd.FST001));
        ptntInfo.setVcfProcStatCd(Optional.ofNullable(ptntInfo.getVcfProcStatCd()).orElse(ProcStatCd.FST001));
        ptntInfo = patientsService.savePtntInfo(ptntInfo);
        // file save
        if (null!=mri && !mri.isEmpty()) {
            File dest = new File(uploadPath + "/mri-" + ptntInfo.getPtntSeq() + "." + FilenameUtils.getExtension(mri.getOriginalFilename()));
            ptntInfo.setMriFilePath(dest.getAbsolutePath());
            mri.transferTo(dest);
            ptntInfo.setMriProcStatCd(ProcStatCd.FST002);
        }
        if (null!=vcf && !vcf.isEmpty()) {
            File dest = new File(uploadPath + "/vcf-" + ptntInfo.getPtntSeq() + "." + FilenameUtils.getExtension(vcf.getOriginalFilename()));
            ptntInfo.setVcfFilePath(dest.getAbsolutePath());
            vcf.transferTo(dest);
            ptntInfo.setVcfProcStatCd(ProcStatCd.FST002);
        }
        ptntPntypes = Optional.ofNullable(ptntPntypes).orElse(Collections.EMPTY_LIST);
        patientsService.deletePtntPntypeByPtntSeq(ptntInfo.getPtntSeq());
        patientsService.savePtntInfo(ptntInfo);
        Long ptntInfoSeq = ptntInfo.getPtntSeq();
        List<PtntPntype> dbPtntPntypes = ptntPntypes.stream().map(it -> PtntPntype.builder().ptntSeq(ptntInfoSeq).termId(it.longValue()).build()).collect(Collectors.toList());
        patientsService.saveAllPtnPnTypes(dbPtntPntypes);
        return ptntInfo;
    }

    @DeleteMapping(value = "/{ptntSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public PtntInfo putPatient(@PathVariable(name = "ptntSeq") Long ptntSeq) throws IOException {
        PtntInfo ptntInfo = patientsService.findById(ptntSeq);
        ptntInfo.setUseCd(UseCd.USE002);
        return patientsService.savePtntInfo(ptntInfo);
    }

    @GetMapping(value = "/{ptntSeq}/files")
    @JsonView({JsonViewFrontEnd.class})
    public String patientFiles(@PathVariable(name = "ptntSeq") Long ptntSeq) {
        return "files";
    }

    @GetMapping(value = "/{ptntSeq}/files/mri")
    @JsonView({JsonViewFrontEnd.class})
    public FileSystemResource patientFilesMri(@PathVariable(name = "ptntSeq") Long ptntSeq, HttpServletResponse response) throws IOException {
        FileSystemResource file = new FileSystemResource(patientsService.findById(ptntSeq).getMriFilePath());
        String contentType = Files.probeContentType(file.getFile().toPath());
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
        response.setHeader("Content-Length", String.valueOf(file.contentLength()));
        return file;
    }

    @GetMapping(value = "/{ptntSeq}/files/vcf")
    @JsonView({JsonViewFrontEnd.class})
    public FileSystemResource patientFilesVcf(@PathVariable(name = "ptntSeq") Long ptntSeq, HttpServletResponse response) throws IOException {
        FileSystemResource file = new FileSystemResource(patientsService.findById(ptntSeq).getVcfFilePath());
        String contentType = Files.probeContentType(file.getFile().toPath());
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
        response.setHeader("Content-Length", String.valueOf(file.contentLength()));
        return file;
    }

    @GetMapping(value = "/{ptntSeq}/evidences")
    @JsonView({JsonViewFrontEnd.class})
    public List<CustomEvidenceScore> patientEvidences(@PathVariable(name = "ptntSeq") Long ptntSeq,
                                                      @RequestParam(name = "top", defaultValue = "1") Long top,
                                                      @RequestParam(name = "phenoType", defaultValue = "false") boolean phenoType,
                                                      @RequestParam(name = "vcf", defaultValue = "false") boolean vcf) {
        return evidencesService.findAll(ptntSeq, top, phenoType, vcf);
    }

    @GetMapping(value = "/{ptntSeq}/results")
    @JsonView({JsonViewFrontEnd.class})
    public String results(@PathVariable(name = "ptntSeq") Long ptntSeq) {
        return "patients Result " + ptntSeq;
    }

    @GetMapping(value = "/{ptntSeq}/results/inputs")
    @JsonView({JsonViewFrontEnd.class})
    public InputInfo resultInputs(@PathVariable(name = "ptntSeq") Long ptntSeq) {
        return resultsService.findTopByPtntSeqOrderByInputPidDesc(ptntSeq);
    }

    @GetMapping(value = "/{ptntSeq}/results/variants")
    @JsonView({JsonViewFrontEnd.class})
    public String resultVariants(@PathVariable(name = "ptntSeq") Long ptntSeq) {
        return "variants";
    }

    @GetMapping(value = "/{ptntSeq}/results/variants/{evidenceId}")
    @JsonView({JsonViewFrontEnd.class})
    public List<InputVariantInfoSameCount> resultVariantDetails(@PathVariable(name = "ptntSeq") Long ptntSeq, @PathVariable(name = "evidenceId") Long evidenceId) {
        return resultsService.variants(ptntSeq, evidenceId);
    }
//
//    @GetMapping(value="/{ptntSeq}/evidences/{caseId}")
//    @JsonView({JsonViewFrontEnd.class})
//    public List<Evidence> evidences(@PathVariable(name = "ptntSeq") Long ptntSeq,
//                                           @RequestParam(name = "top", defaultValue = "1") Long top,
//                                           @RequestParam(name = "phenoType", defaultValue = "false") boolean phenoType,
//                                           @RequestParam(name = "vcf", defaultValue = "false") boolean vcf) {
//        return  evidencesService.findAll(ptntSeq, top, phenoType, vcf);
//    }

}
