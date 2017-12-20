package com.visualkhh.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Aspect @Component @Order @Slf4j
public class CheckHeaderAOP {

	public static @interface DisableCheckHeader {
	}

	@Value("${project.properties.header-name}")
	private String headerName;


	@Before("within(com.visualkhh.api.controller..*) && !@annotation(com.visualkhh.api.aop.CheckHeaderAOP.DisableCheckHeader)")
	public void checkHeaderBefore(JoinPoint joinPoint) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.debug("AOP "+request);
	}

//	@Around("execution(* FitBrainController.putHistory(..))")
//	public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable
//	{
//		System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
//		try {
//			joinPoint.proceed();
//		} catch (Exception ex) {
//			log.debug(ex.getMessage());
//		} finally {
//			//Do Something useful, If you have
//		}
//		System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": After Method Execution");
//	}
}
