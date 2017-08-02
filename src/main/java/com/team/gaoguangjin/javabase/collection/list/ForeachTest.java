package com.team.gaoguangjin.javabase.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:gaoguangjin
 * @date 2017/6/21 14:48
 */
public class ForeachTest {

	public static void main(String[] args) {
		test();
	}
	private static void test() {
		List<String> list = new ArrayList<>();
		for(String s : list) {
			System.out.println(s);
		}

		List<String> linkedList = new LinkedList<>();
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		linkedList.get(1);
	}
}
