package com.genome.dx.wcore.controller;

import com.genome.dx.core.domain.Term;
import com.genome.dx.core.model.phenoType.PhenoTypeTreeForBootstrapTreeView;
import com.genome.dx.wcore.service.MetasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(WebCoreMetasController.URI_PREFIX)
public class WebCoreMetasController {
    public static final String URI_PREFIX = "/metas";

    @Autowired
    MetasService metasService;

    @GetMapping(value = {"", "/"})
    public String metas() {
        return "meats";
    }


    @GetMapping(value = "/terms")
    public Map<Long, Term> terms(@RequestParam(value = "ids", required = false) Collection<Long> ids) {
        if (null == ids || ids.size() <= 0 ) {
            return metasService.findTermAll();
        } else {
            return metasService.findTermAllByIds(ids);
        }
    }

    @GetMapping("/phenotypes")
    public List<PhenoTypeTreeForBootstrapTreeView> phenotypes(@RequestParam(value = "type") String type) {
        if ("bootstrap-treeview".equals(type)) {
            return metasService.findPhenoTypeForBootStrapTreeView();
        } else {
            return null;
        }
    }

}
