package com.team.gaoguangjin.soa.DynamicJar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDynamic {
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:springwithoutjdbc.xml");
		
		// 加载自定义的类
		load();
		
		// 传统java加载
		Class<?> bean = Thread.currentThread().getContextClassLoader().loadClass("com.team.gaoguangjin.soa.DynamicJar.Bean");
		Object obj = bean.newInstance();
		
		// dispaly(obj);
		
		// 注册到spring
		springLoad(ac, obj);
		Object ob = ac.getBean("bean");
		dispaly(obj);
		
		FileOutputStream ofs = new FileOutputStream(new File(""));
		BufferedOutputStream bos = new BufferedOutputStream(ofs);
		bos.write(1);
	}
	
	private static void dispaly(Object obj) throws Exception {
		Method method = obj.getClass().getDeclaredMethod("getName");
		Object beanObj = method.invoke(obj);
		System.out.println(beanObj);
		
	}
	
	/**
	 * @Description: spring动态加载jar 然后注册到bean
	 * @param applicationContext
	 * @param obj
	 * @return:void
	 */
	private static void springLoad(ApplicationContext applicationContext, Object obj) {
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(obj.getClass());
		defaultListableBeanFactory.registerBeanDefinition("bean", beanDefinitionBuilder.getRawBeanDefinition());
	}
	
	private static void load() throws Exception {
		URL urls = new URL("file:/F:/IdeaProjects/TeamStudy/target/classes/com/team/gaoguangjin/soa/DynamicJar/dynamic.jar");
		// GetPI.class
		URLClassLoader urlLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		Class<URLClassLoader> sysclass = URLClassLoader.class;
		Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
		method.setAccessible(true);
		method.invoke(urlLoader, urls);
	}
}
