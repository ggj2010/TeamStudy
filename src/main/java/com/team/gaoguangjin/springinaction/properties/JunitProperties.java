package com.team.gaoguangjin.springinaction.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
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
	
	public static void main(String[] args) throws IOException {
		// ApplicationContext ac = new ClassPathXmlApplicationContext("spring/testproperties.xml");
		// AbstractApplicationContext bb = ((AbstractApplicationContext) ac);
		// AbstractBeanFactory cc = (AbstractBeanFactory) bb.getBeanFactory();
		// String result = cc.resolveEmbeddedValue("${jdbc.url}");
		// System.out.println(result);
		// // 不容易
		// TestProperties tp = (TestProperties) ac.getBean("tt");
		// tp.test();
		
		// 不可以
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource res = resolver.getResource("spring/testproperties.xml");
		// 继承了BeanFactory
		AbstractBeanFactory bf = new XmlBeanFactory(res);
		TestProperties tps = (TestProperties) bf.getBean("tt");
		String result2 = bf.resolveEmbeddedValue("${jdbc.url}");
		System.out.println(result2);
		
		// 直接加载properties
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("jdbc.properties");
		Properties props = new Properties();
		InputStream is = resource.getInputStream();
		props.load(is);
		
		System.out.println(props.getProperty("jdbc.url"));
		
	}
}
