package com.khh.project.api;

import com.khh.project.config.properties.ProjectProperties;
import com.khh.project.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ApiController.PATH_ROOT)
@Slf4j
public class ApiController {
    public static final String PATH_ROOT = "/api";

	@Autowired
	private ProjectProperties projectProperty;
    @Autowired
	BoardService service;


	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping({"", PATH_ROOT})
	public Map index(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.debug(projectProperty.toString());
		Map val = new HashMap<>();
		val.put("a","a_val");
		val.put("b","b_val");
        return val;
    }
}
