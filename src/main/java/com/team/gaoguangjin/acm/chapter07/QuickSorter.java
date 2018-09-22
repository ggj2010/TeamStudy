package com.team.gaoguangjin.acm.chapter07;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSorter {
	private QuickSorter() {}

	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int fromIndex, int toIndex) {
		if (toIndex - fromIndex > 1) {
			int q = partition(a, fromIndex, toIndex);
			
			sort(a, fromIndex, q-1 ); // 注意这里是q-1而不是q，a[q]=pivot可以不用它算进来排序，
									  // 相反算进来会发生死递归，考虑对数组[2,3]返回q=2，会循环调用sort(a, 0, 2)。
			sort(a, q, toIndex);
		}
	}

	private static int partition(int[] a, int fromIndex, int toIndex) {
		int lastIndex = toIndex - 1;
		int pivot = a[lastIndex];
		int i = fromIndex;
		for (int j = fromIndex; j < lastIndex; j++) {
			// 循环不变式：(跟教材上有所不同）
			// 		a[fromIndex..i) <= pivot
			// 		a[i..j) > pivot
			// 		a[toIndex-1] = pivot
			if (a[j] <= pivot) {
				swap(a, i++, j);
			}
		}
		swap(a, i, lastIndex);
		
		return i + 1;
	}
	
	/**
	 * 见思考题7-1：使用HOARE_PARTITION
	 */
	public static void sort2(int[] a) {
		sort2(a, 0, a.length);
	}
	public static void sort2(int[] a, int fromIndex, int toIndex) {
		if (toIndex - fromIndex > 1) {
			int q = partition2(a, fromIndex, toIndex);
			
			sort(a, fromIndex, q ); // 注意这里是q
			sort(a, q, toIndex);
		}
	}
	
	/*
	 * HOARE_PARTITION分治法。
	 * 注意：这里之所以不会出现超出数组边界的访问，是因为第一次循环结束是必有a[fromIndex]<=pivot，a[toIndex-1]>=pivot
	 */
	private static int partition2(int[] a, int fromIndex, int toIndex) {
		int pivot = a[fromIndex];
		int i = fromIndex - 1;
		int j = toIndex;
		while (true) {
			/* 循环不变式
			 		a[fromIndex..i] <= pivot
					a[j..toIndex) >= pivot */
			while (a[--j] > pivot) continue;
			while (a[++i] < pivot) continue;
			if (i < j) {
				swap(a, i, j);
			} else {
				return i;
			}
		}
	}

	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(Object[] a, int fromIndex, int toIndex) {
		if (toIndex - fromIndex > 1) {
			int q = partition(a, fromIndex, toIndex);
			
			sort(a, fromIndex, q-1 );
			sort(a, q, toIndex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static int partition(Object[] a, int fromIndex, int toIndex) {
		int lastIndex = toIndex - 1;
		Object pivot = a[lastIndex];
		int i = fromIndex;
		for (int j = fromIndex; j < lastIndex; j++) {
			if (((Comparable)a[j]).compareTo(pivot) <= 0) {
				swap(a, i++, j);
			}
		}

		swap(a, i, lastIndex);
		
		return i + 1;
	}

	public static <T> void sort(T[] a, Comparator<? super T> c) {
		sort(a, 0, a.length, c);
	}

	public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		if (toIndex - fromIndex > 1) {
			int q = partition(a, fromIndex, toIndex, c);
			
			sort(a, fromIndex, q-1, c);
			sort(a, q, toIndex, c);
		}
	}
	
	private static <T> int partition(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		int lastIndex = toIndex - 1;
		T pivot = a[lastIndex];
		int i = fromIndex;
		for (int j = fromIndex; j < lastIndex; j++) {
			if (c.compare(a[j], pivot) <= 0) {
				swap(a, i++, j);
			}
		}
		swap(a, i, lastIndex);
		
		return i + 1;
	}


	private static void swap(int[] a, int i, int j) { int temp = a[i]; a[i] = a[j]; a[j] = temp; }
	private static void swap(Object[] a, int i, int j) { Object temp = a[i]; a[i] = a[j]; a[j] = temp; }
	
	public static void main(String[] args) {
		int[] a = {5, 3, 2};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[]{5, 2, 3};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {2, 3, 5};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {2, 3};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {3, 2};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {3, 3};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {3, 3, 3};
		sort2(a);
		System.out.println(Arrays.toString(a));
		
/*		Integer[] a2 = {3, 5, 2, 1, 21, 7};
		sort(a2);
		System.out.println(Arrays.toString(a2));
		
		a2 = new Integer[]{3, 5, 2, 1, 21, 7};
		sort(a2, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println(Arrays.toString(a2));*/
	}
}
