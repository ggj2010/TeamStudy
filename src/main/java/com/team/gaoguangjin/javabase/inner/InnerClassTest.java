package com.team.gaoguangjin.javabase.inner;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:InnerClassTest.java
 * @Description: 内部类使用
 * @author gaoguangjin
 * @Date 2015-4-23 下午4:54:08
 */
@Slf4j
public class InnerClassTest {
	public static void main(String[] args) {
		InnerClassTest ict = new InnerClassTest();
		ict.demo1("高广金", new InnerClass() {
			public String getName(String name) {
				return name + "gaoguangjin";
			}
		});
	}
	
	protected static void demo3(String value) {
		log.info("执行demo1的方法：" + value);
	}
	
	private static void demo1(String name, InnerClass innerClass) {
		log.info("执行demo1的方法");
		String names = innerClass.getName(name);
		log.info("匿名类得到的名称：" + names);
	}
	
	public interface InnerClass {
		public String getName(String name);// 不要带参数
		
	}
}
