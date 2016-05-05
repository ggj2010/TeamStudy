package com.team.gaoguangjin.javabase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * getDeclaredConstructor可以返回指定参数的构造函数， 而getConstructor只能放回指定参数的public的构造函数 必须要公有的才可以的啊不然报错Class com.fanshe.ReFlact can
 * not access a member of class com.fanshe.FanShe with modifiers "private"
 * @author ggj Class下的newInstance()的使用有局限，因为它生成对象只能调用无参的构造函数, 所以有时候需要先得到该类的构造器，然后再调用newInstance()
 * 
 * 
 * 通过反射创建新的类示例，有两种方式： Class.newInstance() Constructor.newInstance()
 * 
 * 以下对两种调用方式给以比较说明： Class.newInstance() 只能够调用无参的构造函数，即默认的构造函数； Constructor.newInstance() 可以根据传入的参数，调用任意构造构造函数。
 * 
 * Class.newInstance() 抛出所有由被调用构造函数抛出的异常。
 * 
 * Class.newInstance() 要求被调用的构造函数是可见的，也即必须是public类型的; Constructor.newInstance() 在特定的情况下，可以调用私有的构造函数。
 * 
 * 
 */
public class ReFlact {
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> reFlactClass = Class.forName("com.team.gaoguangjin.javabase.reflect.FanShe");
		// getClassNameWithParam(reFlactClass);
		 getClassWithoutParam(reFlactClass);
		
		// getDefaultConst();
		
		// getMethod(reFlactClass);
		
	}
	
	/**
	 * 获取某个类所有方法
	 * @param reFlactClass
	 */
	
	private static void getMethod(Class<?> reFlactClass) {
		try {
			Object object = reFlactClass.newInstance();
			Method[] methods = reFlactClass.getMethods();
			// 获取某个类的所有方法
			for (Method method : methods) {
				String methodName = method.getName();
				Class<?> returnType = method.getReturnType();
				Class<?>[] parameterTypes = method.getParameterTypes();
				
				System.out.print(methodName + "=================返回类型：");
				// 获取指定方法的返回类型
				System.out.print(returnType);
				// 参数类型
				
				if (parameterTypes.length > 0) {
					System.out.println("======================参数类型" + parameterTypes[0]);
				} else {
					System.out.println("=================无参数===");
				}
			}
		}
		catch (Exception e) {
		}
	}
	
	private static void getDefaultConst() {
		try {
			// 通过类加载器获取类对象
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass("com.team.gaoguangjin.javabase.reflect.FanShe");
			// //获取构造器
			Constructor<?> cons = clazz.getDeclaredConstructor(String.class);
			// 让私有变公有方法
			cons.setAccessible(true);
			FanShe fanshe = (FanShe) cons.newInstance("编号89757");
		}
		catch (Exception e) {
			
		}
		
	}
	
	/**
	 * 实例化不带参数的构造器
	 * @param reFlactClass
	 */
	private static void getClassWithoutParam(Class<?> reFlactClass) {
		try {
			// Class下的newInstance()的使用有局限，因为它生成对象只能调用无参的构造函数
			
			// 普通实例化
			Object object = reFlactClass.newInstance();
			// 得到成员变量
			Field fieldID = reFlactClass.getDeclaredField("id");
			Field fieldName = reFlactClass.getDeclaredField("name");
			// Class com.team.gaoguangjin.javabase.reflect.ReFlact can not access a member of class
			// com.team.gaoguangjin.javabase.reflect.FanShe with modifiers "private"
			fieldName.setAccessible(true);// 将私有变量变成共有的，才能赋值操作
			
			// 对成员变量进行赋值
			fieldID.set(object, "编号85757");
			fieldName.set(object, "高广金");
			
			// 调用无参数方法
			Method methodWithoutParams = reFlactClass.getMethod("getOutPut");
			methodWithoutParams.invoke(object);
			
			// 调用带参数方法
			Method methodWithParams = reFlactClass.getMethod("getParameter", String.class);
			
			String message = "【3】这是调用带参数方法的反射";
			String callBack = (String) methodWithParams.invoke(object, new Object[] { message });
			System.out.println(callBack);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 实例化代参数的构造器
	 * @param reFlactClass
	 */
	private static void getClassNameWithParam(Class<?> reFlactClass) {
		try {
			// 带有构造器方法,方法必须是public类型的
			Constructor<?> conStructor = reFlactClass.getConstructor(String.class, String.class);
			
			// 实例化带参数的
			FanShe fanshe = (FanShe) conStructor.newInstance("编号1", "gaoguangjin");
			
			// 调用方法
			Method method = reFlactClass.getMethod("getOutPut");
			method.invoke(fanshe);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
