package com.team.gaoguangjin.acm.chapter10;


public class ArrayStack<E> {
	private static final int INITIAL_SIZE = 6;
	
	private E[] elementData;
	private int top; // 指向下一个空位置
	
	public ArrayStack(int intialSize) {
		@SuppressWarnings("unchecked")
		E[] data = (E[]) new Object[intialSize];
		elementData = data;
		top = 0;
	}
	
	public ArrayStack() { this(INITIAL_SIZE); }
	
	public E get(int i) {
		checkIndex(i);
		return elementData[i];
	}
	
	public E top() {
		return get(top - 1);
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public int size() {
		return top;
	}
	
	public void push(E elem) {
		ensureSize(top + 1);
		
		elementData[top++] = elem;
	}
	
	public E pop() {
		int t = top - 1;
		E v = get(t);
		elementData[t] = null; // happy GC
		top = t;
		return v;
	}
	
	/**
	 * 弹出n个元素，返回最后被弹出的元素。
	 * @param n
	 * @return
	 */
	public E pop(int n) {
		int t = top - n;
		E v = get(t);
		for (int i = t; i < top; i++) {
			elementData[i] = null; // happy GC
		}
		top = t;
		return v;
	}
	
	private void ensureSize(int size) {
		if (elementData.length < size) {
			int newLength = Math.max(elementData.length << 1, size);
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[newLength];
			System.arraycopy(this.elementData, 0, newData, 0, this.elementData.length);
			
			this.elementData = newData;
		}
	}
	
	private void checkIndex(int i) {
		if (i < 0 || i>= top) {
			throw new IndexOutOfBoundsException("index " + i + " not in [0, " + (top - 1) + "]");
		}
	}
}
