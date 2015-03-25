package com.team.gaoguangjin.thread.forkjion;

import java.util.ArrayList;
import java.util.List;

public class CountListIntegerSumMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int threadCounts = 10;// 采用的线程数
		// 生成的List数据
		for (int i = 1; i <= 1000000; i++) {
			list.add(i);
		}
		CountListIntegerSum countListIntegerSum = new CountListIntegerSum(list, threadCounts);
		long sum = countListIntegerSum.getIntegerSum();
		System.out.println("List中所有整数的和为:" + sum);
	}
}
