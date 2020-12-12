package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AOP {
    @Around("execution(* com.example.demo.DemoApplication.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis(); //전처리
        Object retVal = pjp.proceed(); // 올래 실행될놈.
        log.info("time: --> {}", System.currentTimeMillis() - start); // 후처리.
        return retVal;
    }
}
