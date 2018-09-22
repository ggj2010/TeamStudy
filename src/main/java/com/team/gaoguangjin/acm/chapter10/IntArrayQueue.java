package com.team.gaoguangjin.acm.chapter10;

public class IntArrayQueue {
	private static final int INITIAL_SIZE = 8;
	
	private static final boolean CHECK_INVARIANT = true;
	
	private int[] elementData;
	private int head; // 队列头
	private int tail; // 队列尾，指向下一个空位置
	private int size;
	
	public IntArrayQueue(int initialSize) {
		elementData = new int[initialSize];
	}
	
	public IntArrayQueue() {
		this(INITIAL_SIZE);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enqueue(int i) {
		ensureSize(size + 1);
		elementData[tail] = i;
		tail = incAndRound(tail);
		size++;

		if (CHECK_INVARIANT) {
			checkInvariant();
		}
	}
	
	private int incAndRound(int i) {
		++i;
		if (i == elementData.length) {
			i = 0;
		}
		return i;
	}
	
	private void ensureSize(int newSize) {
		if (elementData.length < newSize) {
			int[] oldData = this.elementData;
			int[] newData = new int[Math.max(elementData.length << 1, newSize)];
			if (size == 0) {
				// do nothing
			} else if (tail > head) {
				System.arraycopy(oldData, head, newData, 0, size);
			} else {
				System.arraycopy(oldData, head, newData, 0, oldData.length - head);
				System.arraycopy(oldData, 0, newData, oldData.length - head, tail);
			}
			this.elementData = newData;
			head = 0;
			tail = size;
		}
	}

	public int dequeue() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Cannot dequeue empty queue");
		}
		int result = elementData[head];
		head = incAndRound(head);
		size--;
		
		if (CHECK_INVARIANT) {
			checkInvariant();
		}
		return result;
	}
	
	public int peek() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("queue is empty");
		}
		return elementData[head];
	}
	
	private void checkInvariant() {
		if (size == 0) {
			if (head != tail) {
				throw new RuntimeException("when size = 0, head(" + head + ") != tail(" + tail + ")");
			}
		} else {
			if (tail > head) {
				if (size != (tail - head)) {
					throw new RuntimeException("when tail > head, size(" + size + ") != tail(" + tail+") - head(" + head + ")");
				}
			} else {
				if (size != (tail - head + elementData.length)) {
					throw new RuntimeException("when tail <= head, size != tail(" + tail + ") - head(" + head + ") + elementData.length(" + elementData.length + ")");
				}
			}
		}
	}
}
