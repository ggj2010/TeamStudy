package com.team.gaoguangjin.acm.chapter08;

import java.util.Arrays;

public class LinearTimeSorter {
	
	/**
	 * 计数排序，所有的元素都在0和max之间。
	 */
	public static void countingSort(int[] array, int fromIndex, int toIndex, int max) {
		int[] counting = new int[max + 1];
		for (int i = fromIndex; i < toIndex; i++) {
			counting[array[i]]++;
		} // counting[i]现在包含值为i的个数
		
		for (int i = 1; i < counting.length; i++) {
			counting[i] += counting[i-1]; 
		} // counting[i]现在包含值小于等于i的个数
		
		int[] tempArray = new int[toIndex - fromIndex];
		for (int i = toIndex - 1; i >= 0; i--) { // 注意：这里是反向遍历，只有这样才是稳定的
			tempArray[--counting[array[i]]] = array[i];
		}
		
		// copy back
		System.arraycopy(tempArray, 0, array, fromIndex, tempArray.length);
	}
	
	public static void countingSort(int[] array, int max) {
		countingSort(array, 0, array.length, max);
	}
	
	/**
	 * 计数排序，所有的元素都在min和max之间。
	 */
	public static void countingSortMinMax(int[] array, int fromIndex, int toIndex, int min, int max) {
		int[] counting = new int[max - min + 1];
		for (int i = fromIndex; i < toIndex; i++) {
			counting[array[i]-min]++;
		} // counting[i]现在包含值为(i+min)的个数
		
		for (int i = 1; i < counting.length; i++) {
			counting[i] += counting[i-1]; 
		} // counting[i]现在包含值小于等于(i+min)的个数
		
		
		int[] tempArray = new int[toIndex - fromIndex];
		for (int i = toIndex - 1; i >= 0; i--) {
			tempArray[--counting[array[i] - min]] = array[i];
		}
		
		// copy back
		System.arraycopy(tempArray, 0, array, fromIndex, tempArray.length);
	}
	
	public static void countingSortMinMax(int[] array, int min, int max) {
		countingSortMinMax(array, 0, array.length, min, max);
	}
	
	/**
	 * 计数排序，自动决定min和max。
	 */
	public static void countingSort(int[] array) {
		if (array.length <= 1) return;
		int min = array[0];
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (min > array[i]) min = array[i];
			if (max < array[i]) max = array[i];
		}
		countingSortMinMax(array, min, max);
	}

	
	private static final int BITS_PER_GROUPS = 4;
	private static final int GROUPS_COUNT = 31 / BITS_PER_GROUPS + 1;
	

	private static final int[] MASK_TABLE = new int[GROUPS_COUNT];
	static {
		MASK_TABLE[0] = (1 << BITS_PER_GROUPS) - 1;
		for (int i = 1; i < MASK_TABLE.length; i++) {
			MASK_TABLE[i] = MASK_TABLE[i-1] << BITS_PER_GROUPS;
		}
	};
	
	/**
	 * 基数排序。将每个int(32位）拆分为8个4位组（或许拆分成4个8位组更好），
	 * 对每个四位组采用计数排序（最大值为15）。
	 * 
	 * 注意：该算法只能对非负整数进行排序。
	 */
	public static void radixSort(int[] array) {
		// 对8个4位组进行排序
		for (int j = 0; j < GROUPS_COUNT; j++) {
			int mask = MASK_TABLE[j];
			
			// 使用计数排序对4位组进行排序
			int[] counting = new int[1 << BITS_PER_GROUPS];
			for (int i = 0; i < array.length; i++) {
				counting[(array[i] & mask) >>> (j*BITS_PER_GROUPS)]++;
			}			
			for (int i = 1; i < counting.length; i++) {
				counting[i] += counting[i-1];
			}
			
			int[] tempArray = new int[array.length];
			for (int i = array.length - 1; i >= 0 ; i--) {
				tempArray[--counting[(array[i] & mask) >>> (j*BITS_PER_GROUPS)]] = array[i];
			}
			// copy back
			System.arraycopy(tempArray, 0, array, 0, tempArray.length);
		}
	}
	
	/**
	 * 桶排序，它假设数据均匀地落在区间[min,max]中。不像《算法导论》那样假设数据
	 * 是分布在[0, 1)之间的浮点数，这里假设的是整数。
	 */
	public static void bucketSort(int[] a, int min, int max) {
		Node[] nodes = new Node[a.length];
		for (int i = 0; i < a.length; i++) {
			int v = a[i];
			int bucketIndex = a.length * (v - min) / (max - min + 1);
			if (nodes[bucketIndex] == null) {
				nodes[bucketIndex] = new Node(v);
			} else {
				Node prev = null;
				Node node = nodes[bucketIndex];
				while (node != null && v > node.value) {
					prev = node;
					node = node.next;
				}
				if (prev == null) {
					nodes[bucketIndex] = new Node(v, nodes[bucketIndex]);
				} else {
					prev.next = new Node(v, node);
				}
			}
		}
		
		for (int i = 0, j = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			while (node != null) {
				a[j++] = node.value;
				node = node.next;
			}
		}
	}
	
	private static class Node {
		private int value;
		private Node next;
		Node(int value) { this.value = value; }
		Node(int value, Node next) { this.value = value; this.next = next; }
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {2, 5, 3, 0, 2, 3, 0, 3};
		countingSort(a, 5);
		System.out.println(Arrays.toString(a));
		
		a = new int[] {0, 3, 1, -2, 0, 1, -2, 1};
		countingSortMinMax(a, -2, 3);
		System.out.println(Arrays.toString(a));
		
		a = new int[] { 13, 30, 5, 21, 17, 25, 3, 9 , 2000};
		radixSort(a);
		System.out.println(Arrays.toString(a));
		
		a = new int[] { 7, 5, 13, 12, 3, 8, 0, 3, 15, 4};
		bucketSort(a, 0, 15);
		System.out.println(Arrays.toString(a));
	}
}
