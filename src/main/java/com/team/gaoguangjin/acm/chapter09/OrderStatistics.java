package com.team.gaoguangjin.acm.chapter09;

import java.util.Random;

public class OrderStatistics {
	public static int maximum(int[] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("Must at least one element");
		}
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (max < a[i]) max = a[i];
		}
		return max;
	}
	
	public static int minimum(int[] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("Must at least one element");
		}
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min > a[i]) min = a[i];
		}
		return min;
	}
	
	public static int[] maxAndMin(int[] a) {
		if (a.length < 1) {
			throw new IllegalArgumentException("Must at least one element");
		}
		int min, max;
		min = max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min > a[i]) min = a[i];
			if (max < a[i]) max = a[i];
		}
		return new int[] {min, max};
	}

	/**
	 * 从array[fromIndex..toIndex)中选择第index小的元素，注意index从零开始计数。
	 */
	public static int select(int[] array, int fromIndex, int toIndex, int index) {
		if (index + fromIndex >= toIndex) throw new IllegalArgumentException("index out of bound");
		
		return select0(array, fromIndex, toIndex, index);
	}
	
	public static int select(int[] array, int index) {
		return select(array, 0, array.length, index);
	}
	
	private static final Random random = new Random();
	private static int select0(int[] array, int fromIndex, int toIndex, int index) {
		if (fromIndex + 1 == toIndex) return array[fromIndex]; // 只有一个元素
		
		int lastIndex = toIndex - 1;
		swap(array, lastIndex, random.nextInt(toIndex - fromIndex) + fromIndex);
		
		int pivot = array[lastIndex];
		int i = fromIndex;
		for (int j = fromIndex; j < lastIndex; j++) {
			if (array[j] <= pivot) {
				swap(array, i++, j);
			}
		}
		swap(array, i, lastIndex);
		
		if (index == i - fromIndex) return array[i];
		if (index < i - fromIndex) return select0(array, fromIndex, i, index);
		else return select0(array, i + 1, toIndex, (index - i + fromIndex - 1));
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i]; a[i] = a[j]; a[j] = temp;
	}
	
	
	public static void main(String[] args) {
		int[] a = new int[] { 3, 5, 1, 4, 7, 0, 8, 23, 17 };
		System.out.println("max: " + maximum(a));
		System.out.println("min: " + minimum(a));
		
		for (int i = 0; i < a.length; i++) {
			System.out.println("element " + i + ": " + select(a, i));
		}
	}
}
