package com.team.gaoguangjin.javabase.classforname;

public class Person {
	private String name = "Alfira";
	public final static String STATIC_FINAL_STATIC = "final常量";
	public static String STATIC_STRING = "静态变量";
	
	static {
		System.out.println("【1】这是静态代码块" + STATIC_FINAL_STATIC);
	}
	
	public Person() {
		System.out.println("不带参数的构造方法");
	}
	
	public void getName() {
		System.out.println(name);
	}
	
	public void setName(String name, int a) {
		this.name = name + a;
	}
}
