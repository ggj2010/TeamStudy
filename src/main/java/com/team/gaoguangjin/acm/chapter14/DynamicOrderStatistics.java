package com.team.gaoguangjin.acm.chapter14;

import java.util.IdentityHashMap;


public class DynamicOrderStatistics<T> extends BaseRedBlackTree<T> {
	
	/**
	 * 从整棵树中选择第i小的元素。要选择最小的元素，设置i为1。
	 */
	public T select(int i) {
		if (i < 1 || i > size) {
			throw new IndexOutOfBoundsException(i + " index out of bound");
		}
		return select((Node<T>) root, i);
	}
	
	/**
	 * 从以node为根的树中选择第i小的元素。要选择最小的元素，设置i为1。
	 */
	private T select(Node<T> node, int i) {
		int r = ((Node<T>)node.getLeft()).size + 1;
		if (i == r) {
			return node.getKey();
		}
		if (i < r) {
			return select((Node<T>) node.getLeft(), i);
		} else {
			return select((Node<T>) node.getRight(), i - r);
		}
	}

	
	@Override
	protected Node<T> createNode(T d, Color c, BaseNode<T> p, BaseNode<T> l, BaseNode<T> r) {
		return new Node<T>(d, c, (Node<T>)p, (Node<T>)l, (Node<T>)r, 0);
	}
	
	@Override
	protected void beforeInsertFixUp(BaseNode<T> node) {
		while (node != nullNode) {
			((Node<T>)node).size++;
			node = node.getParent();
		}
	}
	
	@Override
	protected void afterLeftRotate(BaseNode<T> oldRoot, BaseNode<T> newRoot) {
		Node<T> x = (Node<T>) oldRoot;
		Node<T> y = (Node<T>) newRoot;
		y.size = x.size;
		x.size = ((Node<T>) x.getLeft()).size + ((Node<T>) x.getRight()).size + 1;
	}
	

	@Override
	protected void afterRightRotate(BaseNode<T> oldRoot, BaseNode<T> newRoot) {
		afterLeftRotate(oldRoot, newRoot);
	}
	
	@Override
	protected void checkNode(BaseNode<T> node, IdentityHashMap<BaseNode<T>, Integer> blackHeightMap) {
		super.checkNode(node, blackHeightMap);
		Node<T> n = (Node<T>) node;
		Node<T> l = (Node<T>) n.getLeft();
		Node<T> r = (Node<T>) n.getRight();
		if (n != nullNode) {
			if (n.size != l.size + r.size + 1) {
				throw new IllegalStateException(String.format("for node(%s), n.size(%d) != l.size(%d) + r.size(%d) + 1", n, n.size, l.size, r.size));
			}
		}
	}
	
	@Override
	protected String toString(BaseNode<T> node) {
		return super.toString(node) + 
			String.format("(size=%d)", ((Node<T>)node).size);
	}
	
	private static class Node<T> extends BaseNode<T> {
		private int size;
		
		public Node(T k, Color c, Node<T> p, Node<T> l, Node<T> r, int s) {
			super(k, c, p, l, r);
			this.size = s;
		}
	}
	
	public static void main(String[] args) {
		DynamicOrderStatistics<Integer> btree = new DynamicOrderStatistics<Integer>();
		
		int[] data = new int[] //{  5, 6, 7, 8, 9, 13};
			{20, 47, 27, 4, 23, 42, 0, 14, 18};
		for (int i = 0; i < data.length; i++) {
			System.out.println("inserting " + data[i]);
			btree.insert(data[i]);
		}
		System.out.println("height: " + btree.getHeight());
		
		System.out.println(btree);
		
		for (int i = 1; i <= data.length; i++) {
			System.out.printf("the %dth element: %d%n", i, btree.select(i));
		}
	}
}
