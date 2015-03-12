package com.team.gaoguangjin.javabase.servlet.ioc.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanContextHelper {
	private static ApplicationContext _instance;
	
	static {
		if (_instance == null)
			_instance = buildApplicationContext();
	}
	
	private BeanContextHelper() {
	}
	
	/**
	 * 重新构建ApplicationContext对象
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext buildApplicationContext() {
		return new ClassPathXmlApplicationContext("reflact/reflact.xml");
	}
	
	/**
	 * 获取一个ApplicationContext对象
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return _instance;
	}
}
