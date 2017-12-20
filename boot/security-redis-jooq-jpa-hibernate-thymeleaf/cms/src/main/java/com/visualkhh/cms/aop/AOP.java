package com.visualkhh.cms.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Aspect @Component @Order @Slf4j
public class AOP {


	@Before("within(com.visualkhh.cms.controller..*)")
	public void checkHeaderBefore(JoinPoint joinPoint) throws IOException {
		log.debug("AOP");
		List<String> list =null;
		Optional.ofNullable(list).ifPresent(it->it.stream().forEach(sub-> System.out.printf(sub)));

	}

}
