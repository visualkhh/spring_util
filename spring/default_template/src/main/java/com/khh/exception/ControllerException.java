package com.khh.exception;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khh.boot.vo.BootCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.omnicns.java.callstack.StackTraceUtil;
import com.omnicns.java.convert.ConvertUtil;
import com.omnicns.java.gson.GsonUtil;
import com.omnicns.web.request.RequestUtil;
import com.omnicns.web.spring.exception.BodyErrorsException;
import com.omnicns.web.spring.exception.CustomErrorsException;
import com.omnicns.web.spring.exception.JsonErrorsException;
import com.omnicns.web.spring.exception.JspErrorsException;



@ControllerAdvice("com.omnicns")
public class ControllerException {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	MessageSourceAccessor msgAccessor;
	
	public ControllerException() {
		log.debug("ControllerException new");
	}
	
	
	@ExceptionHandler(CustomErrorsException.class)
	public ResponseEntity<?> customErrosexception(HttpServletRequest req, HttpServletResponse res, CustomErrorsException ex){
		log(req, res, ex.getErrorCode(), ex);
		/////////custom
		ex.write(req, res);
		ResponseEntity<String> responseentity = new ResponseEntity<String>(ex.getHeaders(), ex.getHttpStatus());
//		responseentity.status(ex.getHttpStatus());
		return responseentity;
	}
	@ExceptionHandler(BodyErrorsException.class)
	public ResponseEntity<?> bodyErrosexception(HttpServletRequest req, HttpServletResponse res, BodyErrorsException ex){
		log(req, res, ex.getErrorCode(), ex);
		/////////custom
		//ex.write(request, response);
		ResponseEntity<String> responseentity = new ResponseEntity<String>(ex.read(req, res), ex.getHeaders(), ex.getHttpStatus());
//		responseentity.status(ex.getHttpStatus());
		return responseentity;
	}
	
	
	@ExceptionHandler(JsonErrorsException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<?>  errosexception(HttpServletRequest request, HttpServletResponse response, JsonErrorsException ex){
		log(request, response, ex.getErrorCode(), ex);
//		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//		response.setCharacterEncoding("UTF-8");
//		model.addAttribute("error_code", ex.getErrorCode());
//		model.addAttribute("error_msg", ex.getMessage());
//		model.addAttribute("errors", ex.getErrors());
//		model.addAttribute("throwable", ex);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		ResponseEntity<String> responseentity = new ResponseEntity<String>(new Json(ex.getErrorCode(), ex.getErrors()).toJson(), headers, HttpStatus.OK);
		
		/////////json
		HashMap<String, Object> error = new HashMap<String, Object>();
		error.put("code", null!=ex.getErrorCode()&&ex.getErrorCode().length()>0?ex.getErrorCode():"999999");
		error.put("msg", null!=ex.getMessage()&&ex.getMessage().length()>0?ex.getMessage():"오류가 발생되었습니다.");
		
		
		if(null!=ex.getErrors()){
			HashMap<String, String> newBody = new HashMap<String, String>();
			List<ObjectError> errors = ex.getErrors().getAllErrors();
			for (int i = 0; i < errors.size(); i++) {
				ObjectError atError = errors.get(i);
				String msg = atError.getDefaultMessage();
				try{
					msg = msgAccessor.getMessage(atError.getCode());
				}catch(NoSuchMessageException e){log.debug("msgAccessor.getMessage",e);}
				String id = atError.getObjectName();
				if (atError instanceof FieldError){
	                FieldError fieldError = (FieldError)atError; 
	                id = fieldError.getField();
				}else{
					id = (null==atError.getCode()?atError.getObjectName():atError.getCode());
				}
				newBody.put(id, msg);
			}
			error.put("errors",newBody);
		}
		ResponseEntity<String> responseentity = new ResponseEntity<String>(GsonUtil.toJsonExpose(error), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseentity;
	}
	
	

	@ExceptionHandler(JspErrorsException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handlerJspException(HttpServletRequest request, HttpServletResponse response, JspErrorsException ex){
		log(request, response, ex.getErrorCode(), ex);
		
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("error_code", null!=ex.getErrorCode()&&ex.getErrorCode().length()>0?ex.getErrorCode():"999999");
		mav.addObject("error_msg", null!=ex.getMessage()&&ex.getMessage().length()>0?ex.getMessage():"오류가 발생되었습니다.");
		mav.addObject("errors", ex.getErrors());
		mav.addObject("throwable", ex);
		mav.setViewName("/error/error");
//		mav.setViewName("/error-body");
//		Errors errors = ex.getErrors();
//		errors.getAllErrors();
		return mav;
	}
	
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public ModelAndView handlerRuntimeException(HttpServletRequest request, HttpServletResponse response, RuntimeException ex){
//		return handlerException(request,response,ex);
//	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex){
		log(request, response, "999999", ex);
		
		
		
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("error_code", "999999");      
		if(AccessDeniedException.class.isAssignableFrom(ex.getClass())){
			mav.addObject("error_msg", "자동 로그아웃 또는 엑세스 권한이 없습니다.");      
		}else if(IllegalArgumentException.class.isAssignableFrom(ex.getClass()) || MethodArgumentTypeMismatchException.class.isAssignableFrom(ex.getClass())){
			mav.addObject("error_msg", "파라미터가 잘못되었습니다.");      
		}else{
			mav.addObject("error_msg", ex.getMessage());      
		}
//		mav.addObject("errors", ex.getErrors());          
		mav.addObject("throwable", ex);                   
		mav.setViewName("/error/error");
		return mav;
	}


	
	
	private void log(HttpServletRequest request, HttpServletResponse response, String rcd, Exception ex) {
		Throwable tex = StackTraceUtil.getLastCause(ex);
		StackTraceElement[] stacks = tex.getStackTrace();
		String trace = ConvertUtil.join(stacks," < ");
		
		StringBuffer logInfo = new StringBuffer();
		logInfo.append(ex.getClass().getName());
		logInfo.append("|").append(request.getRequestURI());//API CODE(URL)
		logInfo.append("|").append(rcd);
//		BootCodeVO codevo = BootManager.getInstance().getCode(rcd);
		BootCodeVO codevo =null;
		logInfo.append("|").append( (codevo==null?"알수없음":codevo.getCd_nm()) );
		logInfo.append("|").append(RequestUtil.getRemoteAddr(request));	// 접속IP
		logInfo.append("|").append(null==ex.getMessage()?"":ex.getMessage().replace("\n","").replace("\r", ""));	// 처리 담당자 ID
		logInfo.append("|").append(trace);
		log.error(logInfo.toString().replace("\n", "").replace("\r",""));
	}
	
	
	
	
//	protected ResponseEntity<String> errorResponse(Throwable throwable, HttpStatus status) {
//		if (null != throwable) {
//			return response(new Json(JsonHead.CODE_ERROR,throwable.getMessage()).toJson(), status);
//		} else {
//			return response(null, status);
//		}
//	}

	protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
		return new ResponseEntity<T>(body, new HttpHeaders(), status);
	}
}
