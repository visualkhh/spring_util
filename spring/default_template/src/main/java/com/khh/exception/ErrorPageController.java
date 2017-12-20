package com.khh.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/error")
public class ErrorPageController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/throwable")
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String throwable(HttpServletRequest request, Model model) {
		log.info("throwable");
		pageErrorLog(request);
		model.addAttribute("error_msg", "예외가 발생하였습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/exception")
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(HttpServletRequest request, Model model) {
		log.info("exception");
		pageErrorLog(request);
		model.addAttribute("error_msg", "예외가 발생하였습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/400")
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String pageError400(HttpServletRequest request, Model model) {
		log.info("page error code 400");
		pageErrorLog(request);
		model.addAttribute("error_msg", "잘못된 요청입니다.");
		return "error/error";
	}

	@RequestMapping(value = "/403")
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String pageError403(HttpServletRequest request, Model model) {
		log.info("page error code 403");
		pageErrorLog(request);
		model.addAttribute("error_msg", "접근이 금지되었습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/404")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String pageError404(HttpServletRequest request, Model model) {
		log.info("page error code 404");
		pageErrorLog(request);
		model.addAttribute("error_msg", "요청하신 페이지는 존재하지 않습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/405")
	public String pageError405(HttpServletRequest request, Model model) {
		log.info("page error code 405");
		pageErrorLog(request);
		model.addAttribute("msg", "요청된 메소드가 허용되지 않습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/500")
	public String pageError500(HttpServletRequest request, Model model) {
		log.info("page error code 500");
		pageErrorLog(request);
		model.addAttribute("msg", "서버에 오류가 발생하였습니다.");
		return "error/error";
	}

	@RequestMapping(value = "/503")
	public String pageError503(HttpServletRequest request, Model model) {
		log.info("page error code 503");
		pageErrorLog(request);
		model.addAttribute("msg", "서비스를 사용할 수 없습니다.");
		return "error/error";
	}

	private void pageErrorLog(HttpServletRequest request) {
		Integer error_code = (Integer) request.getAttribute("javax.servlet.error.status_code");
		log.info("status_code : "+ error_code);
		log.info("exception_type : "+ request.getAttribute("javax.servlet.error.exception_type"));
		log.info("message : "+ request.getAttribute("javax.servlet.error.message"));
		log.info("request_uri : "+ request.getAttribute("javax.servlet.error.request_uri"));
		log.info("exception : "+ request.getAttribute("javax.servlet.error.exception"));
		log.info("servlet_name : "+ request.getAttribute("javax.servlet.error.servlet_name"));
		
		request.setAttribute("error_code", error_code);
		request.setAttribute("errors", request.getAttribute("javax.servlet.error.exception"));
		
	}
}