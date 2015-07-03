package com.team.gaoguangjin.springinaction.aspectAop;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName:JunitTestAop.java
 * @Description: aspectaop有两种方式实现 一种实在烦方法面前加入注解@Before() 还有一种是在 xml文件里面进行配置
 * @author gaoguangjin
 * @Date 2015-3-8 下午5:59:50
 */
@Slf4j
public class JunitTestAop {
	
	@Test
	public void test() {
		
		// 代码调用注解aop
		// getFromMethod();
		
		// xml调用注解aop
		geFromXml();
		
		// xml 拦截切面定义了 annation的类
		// geAnnoationFromXml();
		
	}
	
	private void geAnnoationFromXml() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("com/team/gaoguangjin/springinaction/aspectAop/beans.xml");
			PlayGame play = (PlayGame) ac.getBean("playGame");
			play.playAnnation();
		} catch (Exception e) {
			log.error("通过xml方式得到aspect注解的aop失败！" + e.getLocalizedMessage());
		}
	}
	
	private void geFromXml() {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("com/team/gaoguangjin/springinaction/aspectAop/beans.xml");
			PlayGame play = (PlayGame) ac.getBean("playGame");
			play.playCs();
		} catch (Exception e) {
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
