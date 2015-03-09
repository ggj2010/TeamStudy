package com.team.gaoguangjin.javabase.string;

import org.junit.Test;

public class StringAndNewString {
	@Test
	public void test() {
		
		String a = "abc";
		String b = "abc";
		// 只有在两个引用都指向了同一个对象时才返回真值
		System.out.println(a == b);
		
		// 当我们将str1的值改为"bcd"时，JVM发现在栈中没有存放该值的地址，便开辟了这个地址，并创建了一个新的对象，其字符串的值指向这个地址
		a = "bcd";
		
		// 赋值的变化导致了类对象引用的变化，str1指向了另外一个新对象！而str2仍旧指向原来的对象
		System.out.println(a == b);
		
		// 只要是用new()来新建对象的，都会在堆中创建，而且其字符串是单独存值的，即使与栈中的数据相同，也不会与栈中的数据共享。
	}
}
