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
	
//�ַ� ����ϴ� advice ���� ProceedingJoinPoint�޼ҵ� �۵� JoinPoint �޾Ƽ� ����ϴ� �޼ҵ�
	
@Around("execution(* com.myin.team25.service.BoardService*.*(..))")
public Object timeLog(ProceedingJoinPoint pjp)throws Throwable{
	
	long startTime = System.currentTimeMillis();
	logger.info(Arrays.toString(pjp.getArgs()));
	//targer �޼ҵ忡 ���� ������ ��
	Object result = pjp.proceed();
	
	long endTime = System.currentTimeMillis();
	logger.info(pjp.getSignature().getName()+ " : " + (endTime - startTime));
	logger.info("====================================");
	
	
	return result;
}
			
}
