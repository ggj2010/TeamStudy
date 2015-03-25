package com.test;

/**
 * @ClassName:TestYuHuo.java
 * @Description: 看看||满足添加会不会执行后面的
 * @author gaoguangjin
 * @Date 2015-3-19 下午3:05:47
 */
public class TestYuHuo {
	public static void main(String[] args) {
		
		if (demo1() || demo2() || demo3()) {
			System.out.println("====");
		}
	}
	
	private static boolean demo3() {
		System.out.println(3);
		return true;
	}
	
	private static boolean demo2() {
		System.out.println(2);
		return false;
	}
	
	private static boolean demo1() {
		System.out.println(1);
		return true;
	}
}
