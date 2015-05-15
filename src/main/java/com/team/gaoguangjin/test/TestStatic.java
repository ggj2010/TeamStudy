package com.team.gaoguangjin.test;

import com.team.gaoguangjin.javabase.servlet.ioc.enty.Student;

public class TestStatic {
	public static Student student = null;
	static {
		student = new Student();
		System.out.println("1234");
	}
}
