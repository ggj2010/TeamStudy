package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:ListTest.java
 * @Description:引用传递，局部方法的修改，不会印象到对象本事。传递的是值的引用，也就是说传递前和传递后都指向同一个引用（也就是同一个内存空间）。
 * @author gaoguangjin
 * @Date 2015-3-23 下午6:20:14
 */
public class ListTest {
	public static void main(String[] args) {
		test2();
	}
	
	/**
	 * @Description: 引用传值赋值！！！！！
	 * @see：传递一个list过去，然后再用一个list赋值，这个引用只是局部改变了
	 * @return:void
	 */
	private static void test2() {
		List list1 = new ArrayList();
		demo2(list1);
		System.out.println("传递的listsize" + list1.size());
		
	}
	
	private static void demo2(List list) {
		List list2 = new ArrayList();
		list2.add("1");
		list = list2;// 将list2赋值给list 这时候list的应用已经改变了，对象指向的堆地址是list2对象的 已经完全和 list1没关系了噢
		System.out.println("局部的list" + list.size());
		
	}
	
}
