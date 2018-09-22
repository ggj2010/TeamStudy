package com.team.gaoguangjin.acm.chapter19;

import com.team.gaoguangjin.acm.chapter12.IntIterator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class BinomialHeap {
	private Node head;
	private int size;
	
	public BinomialHeap() {}
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	
	public int minimum() {
		if (head == null) throw new IndexOutOfBoundsException("empty heap");
		return minNode().key;
	}
	
	private Node minNode() {
		assert head != null;
		
		Node min = head;
		Node n = head.sibling;
		while (n != null) {
			if (min.key > n.key) min = n;
			n = n.sibling;
		}
		return min;
	}
	
	public void insert(int k) {
		BinomialHeap h = new BinomialHeap();
		h.head = new Node(k);
		h.size = 1;
		union(h);
	}
	
	public int extractMin() {
		if (head == null) throw new IndexOutOfBoundsException("empty heap");
		
		// remove minimum node from root list
		Node minPrevNode = null;
		Node minNode = this.head;
		Node prevNode = this.head;
		Node node = prevNode.sibling;
		while (node != null) {
			if (minNode.key > node.key) {
				minPrevNode = prevNode;
				minNode = node;
			}
			prevNode = node;
			node = node.sibling;
		}

		deleteRootNode(minPrevNode, minNode);
		
		return minNode.key;
	}

	private void deleteRootNode(Node prevRoot, Node root) {
		Node node;
		if (prevRoot == null) {
			this.head = root.sibling;
		} else {
			prevRoot.sibling = root.sibling;
		}
		
		// revere the order of linked list of root's children,
		// and set h.head to point to the head of the result list.
		BinomialHeap h = new BinomialHeap();
		for (node = root.child; node != null;) {
			Node nextNode = node.sibling;
			
			node.parent = null;		// free its parent node			
			node.sibling = h.head;
			h.head = node;
			
			node = nextNode;
		}

		int _size = this.size;
		union(h);		
		this.size = _size - 1;
	}
	
	public void union(BinomialHeap heap) {
		if (heap == this) {
			throw new IllegalArgumentException("cannot union with itself");
		}
		if (heap.head == null) return;
		if (this.head == null) {
			this.head = heap.head;
			this.size = heap.size;
			return;
		}
		merge(heap);
		
		Node xprev = null;
		Node x = this.head;
		Node xnext = x.sibling;
		
		while (xnext != null) {
			if (x.degree != xnext.degree || (xnext.sibling != null && xnext.sibling.degree == x.degree)) {
				xprev = x;
				x = xnext;
			} else if (x.key <= xnext.key) {
				x.sibling = xnext.sibling;
				link(xnext, x);
			} else {
				if (xprev == null) {
					this.head = xnext;
				} else {
					xprev.sibling = xnext;
				}
				link(x, xnext);
				x = xnext;
			}
			xnext = x.sibling;
		}
	}
	
	/**
	 * 将y为根的二项树和z为根的二项树连接，两者高度相同，连接之后的新树的高度增1。
	 * 它使z成为y的父结点，z成为新树的根。
	 */
	private Node link(Node y, Node z) {
		y.parent = z;
		y.sibling = z.child;
		z.child = y;
		z.degree++;
		return z;
	}
	
	/**
	 * 将heap和this的根结点合并成按度增加的链表。合并之后<code>heap</code>不应该再被使用。
	 */
	private void merge(BinomialHeap heap) {
		assert heap.head != null && this.head != null;
		
		Node n1 = this.head;
		Node n2 = heap.head;
		Node n;
		if (n1.degree < n2.degree) {
			n = n1;
			n1 = n1.sibling;
		} else {
			this.head = n2;
			n = n2;
			n2 = n2.sibling;
		}
		
		while (n1 != null && n2 != null) {
			if (n1.degree < n2.degree) {
				n.sibling = n1;
				n = n1;
				n1 = n1.sibling;
			} else {
				n.sibling = n2;
				n = n2;
				n2 = n2.sibling;
			}
		}
		if (n1 != null) n.sibling = n1;
		else n.sibling = n2;
		
		this.size += heap.size;
	}
	
	/** 
	 * 所有结点的遍历。先输出深度为0的结点，也就是根结点，然后输出深度为1的结点，依次下去。
	 */
	public IntIterator iterator() {
		return new IntIterator() {
			LinkedList<Node> items = new LinkedList<Node>();
			{
				for (Node node = head; node != null; node = node.sibling) {
					items.addLast(node);
				}
			}
			
			public boolean hasNext() {
				return items.size() > 0;
			}

			public int next() {
				Node n = items.removeFirst();
				if (n.child != null) {
					for (Node c = n.child; c != null; c = c.sibling) {
						items.addLast(c);
					}
				}
				return n.key;
			}
			
		};
	}
	
	public boolean contains(int k) {
		return search(k) != null;
	}
	
	/*
	 * 下面的方法是包私有的，它们要么返回Node对象，要么接受Node对象作为参数。我个人认为
	 * 暴露Node不可取，但是为了演示书中的例子，将它设置成包私有，这样就能够在单元测试中
	 * 做测试了。
	 */
	Node search(int k) {
		for (Node n = this.head; n != null; n = n.sibling) {
			Node r = search(n, k);
			if (r != null) return r;
		}
		return null;
	}
	
	private Node search(Node n, int k) {
		if (n.key == k) return n;
		if (k < n.key) return null;
		if (n.child != null) {
			for (Node c = n.child; c != null; c = c.sibling) {
				Node r = search(c, k);
				if (r != null) return r;
			}
		}
		return null;
	}
	
	void decreaseKey(Node x, int k) {
		if (k > x.key) {
			throw new IllegalArgumentException("must decrease key");
		}
		Node y = x;
		Node z = y.parent;
		while (z != null && y.key < z.key) {
			swapKey(y, z);
			y = z;
			z = z.parent;
		}
	}
	private void swapKey(Node y, Node z) {
		int temp = y.key; y.key = z.key; z.key = temp;
	}
	
	void delete(Node x) {
		assert x != null;
		
		while (x.parent != null) {
			swapKey(x, x.parent);
			x = x.parent;
		}
		Node xprev;
		if (x == this.head) xprev = null;
		else {
			for (xprev = this.head; xprev.sibling != x; xprev = xprev.sibling) {
				continue;
			}
			assert(xprev != null);
		}
		deleteRootNode(xprev, x);
	}
	
	/** 
	 * 二项堆的一个结点，非根结点是一棵二项树。
	 */
	static class Node {
		Node parent;
		Node child;
		Node sibling;
		int key;
		int degree;
		
		public Node(int k) { key = k; }
		public String toString() { return key+"@"+degree; }
	}
	
	void checkConstraint() {
		if ((size == 0) != (head == null)) {
			throw new IllegalStateException("size == 0 <=> head == null");
		}
		int expectedSize = 0;
		for (Node node = head; node != null; node = node.sibling) {
			Set<Node> nodes = new HashSet<Node>();
			checkNode(node, true, nodes);
			expectedSize += nodes.size();
		}
		if (size != expectedSize) {
			throw new IllegalStateException(String.format("expected size is %d, but is %d", expectedSize, size));
		}
	}
	
	void checkNode(Node node, boolean isRoot, Set<Node> nodes) {
		if (nodes.contains(node)) {
			throw new IllegalStateException("circular reference detected?");
		}
		nodes.add(node);
		
		if ((node.child == null) != (node.degree == 0)) { // 孩子结点为空  <=> 结点的度为零
			throw new IllegalStateException(String.format("for node(%s), (node.child == null) != (node.degree == 0)", node));
		}
		if (isRoot != (node.parent == null)) { // 结点为根结点 <=> 结点的父结点为空
			throw new IllegalStateException(String.format("for node(%s), isRoot != (node.parent == null)", node));
		}
		if (node.child != null && node.child.degree + 1 != node.degree) { // 结点的度比孩子的度多1
			throw new IllegalStateException(String.format("node.child.degree + 1 != node.degree"));
		}
		if (!isRoot && node.degree > 0 && (node.sibling.degree + 1 != node.degree)) { // 对非根结点，结点的度比兄弟结点多1；结点度大于0,那么必存在兄弟结点
			throw new IllegalStateException(String.format("for non-root node(%s), node.child.degree + 1 != node.degree", node));
		}
		if (isRoot && node.sibling != null && node.degree >= node.sibling.degree) { // 对根结点，结点的度比兄弟结点要少
			throw new IllegalStateException(String.format("for root node(%s), node.degree >= node.sibling.degree", node));
		}
		
		if (node.child != null) {
			for (Node c = node.child; c != null; c = c.sibling) {
				if (c.key < node.key) { // 子结点必须大于等于父结的key
					throw new IllegalStateException("child key must be bigger than or equal to parent key");
				}
				if (c.parent != node) {
					throw new IllegalStateException("child parent pointer violated!");
				}
				checkNode(c, false, nodes);
			}
		}
	}
}
