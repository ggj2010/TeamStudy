package com.team.gaoguangjin.javabase.servlet.ioc.enty;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
 * DisposableBean
 为Bean的销毁提供回调功能,在bean实例销毁前调用接口的destroy()方法.
 InitializingBean
 Spirng的InitializingBean为bean提供了定义初始化方法的方式。
 InitializingBean是一个接口，它仅仅包含一个方法：afterPropertiesSet()。
 */
public class Person implements InitializingBean, DisposableBean {
	private String name;
	private int age;
	{
		System.out.println("【0】静态方法");
	}
	
	public Person() {
		System.out.println("【1】构造方法在调用...");
		this.name = "默认用户名";
		// this.age = 250;
		System.out.println(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
	
	public void init() {
		System.out.println("【3】　init()正在调用...");
		this.name = "init";
		// this.age = 998;
		
	}
	
	public void afterPropertiesSet() throws Exception {
		System.out.println("【2】　afterPropertiesSet()正在调用...");
		// this.age = 999;
		
	}
	
	public void destroy() throws Exception {
		System.out.println("--------||　destroy()正在调用...");
	}
	
	/**
	 * 正是实例化Bean的过程：
	 * 
	 * 1、调用Bean的默认构造方法，或者在指定的构造方法，生成bean实例（暂称为instance1）；
	 * 3、如果Bean的配置文件中注入了Bean属性值，则在instance1基础上进行属性注入形成instance2，这种注入是覆盖性的。
	 * 2、如果Bean实现了InitializingBean接口，则调用afterPropertiesSet()方法，来改变或操作instance2，得到instance3；
	 * 4、如果Bean的配置文件中指定了init-method="init"属性，则会调用指定的初始化方法， 则在instance3的基础上调用初始化方法init()，将对象最终初始化为instance4；
	 * 当然，这个初始化的名字是任意的。
	 */
	
}
