package com.khh.project.web;

import com.khh.project.service.error.ErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Slf4j
@RequestMapping(ErrorController.PATH_ROOT)
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	public static final String PATH_ROOT 			= "/error";
	public static final String PATH_ERROR_DEFAULT 	= "/default";
	public static final String PATH_ERROR_401 		= "/401";
	public static final String PATH_ERROR_403 		= "/403";
	public static final String PATH_ERROR_404 		= "/404";
	public static final String PATH_ERROR_500 		= "/500";

	@Autowired
	ErrorService service;


	@RequestMapping(value = PATH_ERROR_DEFAULT, method = GET)
	public String defaultError() {
		return "error/default";
	}

	@RequestMapping(value = PATH_ERROR_401, method = GET)
	public String error401() {
		return "error/401";
	}

	@RequestMapping(value = PATH_ERROR_403, method = GET)
	public String error403() {
		return "error/403";
	}

	@RequestMapping(value = PATH_ERROR_404, method = GET)
	public String error404() {
		return "error/404";
	}

	@RequestMapping(value = PATH_ERROR_500, method = GET)
	public String error500() {
		return "error/500";
	}

	@Override
	public String getErrorPath() {
		return "error/default";
	}
}
