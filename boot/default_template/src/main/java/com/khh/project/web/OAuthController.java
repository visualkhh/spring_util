package com.khh.project.web;


import com.khh.project.service.oauth.OAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(OAuthController.PATH_ROOT)
@Slf4j
public class OAuthController {
	public static final String PATH_ROOT = "/oauth";

	@Autowired
	OAuthService service;
	@RequestMapping("/error")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "error/default";
	}

}
