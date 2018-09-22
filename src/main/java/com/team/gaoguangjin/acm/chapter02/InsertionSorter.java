package com.team.gaoguangjin.acm.chapter02;

import java.util.Arrays;
import java.util.Comparator;

public class InsertionSorter {
	private InsertionSorter() {}
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int fromIndex, int toIndex) {
		for (int j = fromIndex + 1; j < toIndex; j++) {
			int key = a[j];
			// insert a[j] to array a[0..j-1]
			int i = j - 1;
			while (i >= 0 && a[i] > key) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
	}
	
	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	@SuppressWarnings("unchecked")
	public static void sort(Object[] a, int fromIndex, int toIndex) {
		for (int j = fromIndex + 1; j < toIndex; j++) {
			Object key = a[j];
			int i = j - 1;
			while (i >= 0 && ((Comparable)a[i]).compareTo(key) > 0) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
	}
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		sort(a, 0, a.length, c);
	}
	
	public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		for (int j = fromIndex + 1; j < toIndex; j++) {
			T key = a[j];
			int i = j - 1;
			while (i >= 0 && c.compare(a[i], key) > 0) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
	}
	
	public static void main(String[] args) {
		int[] a = {3, 2, 5, 1};
		sort(a);
		System.out.println(Arrays.toString(a));
		
		Integer[] a2 = {3, 2, 5, 1};
		sort(a2);
		System.out.println(Arrays.toString(a2));
		
		a2 = new Integer[]{3, 2, 5, 1};
		sort(a2, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println(Arrays.toString(a2));
	}
}
