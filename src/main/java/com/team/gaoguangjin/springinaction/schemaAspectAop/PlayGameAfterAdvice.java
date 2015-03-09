package com.team.gaoguangjin.springinaction.schemaAspectAop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.AfterReturningAdvice;

@Slf4j
public class PlayGameAfterAdvice implements AfterReturningAdvice {
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.info("【3】方法执行之后");
	}
	
}
