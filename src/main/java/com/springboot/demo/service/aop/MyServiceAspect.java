package com.springboot.demo.service.aop;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyServiceAspect {

	@Before("execution(public * com.springboot.demo.service..*.*(..))")
	public void before(JoinPoint joinPoint) {
		System.err.println(" -- MyServiceAspect: before -- ");
	}
	
	@After("execution(public * com.springboot.demo.service..*.*(..))")
	public void after() {
		System.err.println(" -- MyServiceAspect: after -- ");
	}
	
	@Around("execution(* com.springboot.demo.service..*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			System.err.println(" -- MyServiceAspect: around - start -- ");
			Object obj = joinPoint.proceed();
			System.err.println(" -- MyServiceAspect: around - end -- : " + obj);
			return obj;
		} catch (Throwable e) {
			throw e;
		} finally {
			System.err.println(" -- MyServiceAspect: around - finally -- ");
		}
	}
	
	@AfterReturning(pointcut = "@annotation(com.springboot.demo.util.MyAspectAnnotation) && execution(* com.springboot.demo.service..*.*(..))", 
			returning = "result")
	public void afterReturing(JoinPoint joinPoint, Object result) {
		System.err.println(" -- MyServiceAspect: afterReturing -- " + (Objects.isNull(result) ? "NULL" : result.toString()));
	}
	
	@AfterThrowing(pointcut = "@annotation(com.springboot.demo.util.MyAspectAnnotation) && execution(* com.springboot.demo.service..*.*(..))", 
			throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
		System.err.println(" -- MyServiceAspect: afterThrowing -- " + exception.getClass().getCanonicalName());
	}
}
