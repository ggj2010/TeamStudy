package com.team.gaoguangjin.springinaction.annoation.clzz;

import java.lang.annotation.Annotation;

/**
 * @ClassName:MainTest.java
 * @Description: 注解类CheckAgeAnnation 和方法注解MethodAnnation
 * @author gaoguangjin
 * @Date 2015-4-22 上午10:30:11
 */
public class MainTest {
	
	private static String name;
	
	public static void main(String[] args) {
		// test();
		MainTest mt = new MainTest();
		System.out.println(name.equals("123"));
	}
	
	private static void test() {
		PlayGame pg1 = new NeedCheck();
		PlayGame pg2 = new NeverNeedCheck();
		
		Annotation[] aa = pg1.getClass().getAnnotations();
		System.out.println();
	}
}
