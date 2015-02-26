package com.team.gaoguangjin.深入java虚拟机.类加载;

public class MyClassLoaderTest {
	public static void main(String[] args) throws Exception {
		try {
			MyClassLoader tc = new MyClassLoader();
			
			Class c = tc.loadClass("com.team.gaoguangjin.深入java虚拟机.类加载.MyClassBean");
			
			System.out.println(tc);
			Object reflectClass = c.newInstance();
			
			c.getMethod("print", String.class).invoke(reflectClass, "高广金");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
