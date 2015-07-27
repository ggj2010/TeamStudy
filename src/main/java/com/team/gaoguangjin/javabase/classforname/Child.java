package com.team.gaoguangjin.javabase.classforname;

public class Child extends Parent {
	
	public static String CHILD_STATIC_NAME = "childe";
	static {
		System.out.println("这是【子类】静态代码块");
	}
}
