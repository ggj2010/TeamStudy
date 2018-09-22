package com.team.gaoguangjin.acm.chapter02;

import java.util.Comparator;

public class MergeSorter {
	private MergeSorter() {}
	
	public static void sort(int[] a, int fromIndex, int toIndex) {
		if (fromIndex + 1 < toIndex) {
			int middle = (fromIndex + toIndex) / 2;
			sort(a, fromIndex, middle);
			sort(a, middle, toIndex);
			merge(a, fromIndex, middle, toIndex);
		}
	}
	
	/*
	 * 这里在每次Merge时都创建一个比较小的临时数组，实际上在sort之前就创建一个大的数组
	 * 会更好，这是我在查看Arrays.sort(Object[] a)的源代码时发现的。
	 */
	private static void merge(int[] a, int fromIndex, int middle, int toIndex) {
		int[] leftArray = new int[middle - fromIndex];
		System.arraycopy(a, fromIndex, leftArray, 0, leftArray.length);
		int[] rightArray = new int[toIndex - middle];
		System.arraycopy(a, middle, rightArray, 0, rightArray.length);
		
		int i = 0;			// pointer to left array
		int j = 0;			// pointer to right array
		int k = fromIndex;	// pointer to whole array
		while (i < leftArray.length && j < rightArray.length) {
			if (leftArray[i] < rightArray[j]) {
				a[k++] = leftArray[i++];
			} else {
				a[k++] = rightArray[j++];
			}
		}
		while (i < leftArray.length) {
			a[k++] = leftArray[i++];
		}
		while (j < rightArray.length) {
			a[k++] = rightArray[j++];
		}
	}

	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(Object[] a, int fromIndex, int toIndex) {
		if (fromIndex + 1 < toIndex) {
			int middle = (fromIndex + toIndex) / 2;
			sort(a, fromIndex, middle);
			sort(a, middle, toIndex);
			merge(a, fromIndex, middle, toIndex);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static void merge(Object[] a, int fromIndex, int middle, int toIndex) {
		Object[] leftArray = new Object[middle - fromIndex];
		System.arraycopy(a, fromIndex, leftArray, 0, leftArray.length);
		Object[] rightArray = new Object[toIndex - middle];
		System.arraycopy(a, middle, rightArray, 0, rightArray.length);
		
		int i = 0;			// pointer to left array
		int j = 0;			// pointer to right array
		int k = fromIndex;	// pointer to whole array
		while (i < leftArray.length && j < rightArray.length) {
			if (((Comparable)leftArray[i]).compareTo(rightArray[j]) < 0) {
				a[k++] = leftArray[i++];
			} else {
				a[k++] = rightArray[j++];
			}
		}
		while (i < leftArray.length) {
			a[k++] = leftArray[i++];
		}
		while (j < rightArray.length) {
			a[k++] = rightArray[j++];
		}
	}

	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		sort(a, 0, a.length, c);
	}
	
	public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		if (fromIndex + 1 < toIndex) {
			int middle = (fromIndex + toIndex) / 2;
			sort(a, fromIndex, middle, c);
			sort(a, middle, toIndex, c);
			merge(a, fromIndex, middle, toIndex, c);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> void merge(T[] a, int fromIndex, int middle, int toIndex, Comparator<? super T> c) {
		T[] leftArray = (T[]) new Object[middle - fromIndex];
		System.arraycopy(a, fromIndex, leftArray, 0, leftArray.length);
		T[] rightArray = (T[]) new Object[toIndex - middle];
		System.arraycopy(a, middle, rightArray, 0, rightArray.length);
		
		int i = 0;			// pointer to left array
		int j = 0;			// pointer to right array
		int k = fromIndex;	// pointer to whole array
		while (i < leftArray.length && j < rightArray.length) {
			if (c.compare(leftArray[i], rightArray[j]) < 0) {
				a[k++] = leftArray[i++];
			} else {
				a[k++] = rightArray[j++];
			}
		}
		while (i < leftArray.length) {
			a[k++] = leftArray[i++];
		}
		while (j < rightArray.length) {
			a[k++] = rightArray[j++];
		}
	}
}
