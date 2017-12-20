//package com.khh.project.web;
//
//import com.khh.project.config.properties.ProjectProperties;
//import com.khh.project.service.board.repository.BoardRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//@RequestMapping(WebController.PATH_ROOT)
//@Slf4j
//public class WebController {
//    public static final String PATH_ROOT = "/";
//
//	@Autowired
//	private ProjectProperties projectProperty;
//    @Autowired
//	BoardRepository boardRepository;
//
//
//	@RequestMapping({"", PATH_ROOT})
//	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
//		log.debug(projectProperty.toString());
//        return "index";
//    }
//}
