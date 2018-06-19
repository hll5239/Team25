package com.myin.team25.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class BoardTimeCheckAdvice {

	private static final Logger logger = LoggerFactory.getLogger(BoardTimeCheckAdvice.class);	
	
//주로 사용하는 advice 종류 ProceedingJoinPoint메소드 작동 JoinPoint 받아서 사용하는 메소드
	
@Around("execution(* com.myin.team25.service.BoardService*.*(..))")
public Object timeLog(ProceedingJoinPoint pjp)throws Throwable{
	
	long startTime = System.currentTimeMillis();
	logger.info(Arrays.toString(pjp.getArgs()));
	//targer 메소드에 직접 관여를 함
	Object result = pjp.proceed();
	
	long endTime = System.currentTimeMillis();
	logger.info(pjp.getSignature().getName()+ " : " + (endTime - startTime));
	logger.info("====================================");
	
	
	return result;
}
			
}
