/*package com.myin.team25.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Component
//aspect ������ ����ϱ� ���� ���
@Aspect
//aspect�� ��ü����
//Ʈ������ , aop �Ѵ� ���Ͻ� ������ ���ش�
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	//*.* .�� ���� ��� �޼ҵ忡 ����
	@Before("execution(* com.myin.team25.service.BoardService*.*(..))")
		public void startLog(){
		
		logger.info("^---------------------------------^");
		logger.info("----------------------------------");
	}
}
*/