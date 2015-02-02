package com.team.gaoguangjin.springinaction.aspectAop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class AspectDemo {
	@Before("execution(* playLOL(..))")
	public void beging() {
		log.info("【1】 玩游戏之前需要【打开】电脑 通过方法方式");
	}
	
	@After("execution(* playLOL(..))")
	public void end() {
		log.info("【3】 不想玩游戏睡觉了需要【关闭】电脑 通过方法方式");
	}
	
	@Before("execution(* playCs(..))")
	public void beging1() {
		log.info("【1】 玩游戏之前需要【打开】电脑 通过xml方式");
	}
	
	@After("execution(* playCs(..))")
	public void end2() {
		log.info("【3】 不想玩游戏睡觉了需要【关闭】电脑 通过xml方式");
	}
	
	// 拦截所有带了注解的方法
	@Before("@annotation(com.team.gaoguangjin.springinaction.annoation.JdkAnnoation)")
	public void annationBegin() {
		log.info("【1】 注解方法运行之前");
	}
	
	@After("@annotation(com.team.gaoguangjin.springinaction.annoation.JdkAnnoation)")
	public void annationEnd() {
		log.info("【3】 注解方法之后");
	}
}
