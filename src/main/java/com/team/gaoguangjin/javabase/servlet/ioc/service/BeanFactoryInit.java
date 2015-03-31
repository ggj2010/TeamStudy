package com.team.gaoguangjin.javabase.servlet.ioc.service;

import org.springframework.context.ApplicationContext;

import com.team.gaoguangjin.javabase.servlet.ioc.enty.Person;

public class BeanFactoryInit {
	public static void main(String args[]) {
		test();
	}
	
	public static void test() {
		ApplicationContext context = BeanContextHelper.getApplicationContext();
		Person person = (Person) context.getBean("person");
		System.out.println("【4】　从Spring BeanFactory获取person...");
		// System.out.println(person.getAge());
	}
}
