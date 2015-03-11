package com.team.gaoguangjin.javabase.array;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:ArrayTest.java
 * @Description: java数组创建
 * @author gaoguangjin
 * @Date 2015-3-11 下午1:58:40
 */
@Slf4j
public class ArrayTest {
	@Test
	public void test() {
		Object[] o = new Object[6];
		Object[] o1 = { '5', '6' };
		int[] in = new int[5];
		int[] in1 = { 1, 2, 3, 4, 5 };
		// ..............
		
		test(in1[0], in1[1], in1[2]);
	}
	
	public void test(int... arg) {
		log.info("数组长度：" + arg.length);
	}
	
	/**
	 * 这种写法报错不可以
	 */
	// public void test(int... arg, String... args) {
	//
	// }
}
