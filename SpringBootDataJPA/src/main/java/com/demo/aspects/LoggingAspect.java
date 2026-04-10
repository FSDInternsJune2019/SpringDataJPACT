package com.demo.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	@Before(value = "execution (* com.demo.controller.EmployeesControllerImpl.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName=joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		log.info(">>> START: {}() | Args: {}", methodName, Arrays.toString(args));
	}
	@After(value = "execution (* com.demo.controller.EmployeesControllerImpl.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		Object arguments[]=joinPoint.getArgs();
		for(Object arg:arguments) {
			log.info("Arg {}",arg);
		}
		String methodName=joinPoint.getSignature().getName();
		log.info("Successful Execution {}",methodName);
	}
	@AfterReturning(value = "execution (* com.demo.controller.EmployeesControllerImpl.*(..))",returning = "result")
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName=joinPoint.getSignature().getName();
		log.info("<<< END: {}() | Result: {}", methodName, result);	
	
	}
	@AfterThrowing(value = "execution (* com.demo.controller.EmployeesControllerImpl.*(..))",throwing = "error")
	public void afterThrowing(JoinPoint joinPoint,Throwable error) {
		String methodName=joinPoint.getSignature().getName();
		log.error("!!! EXCEPTION in {}: {} - Message: {}", 
                methodName, 
                error.getClass().getSimpleName(), 
                error.getMessage());
	}

}
