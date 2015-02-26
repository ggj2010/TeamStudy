package com.team.gaoguangjin.深入java虚拟机.类解析.静态常量;

public class Student {
	public final static String FIANL_STATIC_STRING = "【2】final修饰的静态常量";
	public static String STATIC_STRING = "【2】静态常量";
	static {
		System.out.println("【1】静态代码块");
	}
	
	public static void main(String[] args) {
		// final修饰的静态常量
		// System.out.println(Student.FIANL_STATIC_STRING);
		
		// 静态常量
		System.out.println(Student.STATIC_STRING);
	}
	
}
