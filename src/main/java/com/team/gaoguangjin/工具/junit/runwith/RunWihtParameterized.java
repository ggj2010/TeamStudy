package com.team.gaoguangjin.工具.junit.runwith;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @ClassName:RunWihtParameterized.java
 * @Description:    Parameterized继承自Suit，从这个身世和名字应该可以猜到一些因果了。Parameterized是在参数上实现了Suit——修饰一个测试类，但是可以提供多组构造函数的参数用于测试不同场景。略微有点抽象
 * @author gaoguangjin
 * @Date 2015-7-15 下午6:10:49
 */
@RunWith(Parameterized.class)
public class RunWihtParameterized {
	private String greeting;
	private String name;
	
	// 提供多组构造函数的参数
	public RunWihtParameterized(String greeting, String name) {
		this.greeting = greeting;
		this.name = name;
	}
	
	@Test
	public void testParams() {
		System.out.println(greeting + "==>" + name);
	}
	
	/**
	 *按顺序提供的一组参数,方法必须是static
	 * @return
	 */
	@Parameters
	public static List<String[]> getParams() {
		return Arrays.asList(new String[][] { { "hello", "高广金" }, { "hi", "高广金" }, { "good morning", "高广金" }, { "how are you", "高广金" } });
	}
	
	public static void main(String[] args) {
		String[][] a = new String[][] { { "hello", "高广金" }, { "hi", "高广金" }, { "good morning", "高广金" }, { "how are you", "高广金" } };
		System.out.println(a);
	}
}
