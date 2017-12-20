package com.visualkhh.api.advice;

import com.visualkhh.common.code.Code;
import com.visualkhh.common.exception.ErrorException;
import com.visualkhh.common.model.error.Error;
import com.visualkhh.common.model.error.FieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE) @Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired MessageSourceAccessor msgSource;
    @Value("${project.properties.header-name}")
    private String headerName = null;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log(ex,request);
        return new ResponseEntity<>(getError(Code.E10002, ex.getBindingResult()), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log(ex,request);
        return new ResponseEntity<>(getError(Code.E10002, ex), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log(ex,request);
        Error e = new Error();
        e.setCode(Code.E10004.name());
        e.setMessage(msgSource.getMessage(Code.E10004.value()));
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log(ex,request);
        Error e = new Error();
        e.setCode(Code.E99999.name());
        e.setMessage(ex.getMessage());
        e.setData(body);
        return new ResponseEntity<>(e, status);
    }

    @ExceptionHandler(ErrorException.class)
    protected ResponseEntity<Object> errorException(ErrorException ex, WebRequest webRequest) {
        log(ex,webRequest);
        Error error = ex.getError();
        if(null!=error){
            if(StringUtils.isEmpty(error.getCode())){
                error.setCode(Code.E99999.name());
            }
            if(StringUtils.isEmpty(error.getMessage())){
                String msg=null;
                try{
                    Code code = Code.valueOf(error.getCode());
                    if(null!=code) {
                        msg = msgSource.getMessage(code.value());
                    }else{
                        msg = ex.getMessage();
                    }
                }catch (Exception e){
                    msg = ex.getMessage();
                }
                error.setMessage(msg);
            }
        }else{
            error = new Error();
            error.setCode(Code.E99999.name());
            if(StringUtils.isEmpty(ex.getMessage())){
                error.setMessage(msgSource.getMessage(Code.E99999.value()));
            }else {
                error.setMessage(ex.getMessage());
            }
        }
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> sqlException(SQLException ex, WebRequest webRequest) {
        log(ex,webRequest);
        Error e = new Error();
        e.setCode(Code.E30000.name());
        e.setMessage(msgSource.getMessage(Code.E30000.value()));
	    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> throwable(Throwable ex, WebRequest webRequest) {
        log(ex,webRequest);
        Error e = new Error();
        e.setCode(Code.E99999.name());
        e.setMessage(Optional.ofNullable(ex.getMessage()).orElseGet(()->ex.toString()));
	    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private Error getError(Code code, BindingResult bindingResult) {
        Error e = new Error();
        e.setCode(code.name());
        e.setMessage(msgSource.getMessage(code.value()));
        for (ObjectError o : bindingResult.getAllErrors()) {
            if(org.springframework.validation.FieldError.class.isAssignableFrom(o.getClass())){
                org.springframework.validation.FieldError originError = (org.springframework.validation.FieldError)o;
                FieldError fe = new FieldError();
                fe.setCode(o.getCode()); fe.setMessage(o.getDefaultMessage()); fe.setField(originError.getField());
                e.addError(fe);
            }else{
                Error e2 = new Error();
                e2.setCode(o.getCode());
                e2.setMessage(o.getDefaultMessage());
                e.addError(e2);
            }
        }
        return e;
    }

    private void log(Throwable ex, WebRequest webRequest) {
        StackTraceElement[] stacks = ex.getStackTrace();
        ServletWebRequest sw = (ServletWebRequest)webRequest;
        HttpServletRequest request = sw.getRequest();
        log.error(ex.getMessage()+"|"+request.getHeader(headerName)+"|"+request.getRequestURI(), ex);
    }

}