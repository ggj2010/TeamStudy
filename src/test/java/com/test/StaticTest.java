package com.test;

public class StaticTest {
	static StaticBean sb;
	 static String name;
	
	public static void main(String[] args) {
		StaticTest a = new StaticTest();
		a.sb = new StaticBean("gao");
		a.name="ggj";
//		System.out.println(a.sb);

		System.out.println(a.name);
		StaticTest b = new StaticTest();
		b.sb = new StaticBean("gaos");
		b.name="gaoguangjin";

//		System.out.println(a.sb);
//		System.out.println(b.sb);

		System.out.println(StaticTest.name);
		System.out.println(StaticTest.name);

	}
}
