package com.team.gaoguangjin.深入java虚拟机.类解析.静态常量;

/**
 * @ClassName:FinalStaticTest.java
 * @Description: final修饰的静态变量，调用时候不会初始化静态代码块，而没有加final修饰的会调用 。
 * 
 * 总结：final修饰的
 * @author gaoguangjin
 * @Date 2015-2-26 上午10:19:15
 */
public class FinalStaticTest {
	// 测试时候，注释分开去掉才能看到效果
	public static void main(String[] args) {
		// 静态常量
		// System.out.println(Student.STATIC_STRING);
		
		// final修饰的静态常量
		System.out.println(Student.FIANL_STATIC_STRING);
	}
}

/**
 * 总结：final static修饰的静态常量，调用时候 不会调用静态代码块 static修饰的静态常量是会调用的。 因为在final修饰的常量都是在 常量池里面，都是在编译期已经确定的。
 */
