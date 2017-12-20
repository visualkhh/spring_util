package com.khh.project;

import com.khh.Application;
import com.omnicns.java.callstack.StackTraceUtil;
import com.omnicns.web.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@org.springframework.web.bind.annotation.ControllerAdvice(Application.BASE_PACKAGES)
@Slf4j
public class ControllerAdvice {


	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, EntityNotFoundException exception){
		log(request,response,exception);
		//log.trace(exception.getMessage(),exception);
		ModelAndView mav = new ModelAndView();
		response.setHeader("x-status", "Exception");
		mav.addObject("throwable", exception);
		mav.setViewName("error/default");
		return mav;
	}


	@ExceptionHandler({UsernameNotFoundException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, UsernameNotFoundException exception){
		log(request,response,exception);
		//log.trace(exception.getMessage(),exception);
		ModelAndView mav = new ModelAndView();
		response.setHeader("x-status", "Exception");
		mav.addObject("throwable", exception);
		mav.setViewName("error/default");
		return mav;
	}



	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Throwable exception){
		log(request,response,exception);
		//log.trace(exception.getMessage(),exception);
		ModelAndView mav = new ModelAndView();
		response.setHeader("x-status", "Exception");
		mav.addObject("throwable", exception);
		mav.setViewName("error/default");
		return mav;
	}

	private void log(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
		Throwable tex = StackTraceUtil.getLastCause(ex);
		StackTraceElement[] stacks = tex.getStackTrace();

		StringBuffer logInfo = new StringBuffer();
		logInfo.append(ex.getClass().getName());
		logInfo.append("|").append(request.getRequestURI());//API CODE(URL)
		logInfo.append("|").append(RequestUtil.getRemoteAddr(request));	// 접속IP
		logInfo.append("|").append(null==ex.getMessage()?"":ex.getMessage().replace("\n","").replace("\r", ""));	// 처리 담당자 ID
		logInfo.append("|").append(Stream.of(stacks).map(at->at.toString()).collect(Collectors.joining("<<")));
		log.error(logInfo.toString().replace("\n", "").replace("\r",""));
	}
}
