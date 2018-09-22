package com.team.gaoguangjin.acm.chapter10;

public class IntLinkedStack {
	public static final boolean CHECK_INVARIANT = true;
	
	private Node head;
	private int size;
	
	public IntLinkedStack() {}

	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public int top() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("empty stack");
		}
		return head.data;
	}
	
	public int pop() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("empty stack");
		}
		int data = head.data;
		head = head.next;
		if (head != null) {
			head.prev = null;
		}
		size--;
		if (CHECK_INVARIANT) checkInvariant();
		return data;
	}
	
	public void push(int d) {
		Node newNode = new Node(d, head, null);
		if (head == null) {
			head = newNode;
		} else {
			head.prev = newNode;
			head = newNode;
		}
		size++;
		if (CHECK_INVARIANT) checkInvariant();
	}
	
	private void checkInvariant() {
		if (head == null) {
			if (size != 0) {
				throw new RuntimeException(String.format(
						"head is null, but size(%d) is not zero", size));
			}
		} else {
			if (head.prev != null) {
				throw new RuntimeException("head.prev must be null");
			}
			
			int actualSize = 0;
			Node node = head;
			while (node != null) {
				actualSize++;
				Node next = node.next;
				if (next != null && next.prev != node) {
					throw new RuntimeException(String.format("for node %s, node.next.prev is not node", node));
				}
				node = next;
			}
			
			if (actualSize != size) {
				throw new RuntimeException(String.format("acutual size(%d) is not expected size(%d)", actualSize, size));
			}
		}
	}
	
	/*
	 * 其实要实现Stack，单向链表就够了。 
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
