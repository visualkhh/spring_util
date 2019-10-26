package com.omnicns.medicine.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Aspect @Component @Order @Slf4j
public class MedicineAOP {

	@Before("within(com.omnicns.medicine.controller..*)")
	public void before(JoinPoint joinPoint) throws IOException {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		log.debug("AOP "+request);
	}


//	@Around("execution(* com.omnicns.omnifit2.api.controller.FitBrainController.putHistory(..))")
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
