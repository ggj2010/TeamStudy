package com.team.gaoguangjin.alibaba.dubbo;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName:JunitTestConsumer.java
 * @Description: 要开启zookeper服务
 * @author gaoguangjin
 * @Date 2015-3-30 上午11:32:10
 */
@Slf4j
public class JunitTestConsumer {
	@Before
	public void before() throws IOException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo/dubbo-provider.xml" });
		context.start();
		// System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
		// ApplicationContext ac = new ClassPathXmlApplicationContext("dubbo/dubbo-provider.xml");
	}
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dubbo/dubbo-consumer.xml");
		// context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("world"); // 执行远程方法
		log.info(hello);
		
	}
}
