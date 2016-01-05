package com.test.extend;

public class Test {
	public static void main(String[] args) {
		Child c = new Child();
		Child2 c2 = new Child2();
		c.setName("test1");
		System.out.println(c.name);
		System.out.println(c2.name);
	}
}
