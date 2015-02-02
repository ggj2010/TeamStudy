package com.team.gaoguangjin.springinaction.springAop;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
/**
 * 
 * @author gaoguangjin
 * 
 * 功能：环绕增强
 * 
 * 主要实现 MethodInterceptor类
 */
public class PlayGameInterceptor implements MethodInterceptor {
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("【1】环绕增强之前");
		invocation.proceed();// 调用反射执行方法
		log.info("【3】环绕增强之后");
		return null;
	}
	
}
