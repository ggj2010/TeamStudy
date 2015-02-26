package com.team.gaoguangjin.springinaction.annoation;

import java.lang.reflect.InvocationTargetException;

public class AnnotationDemo {
	@JdkAnnoation(value = true, url = "www.baidu.com")
	public void demo1() {
		System.out.println("这是注解得到的值1");
	}
	
	@JdkAnnoation(value = false, url = "www.gaoguangjin.com")
	public void demo2() {
		System.out.println("这是注解得到的值2");
	}
	
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException,
			InvocationTargetException, NoSuchMethodException {
		try {
			Class<?> classs = Class.forName("com.team.gaoguangjin.springinaction.annoation.AnnotationDemo");
			Object reflect = classs.newInstance();
			
			classs.getMethod("demo1").invoke(reflect, null);
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
