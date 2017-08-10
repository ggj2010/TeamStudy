package com.team.gaoguangjin.算法.排序;

/**
 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。
 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 * @ClassName:SelectSort.java
 * @Description: 选择排序
 * @author gaoguangjin
 * @Date 2015-3-20 上午9:25:46
 */
public class SelectSort {
	public static void main(String[] args) {
		sort(SortUtil.getRandomArray(10), 10);
		sortNormal(SortUtil.getRandomArray(10), 10);
	}
	
	/**
	 * @Description: 用两个参数的这个比较容易理解
	 * @param randomArray
	 * @param length
	 * @return:void
	 */
	private static void sort(int[] randomArray, int length) {
		int min;
		int minIndex = 0;
		for (int i = 0; i < length - 1; i++) {
			min = randomArray[i];
			for (int j = i; j < length; j++) {
				if (min > randomArray[j]) {
					min = randomArray[j];
					minIndex = j;
				}
			}
			int temp = randomArray[i];
			randomArray[i] = min;
			randomArray[minIndex] = temp;
		}
		
		SortUtil.display("之后", randomArray);
	}
	
	/**
	 * @Description: 用一个参数下标控制
	 * @param randomArray
	 * @param length
	 * @return:void
	 */
	private static void sortNormal(int[] randomArray, int length) {
		for (int i = 0; i < length - 1; i++) {
			// 最小值
			int min = i;
			for (int j = i; j < length; j++) {
				if (randomArray[min] > randomArray[j]) {
					// 最小值的位置变化
					min = j;
				}
			}
			if (i != min) {
				int temp = randomArray[i];
				randomArray[i] = randomArray[min];
				randomArray[min] = temp;
			}
		}
		
		SortUtil.display("之后", randomArray);
	}
}
