package com.team.gaoguangjin.springinaction.springAop;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class JunitTestSpringAop {
	@Test
	public void test() {
		// 用ProxyFactory方法去调用aop
		testByMethod();
		// 用spring配置文件去调用aop
		// testAopXml();
		
	}
	
	private void testAopXml() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("com/team/gaoguangjin/springinaction/springAop/beans.xml");
			PlayGame play = (PlayGame) ac.getBean("play");
			play.playLOL();
		} catch (Exception e) {
			log.error("测试xml aop增强失败！：" + e.getLocalizedMessage());
		}
	}
	
	private void testByMethod() {
		// 测试前置增强
		PlayGameBeforAdvice pba = new PlayGameBeforAdvice();
		excuteAdviceByParam(pba);
		// 测试后置增强
		PlayGameAfterAdvice paa = new PlayGameAfterAdvice();
		excuteAdviceByParam(paa);
		// 测试环绕增强
		PlayGameInterceptor pgi = new PlayGameInterceptor();
		excuteAdviceByParam(pgi);
	}
	
	public void excuteAdviceByParam(Advice advice) {
		try {
			PlayGame target = new PlayGameImp();
			// spring提供的代理工厂
			ProxyFactory proxy = new ProxyFactory();
			// 设置代理的接口
			proxy.setInterfaces(target.getClass().getInterfaces());
			// 设置代理目标
			proxy.setTarget(target);
			// 为代理目标添加增强
			proxy.addAdvice(advice);
			// 强制以cglib形式代理 对于单例模式来说第一次创建比较慢，后来就好。
			proxy.setOptimize(true);
			// 生成代理类
			PlayGame play = (PlayGame) proxy.getProxy();
			play.playLOL();
		} catch (Exception e) {
			log.error("测试aop增强失败！：" + e.getLocalizedMessage());
		}
	}
	
}
