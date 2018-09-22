package com.team.gaoguangjin.acm.chapter06;

import java.util.Arrays;

/**
 * 最大堆的实现。
 */
public class IntHeap {
	private static final int[] EMPTY_ARRAY = new int[0];
	
	// whether use recursive max heapify?
	private static final boolean RECURSIVE_MAX_HEAPIFY = false;
	
	private int size;
	private int[] elementData = EMPTY_ARRAY;
	
	public IntHeap() {}
	
	public IntHeap(int[] data, int fromIndex, int toIndex) {
		size = toIndex - fromIndex;
		elementData =  new int[size];
		System.arraycopy(data, fromIndex, elementData, 0, size);
		buildMaxHeap();
	}
	
	public IntHeap(int[] data) {
		this(data, 0, data.length);
	}
	
	private void maxHeapify(int root) {
		if (RECURSIVE_MAX_HEAPIFY) _maxHeapify(root);
		else _maxHeapify_nonrecursive(root);
	}
	
	private static void swap(int[] a, int i, int j) { int temp = a[i]; a[i] = a[j]; a[j] = temp; }
	
	/**
	 * 使第指定结点保持堆的性质，它假定以left(i)和right(i)为根的两棵二叉树都保持堆的性质。
	 * 见算法导论6.2节。
	 */
	private void _maxHeapify(int root) {
		int left = left(root);
		int right = right(root);
		int largest = root;
		if (left < size && elementData[left] > elementData[largest]) {
			largest = left;
		}
		if (right < size && elementData[right] > elementData[largest]) {
			largest = right;
		}
		if (largest != root) {
			swap(elementData, largest, root);
			_maxHeapify(largest); // 递归调用
		}
	}
	
	/**
	 * _maxHeapify的非递归版本
	 */
	private void _maxHeapify_nonrecursive(int root) {
		int parent = root;
		int largest = parent;
		while (true) {
			int left = left(parent);
			int right = right(parent);
			if (left < size && elementData[left] > elementData[largest]) {
				largest = left;
			}
			if (right < size && elementData[right] > elementData[largest]) {
				largest = right;
			}
			if (largest == parent) break;
			
			swap(elementData, largest, parent);
			parent = largest;
		}
	}
	
	private void buildMaxHeap() {
		for (int i = parent(size - 1); i >= 0; i--) {
			maxHeapify(i);
		}
	}
	
	private int parent(int i) {
		return (i - 1) >> 1;
	}
	
	private int left(int i) {
		return (i << 1) + 1;
	}
	
	private int right(int i) {
		return (i + 1) << 1; 
	}
	
	public int get(int i) {
		checkIndex(i);
		return elementData[i];
	}
	
	public int size() {
		return size;
	}
	
	public static void heapSort(int[] data) {
		heapSort(data, 0, data.length);
	}
	
	public static void heapSort(int[] data, int fromIndex, int toIndex) {
		IntHeap heap = new IntHeap(data, fromIndex, toIndex);
		
		for (int i = heap.size - 1; i > 0; i--) {
			swap(heap.elementData, 0, i);
			
			heap.size--;
			heap.maxHeapify(0);
		}
		
		// copy back to original data
		System.arraycopy(heap.elementData, 0, data, fromIndex, toIndex - fromIndex);
	}
	
	public int maximum() {
		if (size == 0) throw new IndexOutOfBoundsException("heap underflow");
		return elementData[0];
	}
	
	public int extractMax() {
		if (size == 0) throw new IndexOutOfBoundsException("heap underflow");
		int max = elementData[0];
		elementData[0] = elementData[--size];
		maxHeapify(0);
		return max;
	}
	
	public void increaseKey(int i, int key) {
		checkIndex(i);

		if (key < elementData[i]) throw new RuntimeException("new key is smaller than current key");
		elementData[i] = key;
		while (i > 0 && elementData[parent(i)] < key) {
			int p = parent(i);
			swap(elementData, p, i);
			i = p;
		}
	}
	
	public void insert(int key) {
		ensureCapacity(++size);
		int i = size - 1;
		elementData[i] = key;
		while (i > 0 && elementData[parent(i)] < key) {
			int p = parent(i);
			swap(elementData, p, i);
			i = p;
		}
	}
	
	private void ensureCapacity(int capactiy) {
		if (elementData.length < capactiy) {
			int[] oldData = elementData;
			elementData = new int[Math.max(elementData.length << 1, capactiy)];
			System.arraycopy(oldData, 0, elementData, 0, oldData.length);
		}
	}
	
	private void checkIndex(int i) {
		// should check it, because maybe elementData.length > size
		if (i < 0 || i >= size) throw new IndexOutOfBoundsException("index " + i + " not in [0, " + size + ")");
	}
	
	/**
	 * Check whether heap property is satisfied.
	 * it is a package private, just for test.
	 */
	void validateHeap() {
		for (int i = 0; i < size; i++) {
			int l = left(i);
			int r = right(i);
			if (l < size && elementData[i] < elementData[l]) {
				throw new AssertionError("element " + i + ": " + elementData[i] + " < element " + l + "(left child): " + elementData[l]);
			}
			if (r < size && elementData[i] < elementData[r]) {
				throw new AssertionError("element " + i + ": " + elementData[i] + " < element " + r + "(right child): " + elementData[r]);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		heapSort(a);
		System.out.println(Arrays.toString(a));
		
		
		// build heap
		a = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		IntHeap heap = new IntHeap(a);
		for (int i = 0; i < heap.size; i++) {
			System.out.print(heap.elementData[i] + " ");
		}
		System.out.println();
		
		// extract max
		for (int i = 0; i < a.length; i++) {
			System.out.print(heap.extractMax() + " ");
		}
		System.out.println("\nnow heap size: " + heap.size);
		
		// insert
		heap = new IntHeap();
		for (int i = 0; i < a.length; i++) {
			heap.insert(a[i]);
		}
		for (int i = 0; i < heap.size; i++) {
			System.out.print(heap.elementData[i] + " ");
		}
		System.out.println();
		
		// increase key
		a = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		heap = new IntHeap(a);
		heap.increaseKey(8, 15);
		for (int i = 0; i < heap.size; i++) {
			System.out.print(heap.elementData[i] + " ");
		}
	}

}
