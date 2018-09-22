package com.team.gaoguangjin.acm.chapter06;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSorter {
	private HeapSorter() {}
	
	public static void sort(int[] a) {
		sort(a, a.length);
	}
	
	/**
	 * 对堆排序，不支持fromIndex，fromIndex总是零。当然有方法可以做到，但我认为都会存在
	 * 性能损失。一种解决方法就你Heap.heapSort()中一样，首先将数据复制到一个临时数组中，
	 * 然后再复制回原数组。另一种解决方法，改变left,right,parent方法，这会使原来高效的
	 * left等方法变得相对低效。
	 */
	public static void sort(int[] a, int length) {
		int size = length;
		// build heap
		for (int i = parent(size - 1); i >= 0; i--) {
			maxHeapfiy(a, i, size);
		}
		
		for (int i = length - 1; i > 0; i--) {
			swap(a, 0, i);

			maxHeapfiy(a, 0, --size);
		}
	}


	private static void swap(int[] a, int i, int j) { int temp = a[i]; a[i] = a[j]; a[j] = temp; }
	private static void swap(Object[] a, int i, int j) { Object temp = a[i]; a[i] = a[j]; a[j] = temp; }
	
	/**
	 * 使root保持最大堆的性质，这里采用非递归方法。
	 */
	private static void maxHeapfiy(int[] data, int root, int size) {
		int largest = root;
		while (true) {
			int left = left(root);
			int right = right(root);
			if (left < size && data[largest] < data[left]) {
				largest = left;
			}
			if (right < size && data[largest] < data[right]) {
				largest = right;
			}
			if (largest == root) break;
			
			swap(data, root, largest);			
			root = largest;
		}
	}
	
	public static void sort(Object[] a) {
		sort(a, a.length);
	}
	
	public static void sort(Object[] a, int length) {
		sort(a, length, null);
	}
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		sort(a, a.length, c);
	}
	
	public static <T> void sort(T[] a, int length, Comparator<? super T> c) {
		int size = length;
		// build heap
		for (int i = parent(size - 1); i >= 0; i--) {
			maxHeapfiy(a, i, size, c);
		}
		
		for (int i = length - 1; i > 0; i--) {
			swap(a, 0, i);

			maxHeapfiy(a, 0, --size, c);
		}
	}
	
	private static <T> void maxHeapfiy(T[] data, int root, int size, Comparator<? super T> c) {
		int largest = root;
		while (true) {
			int left = left(root);
			int right = right(root);
			if (left < size && compare(data, largest, left, c) < 0) {
				largest = left;
			}
			if (right < size && compare(data, largest, right, c) < 0) {
				largest = right;
			}
			if (largest == root) break;
			
			swap(data, root, largest);			
			root = largest;
		}
	}

	private static <T> int compare(T[] data, int i0, int i1, Comparator<? super T> c) {
		@SuppressWarnings("unchecked")
		int result = c == null ? ((Comparable)data[i0]).compareTo(data[i1])
							   : c.compare(data[i0], data[i1]);
		return result;
				
	}
	
	private static int parent(int i) {
		return (i - 1) >> 1;
	}
	
	private static  int left(int i) {
		return (i << 1) + 1;
	}
	
	private static int right(int i) {
		return (i + 1) << 1; 
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
