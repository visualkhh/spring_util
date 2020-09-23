package com.genome.dx.wcore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.MsgCode;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Brd;
import com.genome.dx.core.domain.base.BrdBase;
import com.genome.dx.core.exception.ErrorMsgException;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.core.repository.BrdRepository;
import com.genome.dx.wcore.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(WebCoreBrdsController.URI_PREFIX)
public class WebCoreBrdsController {
    public static final String URI_PREFIX = "/brds";

    @Autowired
    SecurityService securityService;

    @Autowired
    BrdRepository brdRepository;

    @GetMapping(value="/notices")
    @JsonView({JsonViewFrontEnd.class})
    public Page<Brd> notice(
            @PageableDefault(sort = { "regDt" }, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(name = "search", defaultValue = "") String titl) {
        Page<Brd> datas = brdRepository.findByTitlContainsAndCateCdAndUseCdOrderByBrdSeqDesc(titl, BrdCateCd.BCC001, UseCd.USE001, pageable);
        return datas;
    }

    @GetMapping("/notices/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd notice(@PathVariable(name = "brdSeq") Long brdSeq) {
        return brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
    }

    @DeleteMapping(value="/notices/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd deleteNotice(@PathVariable(name = "brdSeq") Long brdSeq) {
        Brd data = brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
        if (data.getAdmSeq().equals(securityService.getUserDetils().getAdmSeq())) {
            data.setUseCd(UseCd.USE002);
            return brdRepository.save(data);
        } else {
            throw new ErrorMsgException(MsgCode.E10104);
        }
    }

    @PutMapping(value="/notices/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd putNotice(@PathVariable(name = "brdSeq") Long brdSeq, @RequestBody BrdBase brd) {
        Brd data = brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
        if (data.getAdmSeq().equals(securityService.getUserDetils().getAdmSeq())) {
            data.setTitl(brd.getTitl());
            data.setCont(brd.getCont());
            return brdRepository.save(data);
        } else {
            throw new ErrorMsgException(MsgCode.E10104);
        }
    }

    @PostMapping(value="/notices")
    @JsonView({JsonViewFrontEnd.class})
    public Brd postNotice(@RequestBody BrdBase brd) {
        brd.setAdmSeq(securityService.getUserDetils().getAdmSeq());
        brd.setCateCd(BrdCateCd.BCC001);
        brd.setUseCd(UseCd.USE001);
        return brdRepository.save(brd.map(Brd.class));
    }

    @GetMapping("/qas")
    @JsonView({JsonViewFrontEnd.class})
    public Page<Brd> qa(
            @PageableDefault(sort = { "regDt" }, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(name = "search", defaultValue = "") String titl) {
        Page<Brd> datas = brdRepository.findByTitlContainsAndCateCdAndUseCdOrderByBrdSeqDesc(titl, BrdCateCd.BCC004, UseCd.USE001, pageable);
        return datas;
    }

    @GetMapping("/qas/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd qa(@PathVariable(name = "brdSeq") Long brdSeq) {
        return brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
    }

    @DeleteMapping(value="/qas/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd deleteQA(@PathVariable(name = "brdSeq") Long brdSeq) {
        Brd data = brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
        if (data.getAdmSeq().equals(securityService.getUserDetils().getAdmSeq())) {
            data.setUseCd(UseCd.USE002);
            return brdRepository.save(data);
        } else {
            throw new ErrorMsgException(MsgCode.E10104);
        }
    }

    @PutMapping(value="/qas/{brdSeq}")
    @JsonView({JsonViewFrontEnd.class})
    public Brd putQA(@PathVariable(name = "brdSeq") Long brdSeq, @RequestBody BrdBase brd) {
        Brd data = brdRepository.findById(brdSeq).orElseThrow(() -> new ErrorMsgException(MsgCode.E10003));
        if (data.getAdmSeq().equals(securityService.getUserDetils().getAdmSeq())) {
            data.setTitl(brd.getTitl());
            data.setCont(brd.getCont());
            return brdRepository.save(data);
        } else {
            throw new ErrorMsgException(MsgCode.E10104);
        }
    }

    @PostMapping(value="/qas")
    @JsonView({JsonViewFrontEnd.class})
    public Brd postGas(@RequestBody BrdBase brd) {
        brd.setAdmSeq(securityService.getUserDetils().getAdmSeq());
        brd.setCateCd(BrdCateCd.BCC004);
        brd.setUseCd(UseCd.USE001);
        return brdRepository.save(brd.map(Brd.class));
    }

}
