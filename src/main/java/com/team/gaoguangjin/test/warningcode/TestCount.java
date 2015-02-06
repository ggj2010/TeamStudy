package com.team.gaoguangjin.test.warningcode;

/**
 * 
 * @author gaoguangjin 功能：count=count++误区
 */
public class TestCount {
	public static void main(String[] args) {
		// normal();
		// testLoop();
		
		// variableAdd();
		
		needPayAttention();
	}
	
	private static void needPayAttention() {
		int m = 4;
		m = m++;
		
		System.out.println("这个时候m的值为零：" + m);
		
	}
	
	private static void variableAdd() {
		// 有赋值
		int beforeCount = 0;
		int afterCount = 0;
		
		// 没有赋值
		int afterCount2 = 0;
		
		// 临时变量赋值
		int temp = 0;
		int tempCount = 0;
		
		// 【1】
		beforeCount = ++beforeCount;
		// 【2】
		afterCount2++;
		// 【3】
		afterCount = afterCount++;
		// 【4】独立的temp 和 after比较
		temp = tempCount++;
		
		System.out.println("【1】beforeCount使用之后：" + beforeCount);
		
		System.out.println("【2】afterCount使用之后，再赋值给afterCount：" + afterCount);
		System.out.println("【3】afterCount2使用之后，再赋值给afterCount2：" + afterCount2);
		System.out.println("【4】tempCount使用之后,赋值给temp：" + temp);
		System.out.println("【5】tempCount使用之后,tempCount：" + tempCount);
		
	}
	
	/**
	 * 循环里面测试
	 */
	private static void testLoop() {
		int beforeCount = 0;
		int afterCount = 0;
		for (int i = 0; i < 5; i++) {
			
			System.out.println("++beforeCount:" + (++beforeCount));
			System.out.println("afterCount++:" + (afterCount++));
		}
		
		System.out.println("beforeCount使用之后：" + beforeCount);
		System.out.println("afterCount使用之后：" + afterCount);
		
	}
	
	/**
	 * i++ 和++i
	 * 
	 * ++i使用之前加1 i++使用之后再加一。
	 * 
	 * 使用之后都会+1;
	 */
	private static void normal() {
		int beforeCount = 0;
		int afterCount = 0;
		
		System.out.println("++beforeCount:" + (++beforeCount));
		System.out.println("afterCount++:" + (afterCount++));
		
		System.out.println("beforeCount使用之后：" + beforeCount);
		System.out.println("afterCount使用之后：" + afterCount);
		
	}
	
	private static void demo1() {
		int count = 0;
		for (int i = 0; i < 1; i++) {
			// count = count++;
			count++;
		}
		System.out.println(count);
		
	}
}
