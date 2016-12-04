package com.khh.project;

import com.khh.project.web.error.ErrorController;
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

@org.springframework.web.bind.annotation.ControllerAdvice("com.khh.project")
@Slf4j
public class ControllerAdvice {


	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, EntityNotFoundException exception){
		log.debug("Entity Not Found Exception {}",exception.getMessage());
		log.trace(exception.getMessage(),exception);

		ModelAndView mav = new ModelAndView();
		response.setHeader("x-status", "Exception");
		mav.addObject("throwable", exception);
		mav.setViewName(ErrorController.ERROR_DEFAULT);
		return mav;
	}


	@ExceptionHandler({UsernameNotFoundException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, UsernameNotFoundException exception){
		log.debug("Username not found {}",exception.getLocalizedMessage());
		log.trace(exception.getMessage(),exception);
		ModelAndView mav = new ModelAndView();
		response.setHeader("x-status", "Exception");
		mav.addObject("throwable", exception);
		mav.setViewName(ErrorController.ERROR_DEFAULT);
		return mav;
	}
}
