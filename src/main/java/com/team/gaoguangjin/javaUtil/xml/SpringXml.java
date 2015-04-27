package com.team.gaoguangjin.javaUtil.xml;

import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @ClassName:SpringXml.java
 * @Description: spring解析xml的方法
 * @author gaoguangjin
 * @Date 2015-4-23 上午11:18:27
 */
public class SpringXml {
	public static void main(String[] args) {
		String location = "src/main/resources/youdao/youdaospring.xml";
		XmlWebApplicationContext swa = new XmlWebApplicationContext();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// Resource[] resources = ((ResourcePatternResolver)classLoader.getResources(location);
		
		// parseXml(url);
	}
	
	private static void parseXml(String url) {
		
	}
}
