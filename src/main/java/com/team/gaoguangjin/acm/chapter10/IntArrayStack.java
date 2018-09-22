package com.team.gaoguangjin.acm.chapter10;

public class IntArrayStack {
	private static final int INITIAL_SIZE = 6;
	
	private int[] elementData;
	private int top; // 指向下一个空位置
	
	public IntArrayStack(int intialSize) {
		elementData = new int[intialSize];
		top = 0;
	}
	
	public IntArrayStack() { this(INITIAL_SIZE); }
	
	public int get(int i) {
		checkIndex(i);
		return elementData[i];
	}
	
	public int top() {
		return get(top - 1);
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public int size() {
		return top;
	}
	
	public void push(int i) {
		ensureSize(top + 1);
		
		elementData[top++] = i;
	}
	
	public int pop() {
		int t = top - 1;
		int v = get(t);
		top = t;
		return v;
	}
	
	/**
	 * 弹出n个元素，返回最后被弹出的元素。
	 * @param n
	 * @return
	 */
	public int pop(int n) {
		int t = top - n;
		int v = get(t);
		top = t;
		return v;
	}
	
	private void ensureSize(int size) {
		if (elementData.length < size) {
			int newLength = Math.max(elementData.length << 1, size);
			int[] newData = new int[newLength];
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
