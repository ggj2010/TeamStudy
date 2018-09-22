package com.team.gaoguangjin.acm.chapter06;

import java.util.Arrays;
import java.util.Comparator;


public class Heap<T> {
	private static final Object[] EMPTY_ARRAY = new Object[0];
	
	// whether use recursive max heapify?
	private static final boolean RECURSIVE_MAX_HEAPIFY = true;
	
	private int size;
	@SuppressWarnings("unchecked")
	private T[] elementData = (T[]) EMPTY_ARRAY;	
	private Comparator<? super T> comparator = null;
	
	public Heap() {}
	
	public Heap(T[] data, int fromIndex, int toIndex) {
		init(data, fromIndex, toIndex);
	}
	
	private void init(T[] data, int fromIndex, int toIndex) {
		size = toIndex - fromIndex;
		@SuppressWarnings("unchecked")
		T[] copyData = (T[]) new Object[size];
		elementData =  copyData;
		System.arraycopy(data, fromIndex, elementData, 0, size);
		buildMaxHeap();
	}
	
	public Heap(T[] data) {
		this(data, 0, data.length);
	}

	public Heap(T[] data, Comparator<? super T> comparator, int fromIndex, int toIndex) {
		this(data, fromIndex, toIndex);
		if (comparator == null) throw new NullPointerException("comparator cannot be null");
		this.comparator = comparator;
		init(data, fromIndex, toIndex);
	}
	
	public Heap(T[] data, Comparator<? super T> comparator) {
		this(data, comparator, 0, data.length);
	}
	
	private void maxHeapify(int root) {
		if (RECURSIVE_MAX_HEAPIFY) _maxHeapify(root);
		else _maxHeapify_nonrecursive(root);
	}
	
	/**
	 * 使第指定结点保持堆的性质，它假定以left(i)和right(i)为根的两棵二叉树都保持堆的性质。
	 * 见算法导论6.2节。
	 */
	private void _maxHeapify(int root) {
		int left = left(root);
		int right = right(root);
		int largest = root;
		if (left < size && compare(elementData[left], elementData[largest]) > 0) {
			largest = left;
		}
		if (right < size && compare(elementData[right], elementData[largest]) > 0) {
			largest = right;
		}
		if (largest != root) {
			swap(elementData, largest, root);
			_maxHeapify(largest); // 递归调用
		}
	}
	
	private static void swap(Object[] a, int i, int j) { Object temp = a[i]; a[i] = a[j]; a[j] = temp; }
	
	private int compare(T o1, T o2) {
		@SuppressWarnings("unchecked")
		int result = comparator == null ? ((Comparable) o1).compareTo(o2)
									    : comparator.compare(o1, o2);
		return result;
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
			if (left < size && compare(elementData[left], elementData[largest]) > 0) {
				largest = left;
			}
			if (right < size && compare(elementData[right], elementData[largest]) > 0) {
				largest = right;
			}
			if (largest == parent) break;

			swap(elementData, largest, root);
			
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
	
	public T get(int i) {
		checkIndex(i);
		return elementData[i];
	}
	
	public int size() {
		return size;
	}
	
	public static void heapSort(Object[] data) {
		heapSort(data, 0, data.length);
	}
	
	public static void heapSort(Object[] data, int fromIndex, int toIndex) {
		heapSort(new Heap<Object>(data, fromIndex, toIndex), data, fromIndex, toIndex);
	}
	
	public static <T> void heapSort(T[] data, Comparator<? super T> c) {
		heapSort(data, 0, data.length, c);
	}
	
	public static <T> void heapSort(T[] data, int fromIndex, int toIndex, Comparator<? super T> c) {
		heapSort(new Heap<T>(data, c, fromIndex, toIndex), data, fromIndex, toIndex);
	}
	
	private static <T> void heapSort(Heap<T> heap, T[] data, int fromIndex, int toIndex) {		
		for (int i = heap.size - 1; i > 0; i--) {
			swap(heap.elementData, 0, i);
			
			heap.size--;
			heap.maxHeapify(0);
		}
		
		// copy back to original data
		System.arraycopy(heap.elementData, 0, data, fromIndex, toIndex - fromIndex);
	}
	
	public T maximum() {
		if (size == 0) throw new IndexOutOfBoundsException("heap underflow");
		return elementData[0];
	}
	
	public T extractMax() {
		if (size == 0) throw new IndexOutOfBoundsException("heap underflow");
		T max = elementData[0];
		elementData[0] = elementData[--size];
		maxHeapify(0);
		return max;
	}
	
	public void insert(T key) {
		ensureCapacity(++size);
		int i = size - 1;
		elementData[i] = key;
		while (i > 0 && compare(elementData[parent(i)], key) < 0) {
			int p = parent(i);
			swap(elementData, p, i);
			i = p;
		}
	}
	
	private void ensureCapacity(int capactiy) {
		if (elementData.length < capactiy) {
			T[] oldData = elementData;
			@SuppressWarnings("unchecked")
			T[] newData = (T[]) new Object[Math.max(elementData.length << 1, capactiy)];
			elementData = newData;
			System.arraycopy(oldData, 0, elementData, 0, oldData.length);
		}
	}

	private void checkIndex(int i) {
		// should check it, maybe: size < i < elementData.length
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
			if (l < size && compare(elementData[i], elementData[l]) < 0) {
				throw new AssertionError("element " + i + ": " + elementData[i] + " < element " + l + "(left child): " + elementData[l]);
			}
			if (r < size && compare(elementData[i], elementData[r]) < 0) {
				throw new AssertionError("element " + i + ": " + elementData[i] + " < element " + r + "(right child): " + elementData[r]);
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		heapSort(a);
		System.out.println(Arrays.toString(a));
		
		a = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		heapSort(a, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		System.out.println(Arrays.toString(a));
	}
}
