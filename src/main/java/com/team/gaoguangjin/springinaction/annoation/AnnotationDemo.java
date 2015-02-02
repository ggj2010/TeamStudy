package com.team.gaoguangjin.springinaction.annoation;

public class AnnotationDemo {
	@JdkAnnoation(value = true, url = "www.baidu.com")
	public void demo1() {
		System.out.println("这是注解得到的值1");
	}
	
	@JdkAnnoation(value = false, url = "www.gaoguangjin.com")
	public void demo2() {
		System.out.println("这是注解得到的值2");
	}
}
