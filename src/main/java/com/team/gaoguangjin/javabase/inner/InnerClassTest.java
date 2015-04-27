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
		demo();
	}
	
	private static void demo() {
		
		demo1("测试", new InnerClass() {
			public void getName() {
				demo3("测试");
			}
		});
	}
	
	protected static void demo3(String value) {
		log.info("执行demo1的方法：" + value);
	}
	
	private static void demo1(String string, InnerClass innerClass) {
		log.info("执行demo1的方法");
		innerClass.getName();
	}
	
	public interface InnerClass {
		public void getName();// 不要带参数
		
	}
}
