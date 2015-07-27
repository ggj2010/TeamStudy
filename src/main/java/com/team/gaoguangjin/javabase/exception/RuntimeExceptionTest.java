package com.team.gaoguangjin.javabase.exception;


public class RuntimeExceptionTest {
	public static void main(String[] args) {
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
