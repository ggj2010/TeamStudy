package com.team.gaoguangjin.缓存.ehcahe.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName:springCache.java
 * @Description: spring自带的cache
 * @author gaoguangjin
 * @Date 2015-4-27 下午5:55:55
 */
public class springCache {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("cache/spring-cache-anno.xml");// 加载
		// spring
		// 配置文件
		
		AccountService s = (AccountService) context.getBean("accountServiceBean");
		// 第一次查询，应该走数据库
		System.out.print("first query...");
		s.getAccountByName("somebody");
		// 第二次查询，应该不查数据库，直接返回缓存的值
		System.out.print("second query...");
		s.getAccountByName("somebody");
	}
}
