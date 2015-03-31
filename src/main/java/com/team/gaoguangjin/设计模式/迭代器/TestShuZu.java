package com.team.gaoguangjin.设计模式.迭代器;

import java.util.Iterator;

public class TestShuZu {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		String[] str = { "a", "b", "c" };
		
		ShuZhu sz = new ShuZhu(str);
		
		Iterator it = sz.createIterator();
		
		while (it.hasNext()) {
			String m = (String) it.next();
			System.out.print(m);
		}
		
	}
}
