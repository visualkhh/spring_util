package com.omnicns.medicine.controller;

import com.omnicns.medicine.code.BrdCateCd;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.Brd;
import com.omnicns.medicine.domain.base.BrdBase;
import com.omnicns.medicine.model.DataTablePageResponse;
import com.omnicns.medicine.repository.BrdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(BrdController.URI_PREFIX)
public class BrdController {
    public static final String URI_PREFIX = "/brd";

    @Autowired
    private BrdRepository brdRepository;

    @GetMapping(value="/notice")
    public DataTablePageResponse getNotice(
            @PageableDefault(sort = { "regDt" }, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(name = "search") String titl) {
        return new DataTablePageResponse(brdRepository.findByTitlContainsAndCateCd(titl, BrdCateCd.BCC001, pageable));
    }

    @GetMapping(value="/help")
    public DataTablePageResponse getHelp(@PageableDefault(sort = { "regDt" }, direction = Direction.DESC) Pageable pageable, @RequestParam(name = "search") String titl) {
        return new DataTablePageResponse(brdRepository.findByTitlContainsAndCateCd(titl, BrdCateCd.BCC002, pageable));
    }


    @PostMapping(value={"/notice", "/help"})
    public void save(@RequestBody Brd brd) {
        brd.setUseCd(UseCd.USE001);
        brdRepository.save(brd);
    }

    @PutMapping(value={"/notice", "/help"})
    public void update(@RequestBody BrdBase brd) {
        brdRepository.save(brd.map(Brd.class));
    }

    @DeleteMapping(value={"/notice", "/help"})
    public void brdDelete(@RequestParam(name = "brdSeq") Integer brdSeq) {
        brdRepository.delete(brdSeq);
    }

}
