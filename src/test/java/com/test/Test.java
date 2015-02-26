package com.test;

public class Test {
	public static void main(String[] args) {
		// for (int i = 0; i < 3; i++) {
		// // printf格式化输出
		// System.out.printf("(%s,%s)", "gao", "帅哥");
		// }
		
		String a = "|ac|";
		String[] st = a.split("\\|");
		System.out.println(st.length);
		for (int i = 0; i < st.length; i++) {
			System.out.println(st[i]);
		}
	}
}
