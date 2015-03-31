package com.team.gaoguangjin.javabase.classforname;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:Test.java
 * @Description:比较 类名.class, class.forName(), getClass()三者区别
 * 
 * Class.forName()在运行时加载；Class.class和getClass()是在编译器加载，即.class是静态加载，.getClass()是动态加载。
 * @author gaoguangjin
 * @Date 2015-2-26 上午9:38:37
 */
@Slf4j
public class TestStatic {
	public static void main(String[] args) {
		
		// 类的装载方式
		load();
		
		// // final static测试
		// staticTest();
		
		// show("com.team.gaoguangjin.javabase.classforname.Person");
	}
	
	/**
	 * 总结：final static修饰的静态常量，调用时候 不会调用静态代码块 static修饰的静态常量是会调用的。 因为在final修饰的常量都是在 常量池里面，都是在编译期已经确定的。
	 */
	private static void staticTest() {
		System.out.println(Person.STATIC_FINAL_STATIC);
		System.out.println("===========无敌分割线===============");
		System.out.println(Person.STATIC_STRING);
	}
	
	/**
	 * @Description: 类的装载方式有两种forname和classloader.load
	 */
	private static void load() {
		try {
			// 装入类Person,并做类的【静态】初始化,会调用类的静态代码块
			// Class<?> class1 = Class.forName("com.team.gaoguangjin.javabase.classforname.Person");
			
			// 获取classloader 再装载类，不会做类的初始化
			Class<?> class2 = TestStatic.class.getClassLoader().loadClass("com.team.gaoguangjin.javabase.classforname.Person");
			// class2.newInstance();
			
			// 装载类，不会做类的初始化
			// Class<Person> class3 = Person.class;
			
			// // class3.newInstance();
			
		} catch (Exception e) {
			log.info("" + e.getLocalizedMessage());
		}
		
	}
	
	private static void show(String name) {
		try {
			// JVM将使用类A的类装载器,将类A装入内存(前提是:类A还没有装入内存),不对类A做类的初始化工作
			Class classtype3 = Person.class;
			// 获得classtype中的方法
			Method getMethod3 = classtype3.getMethod("getName", new Class[] {});
			Class[] parameterTypes3 = { String.class, int.class };
			Method setMethod3 = classtype3.getMethod("setName", parameterTypes3);
			
			// 实例化对象，因为这一句才会输出“静态初始化”以及“初始化”
			Object obj3 = classtype3.newInstance();
			// 通过实例化后的对象调用方法
			getMethod3.invoke(obj3); // 获取默认值
			setMethod3.invoke(obj3, "Setting new ", 3); // 设置
			getMethod3.invoke(obj3); // 获取最新
			System.out.println("----------------");
			
			// 返回运行时真正所指的对象
			Person p = new Person();
			Class classtype = p.getClass();// Class.forName(name);
			// 获得classtype中的方法
			Method getMethod = classtype.getMethod("getName", new Class[] {});
			Class[] parameterTypes = { String.class, int.class };
			Method setMethod = classtype.getMethod("setName", parameterTypes);
			getMethod.invoke(p);// 获取默认值
			setMethod.invoke(p, "Setting new ", 1); // 设置
			getMethod.invoke(p);// 获取最新
			System.out.println("----------------");
			
			// 装入类,并做类的初始化
			Class classtype2 = Class.forName(name);
			// 获得classtype中的方法
			Method getMethod2 = classtype2.getMethod("getName", new Class[] {});
			Class[] parameterTypes2 = { String.class, int.class };
			Method setMethod2 = classtype2.getMethod("setName", parameterTypes2);
			// 实例化对象
			Object obj2 = classtype2.newInstance();
			// 通过实例化后的对象调用方法
			getMethod2.invoke(obj2); // 获取默认值
			setMethod2.invoke(obj2, "Setting new ", 2); // 设置
			getMethod2.invoke(obj2); // 获取最新
			
			System.out.println("----------------");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
