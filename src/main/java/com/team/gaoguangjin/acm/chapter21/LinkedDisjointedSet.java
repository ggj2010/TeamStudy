package com.team.gaoguangjin.acm.chapter21;

/**
 * 基于链表的可合并集合实现。
 * 
 * LinkedDisjointedSet的应用数据需要包含指向包含它的LinkedDisjointedSet的指针。
 */
public class LinkedDisjointedSet {
	private int size;
	private Node head;
	private Node tail;
	
	LinkedDisjointedSet() {}
	
	public LinkedDisjointedSet(AppData d) {
		size = 1;
		d.set = this;
		head = tail = new Node(d);
	}
	
	public int size() { return size; }
	
	public void union(LinkedDisjointedSet set) {
		if (set == this) throw new IllegalArgumentException("Cannot union with itself");
		if (set == null) return;
		
		tail.next = set.head;
		tail = set.tail;
		size += set.size;
		
		for (Node n = set.head; n != null; n = n.next) {
			n.data.set = this;
		}
	}
	
	public static LinkedDisjointedSet union(LinkedDisjointedSet set1, LinkedDisjointedSet set2) {
		if (set1 == null) return set2;
		if (set2 == null) return set1;
		if (set1.size < set2.size) {
			set2.union(set1);
			return set2;
		} else {
			set1.union(set2);
			return set1;
		}
	}
	
	public static AppData findSet(AppData d) {
		return d == null ? null : d.set.getRepresent();
	}
	
	public AppData getRepresent() {
		return head.data;
	}
	
	private static class Node {
		private AppData data;
		private Node next;
		
		public Node(AppData d) { data = d; }
		public String toString() { return data.toString(); }
	}
	
	public abstract static class AppData {
		protected LinkedDisjointedSet set;
		
		public void setSet(LinkedDisjointedSet s) { set = s; }
		public LinkedDisjointedSet getSet() { return set; }
	}
	
	void checkConstraint() {
		if (size == 0 || head == null || tail == null)
			throw new IllegalStateException("not allow empty set at present");
		if (tail.next != null) {
			throw new IllegalStateException("tail node's next node must be null");
		}
		int actualSize = 0;
		for (Node n = head; n != null; n = n.next) {
			actualSize++;
			if (n.data.set != this) {
				throw new IllegalStateException(String.format("for node(%s), its appdata's set it is not the set that contained it", n));
			}
		}
		if (size != actualSize) {
			throw new IllegalStateException(String.format("expected size is %d, but is %d", size, actualSize));
		}
	}
}
