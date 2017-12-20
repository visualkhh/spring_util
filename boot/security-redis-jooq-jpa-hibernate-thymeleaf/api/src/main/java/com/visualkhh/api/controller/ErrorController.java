//package com.visualkhh.api.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(ErrorController.URI_PREFIX)
//public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController{
//
//	public static final String URI_PREFIX 		= "/error";
//
//	public static final String ERROR_DEFAULT    = "/default";
//	public static final String ERROR_400        = "/400";
//	public static final String ERROR_401        = "/401";
//	public static final String ERROR_403        = "/403";
//	public static final String ERROR_404        = "/404";
//	public static final String ERROR_405        = "/405";
//	public static final String ERROR_500        = "/500";
//
//
//
//	@GetMapping(value = ERROR_DEFAULT)
//	public String defaultError() {
//		return "error/default";
//	}
//
//	@GetMapping(value = ERROR_400)
//	public String error400() {
//		return "error/400";
//	}
//	@GetMapping(value = ERROR_401)
//	public String error401() {
//		return "error/401";
//	}
//
//	@GetMapping(value = ERROR_403)
//	public String error403() {
//		return "error/403";
//	}
//
//	@GetMapping(value = ERROR_404)
//	public String error404() {
//		return "error/404";
//	}
//	@GetMapping(value = ERROR_405)
//	public String error405() {
//		return "error/405";
//	}
//
//	@GetMapping(value = ERROR_500)
//	public String error500() {
//		return "error/500";
//	}
//
//	@Override
//	public String getErrorPath() {
//		return ERROR_DEFAULT;
//	}
//}
