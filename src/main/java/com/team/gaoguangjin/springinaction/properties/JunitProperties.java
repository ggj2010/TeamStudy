package com.team.gaoguangjin.springinaction.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/testproperties.xml" })
public class JunitProperties {
	@Autowired
	TestProperties testProperties;
	
	@Test
	public void test() {
		testProperties.test();
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/testproperties.xml");
		AbstractApplicationContext bb = ((AbstractApplicationContext) ac);
		AbstractBeanFactory cc = (AbstractBeanFactory) bb.getBeanFactory();
		String result = cc.resolveEmbeddedValue("${jdbc.url}");
		System.out.println(result);
		// 不容易
		
		// TestProperties tp = (TestProperties) ac.getBean("tt");
		// tp.test();
		
		// ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// Resource res = resolver.getResource("spring/testproperties.xml");
		// // 继承了BeanFactory
		// XmlBeanFactory bf = new XmlBeanFactory(res);
		// TestProperties tp = (TestProperties) bf.getBean("tt");
		// tp.test();
		// String result = bf.resolveEmbeddedValue("${jdbc.url}");
		// System.out.println(result);
		//
	}
}
