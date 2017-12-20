package com.khh.api.resource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AOP {

//	ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
//	HttpServletRequest request = servletContainer.getRequest();
//	HttpServletResponse response = servletContainer.getResponse();

//	[Advice 타입]
//	Before advice: joinpoint전에 수행되는 advice. 하지만 joinpoint를 위한 수행 흐름 처리(execution flow proceeding)를 막기위한 능력(만약 예외를 던지지 않는다면)을 가지지는 않는다.
//	After returning advice: joinpoint이 일반적으로 예를 들어 메소드가 예외를 던지는것없이 반환된다면 완성된 후에 수행되는 advice.
//	After throwing advice: 메소드가 예외를 던져서 빠져나갈때 수행되는 advice
//	After (finally) advice: join point를 빠져나가는(정상적이거나 예외적인 반환) 방법에 상관없이 수행되는 advice.
//	Around advice: 메소드 호출과 같은 joinpoint주위(surround)의 advice. 이것은 가장 강력한 종류의 advice이다. Around advice는 메소드 호출 전후에 사용자 정의 행위를 수행할수 있다. 그것들은 joinpoint를 처리하거나 자기 자신의 반환값을 반환함으로써 짧게 수행하거나 예외를 던지는 것인지에 대해 책임을 진다.

	//joinpoint전에 수행되는 advice. 하지만 joinpoint를 위한 수행 흐름 처리(execution flow proceeding)를 막기위한 능력(만약 예외를 던지지 않는다면)을 가지지는 않는다.
	@Before("execution("+Controller.AOP_EXECUTION+")")
	public void before(JoinPoint joinPoint) {
		log.debug(joinPoint.toString());
	}




	//joinpoint이 일반적으로 예를 들어 메소드가 예외를 던지는것없이 반환된다면 완성된 후에 수행되는 advice.
	@AfterReturning(value = "execution("+Controller.AOP_EXECUTION+")", returning = "retVal")
	public void afterreturning(JoinPoint joinPoint, Object retVal){
		log.debug(retVal.toString());
	}
//
	@AfterThrowing(value = "execution("+Controller.AOP_EXECUTION+")", throwing = "ex")
	public void afterThrowin(Throwable ex){
		log.debug(ex.toString());

	}
	@After("execution("+Controller.AOP_EXECUTION+")")
	public void after(){
		log.debug("after");
	}


	@Around("execution("+Controller.AOP_EXECUTION+")")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug(joinPoint.toString());
		return joinPoint.proceed();
	};

}
