package com.team.gaoguangjin.thread.forkjion;

import java.util.ArrayList;
import java.util.List;

public class MoreListAdd {
	static int sum = 0;
	
	public static void main(String[] args) {
		Long bgtime = System.currentTimeMillis();
		List<Integer> list = addList();
		sumNormal(list);
		
		Long edtime = System.currentTimeMillis();
		// System.out.println("总消耗时间是：" + (edtime - bgtime));
		
		// sumThread(list);
		
	}
	
	private static void sumThread(final List<Integer> list) {
		
	}
	
	private static void sumNormal(List<Integer> list) {
		
		int length = list.size();
		for (int i = 0; i < length; i++) {
			sum = sum + list.get(i);
		}
		System.out.println("值是多少" + sum);
		
	}
	
	private static List<Integer> addList() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 1000000; i++) {
			list.add(1);
		}
		return list;
	}
}
