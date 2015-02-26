package com.team.gaoguangjin.深入java虚拟机.类加载;

/**
 * @ClassName:TestClassLoader.java
 * @Description: 双亲委派加载机制
 * @author gaoguangjin
 * @Date 2015-2-26 下午2:31:01
 */
public class TestClassLoader {
	public static void main(String[] args) {
		// 架包路径
		// path();
		// bootstrap引导类加载器，extension 扩展类加载器 app系统类加载器，用户自定义加载器
		// loaderPath();
		
		// test自定义
		testMyClassBean();
		
	}
	
	/**
	 * @Description: 将MyClassBean.class导出成*.jar格式拷贝到java.ext.dirs:C:\Program Files\Java\jdk1.6.0_43\jre\lib\ext目录下面
	 * 
	 * 对比测试一和测试二，我们明显可以验证前面说的双亲委派机制，系统类加载器在接到加载MyClassBean类型的请求时，
	 * 
	 * 首先将请求委派给父类加载器（标准扩展类加载器），标准扩展类加载器抢先完成了加载请求
	 * 
	 */
	private static void testMyClassBean() {
		
		try {
			Class<?> typeLoaded = Class.forName("com.team.gaoguangjin.深入java虚拟机.类加载.MyClassBean");
			ClassLoader classLoader = typeLoaded.getClassLoader();
			
			// 没拷贝之前输出的结果是sun.misc.Launcher$AppClassLoader@addbf1
			System.out.println(classLoader);
			
			// 输出结果就是 sun.misc.Launcher$ExtClassLoader@42e816
			System.out.println(classLoader);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void path() {
		System.out.println("sun.boot.class.path:" + System.getProperty("sun.boot.class.path"));
		System.out.println("java.ext.dirs:" + System.getProperty("java.ext.dirs"));
		System.out.println("java.class.path:" + System.getProperty("java.class.path"));
		
	}
	
	private static void loaderPath() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();// ClassLoader.getSystemClassLoader()
		ClassLoader classLoader = TestClassLoader.class.getClassLoader();
		System.out.println("getContextClassLoader:" + classLoader.toString());
		System.out.println("getContextClassLoader.parent:" + classLoader.getParent().toString());
		// 因为Bootstrap ClassLoader没有继承classloader 所以为空
		System.out.println("getContextClassLoader.parent2:" + classLoader.getParent().getParent());
	}
}
