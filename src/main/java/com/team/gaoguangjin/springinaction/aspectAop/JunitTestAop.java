package com.team.gaoguangjin.springinaction.aspectAop;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class JunitTestAop {
	
	@Test
	public void test() {
		
		// 代码调用注解aop
		getFromMethod();
		
		// xml调用注解aop
		// geFromXml();
		
		// xml 拦截切面定义了 annation的类
		// geAnnoationFromXml();
		
	}
	
	private void geAnnoationFromXml() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"com/team/gaoguangjin/springinaction/aspectAop/beans.xml");
			PlayGame play = (PlayGame) ac.getBean("playGame");
			play.playAnnation();
		}
		catch (Exception e) {
			log.error("通过xml方式得到aspect注解的aop失败！" + e.getLocalizedMessage());
		}
	}
	
	private void geFromXml() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"com/team/gaoguangjin/springinaction/aspectAop/beans.xml");
			PlayGame play = (PlayGame) ac.getBean("playGame");
			play.playCs();
		}
		catch (Exception e) {
			log.error("通过xml方式得到aspect注解的aop失败！" + e.getLocalizedMessage());
		}
	}
	
	private void getFromMethod() {
		PlayGame target = new PlayGameImp();
		// 定义 AspectJProxyFactory 而不是ProxyFactory
		AspectJProxyFactory aspectFactory = new AspectJProxyFactory();
		// 先要setTarget 然后再添加aspect切面
		aspectFactory.setTarget(target);
		
		// 添加切面类
		aspectFactory.addAspect(AspectDemo.class);
		// 生成切面的代理对象
		PlayGame proxy = aspectFactory.getProxy();
		proxy.playLOL();
	}
}
