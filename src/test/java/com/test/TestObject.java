package com.test;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
	
	List<String> list;
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		ObjectBean ob1 = new ObjectBean(list);
		ob1.list.add("dddd");
		
		// System.out.println(list.size());
		
		// ObjectBean ob2 = new ObjectBean(list);
		
		new TestObject().test2();
	}
	
	private void test2() {
		List<String> list = new ArrayList<String>();
		
		test3(list);
		System.out.println(list.size());
		
	}
	
	private void test3(List<String> list) {
		this.list = list;
		
		List<String> list2 = list;
		
		list2.add("dddd");
	}
}
