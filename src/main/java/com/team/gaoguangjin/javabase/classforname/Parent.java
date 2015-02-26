package com.team.gaoguangjin.javabase.classforname;

public class Parent {
	
	public final static String FINAL_STATIC_NAME = "father";
	public static String STATIC_NAME = "father";
	static {
		System.out.println("这是【父类】静态代码块");
	}
}
