package com.team.gaoguangjin.javabase.shift;

import lombok.extern.slf4j.Slf4j;

/**
 * kuohao chaonab
 * >>是带符号右移，负数高位补1，正数补0
 * <<左移不管负数还是正数，在低位永远补0
 * >>>是不带符号右移，不论负数还是正数，高位补0
 * << 左移运算符，num << 1,相当于num乘以2
 * >> 右移运算符，num >> 1,相当于num除以2
 * >> 右移运算符，num >> 2,相当于num除以2*2
 * >>> 无符号右移，忽略符号位，空位都以0补齐
 * 位移运算效率比较高 比如hashmap里面大量使用了。
 *
 *
 *  1、乘法 a * (2^n) 等价于 a << n
 3*2=3<<1
 3*4=3<<2
 2、除法  a / (2^n) 等价于 a>> n
 8/2=8>>1
 8/4=8>>2
 3、取模运算，采用位运算实现 a % (2^n) 等价于 a & (2^n - 1)
 9%4=9&3
 * @author:gaoguangjin
 * @date 2016/12/20 17:22
 */
@Slf4j
public class Shift {
	
	// 括号朝那边就是向那边移动，左位移是乘，右位移是减少
	public static void main(String[] args) {
		testPerformance();
		test();
		randomDemo();
	}

	/**
	 * 将一组数据随机分成几组
	 */

	private static void randomDemo() {
		//方法一。
		String a="aaaa";
		//随机分成2组
		int value = a.hashCode() % 2;
		// 因为1的二进制是0001，因为与运算，只有两个都是1的时候才返回1，否则返回0，
		//a.hashCode() 的末尾要么是0要么是1.
		int value2=a.hashCode()&1;
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

	/**
	 * 3
	 12
	 24
	 48
	 1
	 */
	private static void test() {
		// 2进制 1010
		int a = 6;
		// a/2
		System.out.println(a >> 1);
		System.out.println(a >> 2);
		// a *1*2 左边移动一位10100 =2*2*2*2+2*2
		System.out.println(a << 1);
		// a *2*2 左边移动两位101000 =2*2*2*2*2+2*2*2
		System.out.println(a << 2);
		System.out.println(a << 3);
		System.out.println(a >>> 2);
	}
}
