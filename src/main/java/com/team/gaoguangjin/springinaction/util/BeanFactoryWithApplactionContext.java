package com.team.gaoguangjin.springinaction.util;

import java.io.IOException;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.team.gaoguangjin.jdbc.springJdbc.testBeanXml.Student;

/**
 * @ClassName:BeanFactoryWithApplactionContext.java
 * @Description:
 * @author gaoguangjin
 * @Date 2015-4-23 下午1:48:47
 */
public class BeanFactoryWithApplactionContext {
	public static void main(String[] args) throws IOException {
		// ApplactionContext();
		BeanFactory();
	}
	
	private static void ApplactionContext() {
		// ApplicatioContext 也是继承了 extends BeanFactory
		ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/springjdbc/springjdbc.xml");
		Student st = (Student) ac.getBean("studentBean");
	}
	
	private static void BeanFactory() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource res = resolver.getResource("jdbcconfig/springjdbc/springjdbc.xml");
		System.out.println(res.getURL());
		// 继承了BeanFactory
		XmlBeanFactory bf = new XmlBeanFactory(res);
		bf.getBean("studentBean");
		bf.destroySingletons();// 关闭容器
		
	}
}
