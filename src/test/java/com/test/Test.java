package com.test;

public class Test {
	public static void main(String[] args) {
		int m = 2;
		String[] st = new String[2];
		demo(m, st);
		System.out.println(m + "" + st[0]);
	}
	
	private static void demo(int m, String[] st) {
		m = 10;
		st[0] = "123";
		st[1] = "123";
	}
}
