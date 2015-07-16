package com.test;

import java.util.List;

/**
 * @ClassName:EmptyListFoeach.java
 * @Description: 空集合不能循环 数组也是
 * @author gaoguangjin
 * @Date 2015-7-7 下午7:03:30
 */
public class EmptyListFoeach {
	public static void main(String[] args) {
		
		// String[] st = null;
		List<String> st = null;
		for (String string : st) {
			System.out.println(string);
		}
		
	}
}
