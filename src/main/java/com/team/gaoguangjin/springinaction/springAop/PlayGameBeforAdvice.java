package com.team.gaoguangjin.springinaction.springAop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 
 * @author gaoguangjin
 * 
 * 实现：MethodBeforeAdvice 接口
 * 
 * 功能：前置增强
 */
@Slf4j
public class PlayGameBeforAdvice implements MethodBeforeAdvice {
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		log.info("【1】方法执行之前");
	}
	
}
