package com.clone.chat.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
@Order
@Slf4j
public class ChatAOP {

    @Before("within(com.clone.chat.controller.api..*)")
    public void before(JoinPoint joinPoint) throws IOException {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		log.debug("AOP "+request);

    }

    @After("within(com.clone.chat.controller.api..*)")
    public void after(JoinPoint joinPoint) throws IOException {
    }


}
