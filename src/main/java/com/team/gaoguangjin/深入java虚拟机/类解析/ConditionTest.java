package com.team.gaoguangjin.深入java虚拟机.类解析;

/**
 * @ClassName:ConditionTest.java
 * @Description: java是条件编译的
 * @author gaoguangjin
 * @Date 2015-2-15 下午3:50:38
 */
public class ConditionTest {
	public static void main(String[] args) {
		if (false) {
			System.out.println("123");
		}
	}
}
