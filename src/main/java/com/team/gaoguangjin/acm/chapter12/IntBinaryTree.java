package com.team.gaoguangjin.acm.chapter12;

import java.util.IdentityHashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class IntBinaryTree {
	private static final boolean CHECK_INVARIANT = true;
	
	private Node root;
	private int size;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private Node minimumNode(Node node) {
		assert node != null;
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public int minimum() {
		if (size == 0) throw new IllegalStateException("no data");
		return minimumNode(root).data;
	}
	
	private Node maximumNode(Node node) {
		assert node != null;
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public int maximum() {
		if (size == 0) throw new IllegalStateException("no data");
		return maximumNode(root).data;
	}
	
	private Node successorNode(Node node) {
		assert node != null;
		if (node.right != null) {
			return minimumNode(node.right);
		} else {
			Node parent = node.parent;
			while (parent != null && parent.right == node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	public IntIterator ascendingIterator() {
		return new IntIterator() {
			private Node next = root == null ? null : minimumNode(root);

			public boolean hasNext() {
				return next != null;
			}

			public int next() {
				if (next == null) throw new NoSuchElementException();
				int result = next.data;
				next = successorNode(next);
				return result;
			}
		};
	}
	
	private Node predecessorNode(Node node) {
		assert node != null;
		if (node.left != null) {
			return maximumNode(node.left);
		} else {
			Node parent = node.parent;
			while (parent != null && parent.left == node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	public IntIterator desendingIterator() {
		return new IntIterator() {
			private Node next = root == null ? null : maximumNode(root);
			
			public boolean hasNext() {
				return next != null;
			}

			public int next() {
				if (next == null) throw new NoSuchElementException();
				int result = next.data;
				next = predecessorNode(next);
				return result;
			}
		};
	}
	
	Node searchNode(int d) {
		return searchNode(root, d);
	}
	
	private Node searchNode(Node node, int d) {
		while (node != null && node.data != d) {
			if (d < node.data) node = node.left;
			else node = node.right;
		}
		return node;
	}

	public void insert(int d) {
		 Node prev = null;
		 Node cur = root;
		 while (cur != null) {
			 prev = cur;
			 if (d < cur.data) {
				 cur = cur.left;
			 } else {
				 cur = cur.right;
			 }
		 }
		 if (prev == null) {	
			 root = new Node(d);
		 } else {
			 Node newNode = new Node(d, prev);
			 if (d < prev.data) {
				 prev.left = newNode;
			 } else {
				 prev.right = newNode;
			 }
		 }
		 size++;
		 
		 if (CHECK_INVARIANT) checkInvarient();
	}
	
	public boolean delete(int d) {
		Node node = searchNode(d);
		if (node != null) {
			delete(node);
			if (CHECK_INVARIANT) checkInvarient();
			return true;
		} else {
			return false;
		}
	}
	
	private void delete(Node node) {
		assert node != null;
		Node d; // d为要删除的结点
		if (node.left == null || node.right == null) {
			d = node;
		} else {
			d = successorNode(node);
		}
		Node c = d.left != null ? d.left : d.right; // c为要链接到的子结点
		if (c != null) c.parent = d.parent;
		
		if (d.parent == null) {
			root = c;
		} else {
			if (d.parent.left == d) {
				d.parent.left = c;
			} else {
				d.parent.right = c;
			}
		}
		
		if (d != node) {
			node.data = d.data;
		}
		size--;
	}
	
	private void checkInvarient() {
		IdentityHashMap<Node, Node> set = new IdentityHashMap<Node, Node>();
		checkLeftRightParentPointer(root, set);
		
		if (set.size() != size) {
			throw new IllegalStateException(String.format("actual size(%d) is not the expected size(%d)", set.size(), size));
		}
	}
	
	/**
	 * 检查left, right, parent指针之间的引用关系是否正确。
	 */
	private void checkLeftRightParentPointer(Node node, IdentityHashMap<Node, Node> set) {
		if (node == null) return;
		if (set.containsKey(node)) {
			throw new IllegalStateException("circular reference detected!");
		}
		set.put(node, node);
		
		Node l = node.left; Node r = node.right;
		if (l != null) {
			 if (l.parent != node) {
				 throw new IllegalStateException("left-parent relation violated");
			 }
			 if (l.data > node.data) {
				 throw new IllegalStateException(String.format("left node(%s) > parent node(%s)", l, node));
			 }
		}
		if (r != null) {
			if (r.parent != node) {
				throw new IllegalStateException("right-parent relation violated");
			}
			 if (r.data < node.data) {
				 throw new IllegalStateException(String.format("right node(%s) < parent node(%s)", r, node));
			 }
		}
		
		checkLeftRightParentPointer(node.left, set);
		checkLeftRightParentPointer(node.right, set);
	}
	
	public String toString() {
		if (root == null) return "[empty]";
		StringBuilder sb = new StringBuilder();
		Node node = minimumNode(root);
		while (node != null) {
			sb.append(String.format("%d: %s %s %s%n", node.data,
					node.left == null ? "-" : node.left.data,
					node.right == null ? "-" : node.right.data,
					node.parent == null ? "-" : node.parent.data));
			node = successorNode(node);
		}
		return sb.toString();
	}
	
	private static class Node {
		private int data;
		private Node left;
		private Node right;
		private Node parent;
		
		public Node(int d) { data = d; }
		public Node(int d, Node p) { data = d; parent = p; }
		public Node(int d, Node p, Node l, Node r) {
			data = d; parent = p; left = l; right = r;
		}
		public String toString() { return Integer.toString(data); }
	}
	
	public static void main(String[] args) {
		IntBinaryTree btree = new IntBinaryTree();
		
		int[] data = new int[] { 8, 9, 6, 5, 13, 7};
		for (int i = 0; i < data.length; i++) {
			btree.insert(data[i]);
		}
		
		System.out.println("tree: " + btree);
		
		System.out.println("minimum: " + btree.minimum());
		System.out.println("maximum: " + btree.maximum());
		
		System.out.println("ascendingIterator");
		for (IntIterator itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.println(itor.next());
		}

		System.out.println("desendingIterator");
		for (IntIterator itor = btree.desendingIterator(); itor.hasNext();) {
			System.out.println(itor.next());
		}
		
		btree.delete(6);
		System.out.println("ascendingIterator");
		for (IntIterator itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.println(itor.next());
		}
		
		
		btree = new IntBinaryTree();
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			btree.insert(random.nextInt(200));
		}
		for (int i = 0; i < 200; i++) {
			btree.delete(random.nextInt(200));
		}
	}
}
