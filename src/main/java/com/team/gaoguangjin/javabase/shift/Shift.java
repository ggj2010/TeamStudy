package com.team.gaoguangjin.javabase.shift;

import lombok.extern.slf4j.Slf4j;

/**
 * >>是带符号右移，负数高位补1，正数补0
 * <<左移不管负数还是正数，在低位永远补0
 * >>>是不带符号右移，不论负数还是正数，高位补0
 * << 左移运算符，num << 1,相当于num乘以2
 * >> 右移运算符，num >> 1,相当于num除以2
 * >>> 无符号右移，忽略符号位，空位都以0补齐
 * 位移运算效率比较高 比如hashmap里面大量使用了。
 *
 * @author:gaoguangjin
 * @date 2016/12/20 17:22
 */
@Slf4j
public class Shift {
	
	// 括号朝那边就是向那边移动，左位移是乘，右位移是减少
	public static void main(String[] args) {
		testPerformance();
		test();
	}
	
	private static void testPerformance() {
		long beginTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {
			int a = i / 2;
		}
		long endTime = System.currentTimeMillis();
		log.info("i/2耗时：{}", endTime - beginTime);
		beginTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {
			int a = i << 2;
		}
		endTime = System.currentTimeMillis();
		log.info("i<<2耗时：{}", endTime - beginTime);
	}
	
	private static void test() {
		// 2进制 1010
		int a = 6;
		// a/2
		System.out.println(a >> 1);
		// a *1*2 左边移动一位10100 =2*2*2*2+2*2
		System.out.println(a << 1);
		// a *2*2 左边移动两位101000 =2*2*2*2*2+2*2*2
		System.out.println(a << 2);
		System.out.println(a << 3);
		System.out.println(a >>> 2);
	}
}
