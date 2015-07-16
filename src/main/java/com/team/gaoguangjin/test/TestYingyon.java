package com.team.gaoguangjin.test;

import java.util.ArrayList;
import java.util.List;

public class TestYingyon {
	public static void main(String[] args) {
		List list = new ArrayList();
		
		list.add(1);
		
		test(list);
		
		System.out.println(list.size());
	}
	
	private static void test(List list) {
		
		List list2 = new ArrayList();
		
		list2 = list;
		
		list.remove(0);
		
		System.out.println(list2.size());
		
		// System.out.println(list.size());
		
		// list.add(2);
		
	}
}
