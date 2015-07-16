package com.test;

public class StaticTest {
	static StaticBean sb;
	
	public static void main(String[] args) {
		StaticTest a = new StaticTest();
		a.sb = new StaticBean("gao");
		
		System.out.println(a.sb);
		StaticTest b = new StaticTest();
		
		b.sb = new StaticBean("gaos");
		
		System.out.println(a.sb);
		
		System.out.println(b.sb);
		
	}
}
