package com.team.gaoguangjin.test;

public class TestExceptionReturn {
	
	public static void main(String[] args) {
		String aa = test();
		System.out.println(aa);
	}
	
	private static String test() {
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			System.out.println("" + e.getLocalizedMessage());
		}
		return "错误捕获也能输出";
	}
	
}
