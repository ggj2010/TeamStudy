package com.team.gaoguangjin.acm.chapter17;

/**
 * 动态扩展和收缩的堆栈。 
 */
public class Stack {
	private static final boolean DEBUG = true;
	
	private int[] data;
	private int size;
	
	public Stack() {
		data = new int[8];
		size = 0;
	}
	
	public int size() {	return size; }	
	public boolean isEmpty() { return size == 0; }
	
	public int top() {
		if (size == 0) throw new IndexOutOfBoundsException("empty stack");
		
		return data[size-1];
	}
	
	public void push(int d) {
		if (size == data.length) {
			setCapacity(size << 1);
		}
		data[size++] = d;
	}
	
	public int pop() {
		if (size == 0) throw new IndexOutOfBoundsException("empty stack");
		
		int result = data[--size];
		
		if (data.length > 8 && (size << 2) < data.length) { // 不到1/4的数据
			setCapacity(data.length >> 1);
		}
		return result;
	}
	
	private void setCapacity(int newCap) {
		if (DEBUG) System.out.println("change capacity from " + data.length + " to " + newCap);
		
		int[] newData = new int[newCap];
		System.arraycopy(data, 0, newData, 0, Math.min(data.length, newCap));
		this.data = newData;
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(3); s.push(5);
		System.out.println(s.pop());
		System.out.println(s.pop());
		
		for (int i = 0; i < 100; i++) {
			s.push(i);
		}
		for (int i = 99; i >=0; i--) {
			if (i != s.pop()) {
				throw new AssertionError();
			}
		}
	}
}
