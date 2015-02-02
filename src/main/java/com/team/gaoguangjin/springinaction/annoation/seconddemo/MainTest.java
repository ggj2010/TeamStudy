package com.team.gaoguangjin.springinaction.annoation.seconddemo;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * 自定义注解测试
 * @author gaoguangjin
 * @date 2015-01-28
 */
@Slf4j
public class MainTest {
	
	@Test
	public void testAnnation() throws SecurityException, NoSuchFieldException {
		ColorAnnation annation = FruitColorTest.class.getField("balana").getAnnotation(ColorAnnation.class);
		String color = annation.color();
		String name = annation.name();
		
		log.info("水果注解名称：" + name + "\r\n 水果注解颜色：" + color);
	}
	
}
