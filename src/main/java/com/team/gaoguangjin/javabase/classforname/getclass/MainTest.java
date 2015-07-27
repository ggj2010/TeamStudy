package com.team.gaoguangjin.javabase.classforname.getclass;

public class MainTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Student.class.getClass();
		
		// 初始化静态代码块
		// Class.forName("com.team.gaoguangjin.javabase.classforname.getclass.Student");
	}
}
