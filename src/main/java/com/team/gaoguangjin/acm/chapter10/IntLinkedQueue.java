package com.team.gaoguangjin.acm.chapter10;


public class IntLinkedQueue {
public static final boolean CHECK_INVARIANT = true;
	
	private Node head;
	private Node tail;
	private int size;
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	
	// 从尾部添加元素
	public void enqueue(int d) {
		Node newNode = new Node(d, null, tail);
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		
		if (CHECK_INVARIANT) checkInvariant();
	}
	
	public int peek() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("empty queue");
		}
		return head.data;
	}
	
	// 从头部移去元素
	public int dequeue() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("empty queue");
		}
		int data = head.data;
		if (size == 1) {
			head = tail = null;
		} else {
			Node next = head.next;
			next.prev = null;
			head = next;
		}
		size--;
		if (CHECK_INVARIANT) checkInvariant();
		return data;
	}
	
	private void checkInvariant() {
		if (size == 0) {
			if (head != null || tail != null) {
				throw new RuntimeException(String.format(
						"size is zero, but head or tail is not null"));
			}
		} else {
			if (head.prev != null) {
				throw new RuntimeException("head.prev must be null");
			}
			if (tail.next != null) {
				throw new RuntimeException("tail.next must be null");
			}
			
			int actualSize = 0;
			Node node = head;
			while (node != null) {
				actualSize++;
				Node next = node.next;
				if (next != null && next.prev != node) {
					throw new RuntimeException(String.format("for node %s, node.next.prev is not node", node));
				}
				if (next == null && node != tail) {
					throw new RuntimeException(String.format("the last node(%s) is not the tail node(%s)", node, tail));
				}
				node = next;
			}
			
			if (actualSize != size) {
				throw new RuntimeException(String.format("acutual size(%d) is not expected size(%d)", actualSize, size));
			}
		}
	}

	/*
	 * 其实要实现Queue，单向链表就够了。 
	 */
	private static class Node {
		private int data;
		private Node next;
		private Node prev;
		
		public Node(int data) { this.data = data;}
		public Node(int data, Node next, Node prev) {
			this.data = data; this.next = next; this.prev = prev;
		}
		public String toString() {
			return String.format("[%d]", data);
		}
	}
}
