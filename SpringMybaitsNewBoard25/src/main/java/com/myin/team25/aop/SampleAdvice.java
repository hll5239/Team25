/*package com.myin.team25.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Component
//aspect 빈으로 등록하기 위해 사용
@Aspect
//aspect를 객체생성
//트랜젝션 , aop 둘다 프록시 서버가 해준다
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	//*.* .을 통해 모든 메소드에 접근
	@Before("execution(* com.myin.team25.service.BoardService*.*(..))")
		public void startLog(){
		
		logger.info("^---------------------------------^");
		logger.info("----------------------------------");
	}
}
*/