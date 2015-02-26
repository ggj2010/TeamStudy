package com.team.gaoguangjin.深入java虚拟机.类解析.静态常量;

import java.util.Random;

public class StaticTest {
	static Random r = new Random(12);
	final int int1 = r.nextInt(100);// 产生0-99的随机数
	
	static int INT_2 = r.nextInt(100);
	
	public static void main(String[] args) {
		StaticTest b1 = new StaticTest();
		System.out.println("int1:" + b1.int1 + "    INT_2:" + b1.INT_2);
		StaticTest b2 = new StaticTest();
		// b2.INT_2=100;//错误的赋值
		System.out.println("int1:" + b2.int1 + "    INT_2:" + b2.INT_2);
		
	}
}
