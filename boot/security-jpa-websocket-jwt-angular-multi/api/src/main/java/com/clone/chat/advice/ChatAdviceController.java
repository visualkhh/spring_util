package com.clone.chat.advice;

import com.clone.chat.code.MsgCode;
import com.clone.chat.exception.BusinessException;
import com.clone.chat.model.error.Error;
import com.clone.chat.model.error.FieldError;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ChatAdviceController extends ResponseEntityExceptionHandler {

//    @Autowired
//    MessageSourceAccessor msgSource;

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> usename(Throwable ex, WebRequest webRequest) {
        logingAndSetMsg(ex, webRequest);
        Error e = new Error();
        e.setCode(MsgCode.ERROR_AUTH.name());
        e.setMessage(Optional.ofNullable(ex.getMessage()).orElseGet(ex::toString));
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> noSuchElementException(Throwable ex, WebRequest webRequest) {
        logingAndSetMsg(ex, webRequest);
        Error e = new Error();
        e.setCode(MsgCode.ERROR_NOSUCHELEMENTEXCEPTION.name());
        e.setMessage(Optional.ofNullable(ex.getMessage()).orElseGet(ex::toString));
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logingAndSetMsg(ex, request);
        return new ResponseEntity<>(getError(MsgCode.ERROR_BIND, ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> throwable(Throwable ex, WebRequest webRequest) {
        logingAndSetMsg(ex, webRequest);
        Error e = new Error();
        e.setCode(((BusinessException) ex).getCode().toString());
        e.setMessage(Optional.ofNullable(ex.getMessage()).orElseGet(ex::toString));
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }


    @MessageExceptionHandler
    @SendToUser(value = "/queue/errors", broadcast = false)
    public Error handleException(Throwable ex) {
        logingAndSetMsg(ex);
        Error e = new Error();
        e.setCode(MsgCode.ERROR_UNKNOWN.name());
        e.setMessage(Optional.ofNullable(ex.getMessage()).orElseGet(ex::toString));
        return e;
    }


    private Error getError(MsgCode code, BindingResult bindingResult) {
        Error e = new Error();
        e.setCode(code.name());
        e.setMessage(bindingResult.getObjectName() + " bind Exception");
        for (ObjectError o : bindingResult.getAllErrors()) {
            if (org.springframework.validation.FieldError.class.isAssignableFrom(o.getClass())) {
                org.springframework.validation.FieldError originError = (org.springframework.validation.FieldError) o;
                FieldError fe = new FieldError();
                fe.setCode(o.getCode());
                fe.setMessage(o.getDefaultMessage());
                fe.setField(originError.getField());
                e.addError(fe);
            } else {
                Error e2 = new Error();
                e2.setCode(o.getCode());
                e2.setMessage(o.getDefaultMessage());
                e.addError(e2);
            }
        }
        return e;
    }
    private void logingAndSetMsg(Throwable ex) {
        logingAndSetMsg(ex, null);
    }
    private void logingAndSetMsg(Throwable ex, WebRequest webRequest) {

        String uri = "noUri";
        String param = "noParam";
        if (null != webRequest) {
            ServletWebRequest sw = (ServletWebRequest) webRequest;
            HttpServletRequest request = sw.getRequest();
            param = request.getParameterMap().entrySet().stream().map(it -> it.getKey() + "=" + String.join(", ", Arrays.asList(it.getValue()))).collect(Collectors.joining("&"));
            uri = request.getRequestURI();
        }
        log.error(ex.getMessage() + "|" + uri + "|" + param, ex);
    }
}
