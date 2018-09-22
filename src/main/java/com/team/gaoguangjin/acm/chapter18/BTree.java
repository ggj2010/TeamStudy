package com.team.gaoguangjin.acm.chapter18;

import java.util.HashMap;
import java.util.Map;

/**
 * 关键字为整数的BTree的模拟实现，没有磁盘的读写操作。
 * 
 * 注意事项：
 * 1. 实现时应该注意释放没有使用的孩子结点。由于这里的关键字是整数，所以没有必要释放没有使用的关键字，
 * 	  然而在实际中，关键字不可能为整数，那么也要释放没有使用的关键字。
 * 2. 对关键字的查找使用的是顺序查找，要加快速度，可以使用二分查找法。
 */
public class BTree {
	private static final int DEFAULT_MINIMUM_DEGREE = 2;
	
	private Node root;
	private int size;
	private int degree; // minimum-degree
	
	public BTree() { this(DEFAULT_MINIMUM_DEGREE); }
	
	public BTree(int minimumDgree) {
		degree = minimumDgree;
		size = 0;
		root = new Node(0, true, degree << 1);
	}
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	
	public NodeKey search(int k) {
		return search(root, k);
	}
	
	private NodeKey search(Node node, int k) {
		int i = 0;
		for (; i < node.num && k > node.key[i]; i++) {
			continue;
		}
		if (i < node.num && k == node.key[i]) {
			return new NodeKey(node, i);
		} else {
			if (node.leaf) return null;
			else return search(node.children[i], k);
		}
	}
	
	/**
	 * 分裂结点x的第i个孩子结点，假设该孩子结点是满的，并且x结点不是满的。
	 */
	private void split(Node x, int i) {
		Node y = x.children[i];
		assert x.num < (degree << 1) -1 : "x should not be full";
		assert y.num == (degree << 1) -1 : "y should be full";
		
		Node z = new Node(degree - 1, y.leaf, degree << 1);
		for (int j = 0; j < degree - 1; j++) {
			z.key[j] = y.key[j + degree];
		}
		if (!y.leaf) {
			for (int j = 0; j < degree; j++) {
				z.children[j] = y.children[j + degree];
			}
			for (int j = degree; j < (degree << 1); j++) { // free unused node
				y.children[j] = null;
			}
		}
		
		y.num = degree - 1;
		
		for (int j = x.num; j > i; j--) {
			x.children[j+1] = x.children[j];
		}
		x.children[i+1] = z;
		for (int j = x.num - 1; j >= i; j--) {
			x.key[j+1] = x.key[j];
		}
		x.key[i] = y.key[degree - 1];
		x.num++;
	}
	
	public void insert(int k) {
		Node r = root;
		if (r.num == (degree<<1)-1) {
			root = new Node(0, false, degree<<1);
			root.children[0] = r;
			split(root, 0);
		}
		insertNonFull(root, k);
		size++;
	}
	
	private void insertNonFull(Node x, int k) {
		assert x.num < (degree<<1)-1 : "x should not be full";
		
		int i = x.num - 1;
		if (x.leaf) {
			while (i >= 0 && x.key[i] > k) {
				x.key[i+1] = x.key[i];
				i--;
			}
			x.key[i + 1] = k;
			x.num++;
		} else {
			while (i >= 0 && x.key[i] > k) {
				i--;
			}
			i++;
			if (x.children[i].num == (degree<<1)-1) {
				split(x, i);
				if (k > x.key[i]) i++;
			}
			insertNonFull(x.children[i], k);
		}
	}
	
	public boolean delete(int k) {
		boolean result = delete(root, k);
		if (result) size--;
		return result;
	}
	
	private boolean delete(Node x, int k) {
		assert x == root || x.num >= degree;
		
		int i = 0;
		for (; i < x.num && k > x.key[i]; i++) {
			continue;
		}
		if (i < x.num && k == x.key[i]) {
			if (x.leaf) {	// case 1
				for (int j = i+1; j < x.num; j++) {
					x.key[j-1] = x.key[j];
				}
				x.num--;
			} else {		// case 2
				Node y = x.children[i];
				Node z = x.children[i+1];
				if (y.num >= degree) { // case 2a, 书中说可以在一趟过程中完成删除，但是这难免要重写一遍delete算法
									   // 这里用的方法是首先在一趟遍历中找到前驱结点，然后再递归调用delete方法删除结点
					int k_ = maxKey(y);	// 要删除的key
					delete(y, k_);
					x.key[i] = k_;
				} else if (z.num >= degree) { // case 2b，同case2a，遍历了两次
					int k_ = minKey(z);
					delete(z, k_);
					x.key[i] = k_;
				} else {	// case 2c
					merge(x, i);
					delete(x.children[i], k);
				}
			}
			return true;
		} else if (x.leaf){
			return false;
		} else {
			Node y = x.children[i];
			if (x.children[i].num == degree-1) { // case 3
				if (i > 0 && x.children[i-1].num >= degree) { // case 3a
					rightRotate(x, i-1);
				} else if (i < x.num && x.children[i+1].num >= degree) { // case 3a
					leftRotate(x, i);
				} else { // case 3b
					if (i < x.num) {
						y = merge(x, i);
					} else {
						y = merge(x, i-1);
					}
				}
			}
			return delete(y, k);
		}
	}
	
	/**
	 * 合并结点x的第i个关键字的左右两个孩子结点，假设结点x至少包含degree个关键字，两个孩子结点的关键字数均为degree-1。
	 */
	private Node merge(Node x, int i) {
		assert x == root || x.num >= degree;
		assert x.children[i].num == degree-1 && x.children[i+1].num == degree-1;
		
		Node y = x.children[i];
		Node z = x.children[i+1];
		int k = x.key[i];
		
		// move x.key[i+1..num-1] to x.key[i..num-2]
		for (int j = i+1; j < x.num; j++) {
			x.key[j-1] = x.key[j];
		}
		// move x.children[i+2..num] to x.children[i+1..num-1]
		for (int j = i+2; j <= x.num; j++) {
			x.children[j-1] = x.children[j];
		}
		x.children[x.num] = null; 	// free node
		x.num--;
		
		y.key[degree-1] = k;
		// move z.key[0..num-1] to y.key[degree..2*degree-2]
		for (int j = 0; j < z.num; j++) {
			y.key[degree+j] = z.key[j];
		}
		y.num = (degree << 1) -1;
		if (!y.leaf) {
			// move z.children[0..num] to y.children[degree..2*degree-1]
			for (int j = 0; j <= z.num; j++) {
				y.children[degree+j] = z.children[j];
			}
			// no need to free z.children, because there are no references to z after this function.
		}
		if (x == root && root.num == 0) { // delete root if it has no key
			root = y;
		}
		return y;
	}
	
	/**
	 * 名字从二叉树的leftRotate借鉴过来，与二叉树的leftRoate类似，它也不改变B树的性质。
	 * 
	 * 它将结点x的第i个关键字移到该关键字的左孩子结点中，将右孩子的第0个关键字移到x结点中。
	 * 它将使左孩子结点的关键字数增1，右孩子结点的关键字数减1。该方法必须要求左孩子结点不是
	 * 满的，而右孩子结点必须比最少的关键字数多1，即至少为degree。
	 */
	private void leftRotate(Node x, int i) {
		assert x.children[i].num < (2*degree-1);
		assert x.children[i+1].num >= degree;
		
		Node y = x.children[i];
		Node z = x.children[i+1];
		y.key[y.num] = x.key[i];
		y.num++;
		if (!y.leaf) {
			y.children[y.num] = z.children[0];
		}
		
		x.key[i] = z.key[0];
		
		for (int j = 1; j < z.num; j++) {
			z.key[j-1] = z.key[j];
		}
		if (!z.leaf) {
			for (int j = 1; j <= z.num; j++) {
				z.children[j-1] = z.children[j];
			}
			z.children[z.num] = null; // free node
		}
		z.num--;
	}
	
	/**
	 * 与leftRotate对称。
	 */
	private void rightRotate(Node x, int i) {
		assert x.children[i].num >= degree;
		assert x.children[i+1].num < (2*degree-1);
		
		Node y = x.children[i];
		Node z = x.children[i+1];
		
		for (int j = z.num-1; j >= 0; j--) {
			z.key[j+1] = z.key[j];
		}
		z.key[0] = x.key[i];
		if (!z.leaf) {
			for (int j = z.num; j >= 0; j--) {
				z.children[j+1] = z.children[j];
			}
			z.children[0] = y.children[y.num];
		}
		z.num++;
		
		x.key[i] = y.key[y.num-1];
		
		if (!y.leaf) {
			y.children[y.num] = null; // free node
		}
		y.num--;
	}
	
	private int maxKey(Node x) {
		int max = x.key[x.num-1];
		while (!x.leaf) {
			x = x.children[x.num];
			if (max < x.key[x.num-1])
				max = x.key[x.num-1];
		}
		return max;
	}
	private int minKey(Node x) {
		int min = x.key[0];
		while (!x.leaf) {
			x = x.children[0];
			if (min > x.key[0])
				min = x.key[0];
		}
		return min;
	}
	
	
	////////////// 验证数据结构的方法，包私有，在单元测试中使用 //////////////////////
	void checkConstraint() {
		Map<Node, Integer> heights = new HashMap<Node, Integer>();
		heights.put(root, 0);
		checkConstraint(root, heights);
		
		int height = -1;
		for (Node node : heights.keySet()) {
			if (node.leaf) {
				if (height == -1) {
					height = heights.get(node);
				} else if (heights.get(node) != height) {
					throw new IllegalStateException("the leaf nodes should have the same height");
				}
			}
		}
	}
	void checkConstraint(Node node, Map<Node, Integer> heights) {
		if (node == null) return;
		
		checkNode(node);
		if (!node.leaf) {
			for (int i = 0; i < node.num + 1; i++) {
				heights.put(node.children[i], heights.get(node) + 1);
				checkConstraint(node.children[i], heights);
			}
		}
	}
	
	private void checkNode(Node node) {
		if (node == root && size > 0 && node.num == 0) { // 非空树的根结点至少包含一个关键字
			throw new IllegalStateException(String.format("for non-empty tree, root node must contain at least one key"));
		}		
		if (node != root && node.num < degree-1) { // 非根结点必须至少包含t-1个关键字
			throw new IllegalStateException(String.format("node %s only contains %d keys, it should contains at least %d", node, node.num, (degree-1)));
		}
		if (node.num > (degree*2-1)) { // 所有结点至多包含2t-1个关键字
			throw new IllegalStateException(String.format("node %s contains %d keys, it should contains at most %d", node, node.num, (degree*2-1)));
		}
		if (node.leaf && node.children != null) { // 叶结点不能有孩子
			throw new IllegalStateException(String.format("node %s is leaft, but the children is not null", node));
		}
		if (node.key.length < node.num) { // 关键字数组必须能够容纳所有关键字
			throw new IllegalStateException(String.format("node(%s).key(length %d) must can holds %d keys", node, node.key.length, node.num));
		}
		for (int i = 0; i < node.num -1; i++) { // 关键字必须按顺序排列
			if (node.key[i] > node.key[i+1]) {
				throw new IllegalStateException(String.format("node(%s).key(%s) is ordered", node, arrayToString(node.key, node.num)));
			}
		}
		if (!node.leaf) {
			if (node.children.length < node.num + 1) { // 孩子数组必须能够容纳所有孩子
				throw new IllegalStateException(String.format("node(%s).children(length %d) must can holds %d keys", node, node.children.length, node.num + 1));
			}
			for (int i = node.num + 1; i < node.children.length; i++) { // 释放没有使用的结点，否则可能造成内存泄露
				if (node.children[i] != null)
					throw new IllegalStateException("you don't free unused node?");
			}
			for (int i = 0; i < node.num; i++) {
				if (node.children[i].key[node.children[i].num-1] > node.key[i]) {
					throw new IllegalStateException(String.format("node.children[i].key[node.children[i].num-1] > node.key[i]"));
				}
				if (node.children[i+1].key[0] < node.key[i]) {
					throw new IllegalStateException(String.format("node.children[i+1].key[0] < node.key[i]"));
				}
			}
		}
	}
	
	private static String arrayToString(int[] a, int len) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < len; i++) sb.append(a[i]).append(",");
		sb.append("]");
		return sb.toString();
	}
	
	public static class Node {
		int num;			// 关键字的个数
		int[] key;			// 所有关键字
		boolean leaf;		// 是否为叶结点
		Node[] children;	// 所有孩子结点

		Node() {}
		Node(int num, boolean leaf, int maxChildren) {
			this.num = num;
			this.leaf = leaf;
			key = new int[maxChildren - 1];
			if (!leaf) children = new Node[maxChildren];
		}
		public String toString() { return arrayToString(key, num); }
	}
	
	/**
	 * 表示结点中的一个关键字，由结点和关键字索引组成。
	 */
	public static class NodeKey {
		Node node;
		int keyIndex;
		
		public NodeKey(Node node, int keyIndex) { this.node = node; this.keyIndex = keyIndex; }
		public String toString() { return node +"@" + keyIndex; }
		public boolean equals(Object o) { return equals((NodeKey)o); }
		private boolean equals(NodeKey o) { return node == o.node && keyIndex == o.keyIndex; }
		public int hashCode() { return (node==null?0:node.hashCode()) ^ keyIndex; }
	}
	
}
