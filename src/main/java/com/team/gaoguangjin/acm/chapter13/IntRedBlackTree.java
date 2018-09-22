package com.team.gaoguangjin.acm.chapter13;

import com.team.gaoguangjin.acm.chapter12.IntIterator;

import java.util.IdentityHashMap;
import java.util.NoSuchElementException;
import java.util.Random;


public class IntRedBlackTree {
	private static final Node NULL_NODE = new Node(-1, Color.BLACK);
/*	static {
		NULL_NODE.parent = NULL_NODE;
		NULL_NODE.left = NULL_NODE;
		NULL_NODE.right = NULL_NODE;
	}*/
	
	private static final boolean CHECK_INVARIATN = true;
	
	private Node root = NULL_NODE;
	private int size;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	private Node minimumNode(Node node) {
		assert node != NULL_NODE;
		while (node.left != NULL_NODE) {
			node = node.left;
		}
		return node;
	}
	
	public int minimum() {
		if (size == 0) throw new IllegalStateException("no data");
		return minimumNode(root).data;
	}
	
	private Node maximumNode(Node node) {
		assert node != NULL_NODE;
		while (node.right != NULL_NODE) {
			node = node.right;
		}
		return node;
	}
	
	public int maximum() {
		if (size == 0) throw new IllegalStateException("no data");
		return maximumNode(root).data;
	}
	
	private Node successorNode(Node node) {
		assert node != NULL_NODE;
		if (node.right != NULL_NODE) {
			return minimumNode(node.right);
		} else {
			Node parent = node.parent;
			while (parent != NULL_NODE && parent.right == node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	public IntIterator ascendingIterator() {
		return new IntIterator() {
			private Node next = root == NULL_NODE ? NULL_NODE : minimumNode(root);

			public boolean hasNext() {
				return next != NULL_NODE;
			}

			public int next() {
				if (next == NULL_NODE) throw new NoSuchElementException();
				int result = next.data;
				next = successorNode(next);
				return result;
			}
		};
	}
	
	private Node predecessorNode(Node node) {
		assert node != NULL_NODE;
		if (node.left != NULL_NODE) {
			return maximumNode(node.left);
		} else {
			Node parent = node.parent;
			while (parent != NULL_NODE && parent.left == node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	public IntIterator desendingIterator() {
		return new IntIterator() {
			private Node next = root == NULL_NODE ? NULL_NODE : maximumNode(root);
			
			public boolean hasNext() {
				return next != NULL_NODE;
			}

			public int next() {
				if (next == NULL_NODE) throw new NoSuchElementException();
				int result = next.data;
				next = predecessorNode(next);
				return result;
			}
		};
	}

	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(Node node) {
		if (node == NULL_NODE) return 0;
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
	
	Node searchNode(int d) {
		return searchNode(root, d);
	}
	
	private Node searchNode(Node node, int d) {
		while (node != NULL_NODE && node.data != d) {
			if (d < node.data) node = node.left;
			else node = node.right;
		}
		return node;
	}

	private void leftRotate(final Node x) {
		assert x != NULL_NODE && x.right != NULL_NODE;
		final Node y = x.right;
		x.right = y.left;
		if (y.left != NULL_NODE) y.left.parent = x; // 这里对y.left的测试是必须的，书上认为不需要测试，这是不正确的。
							// 这里的关键是如果不测试则有可能改变NULL_NODE的parent值，而delete(Node)中调用deleteFixUp
							// 之前也可能改变NULL_NODE的parent的值，在deleteFixUp中多次调用leftRotate, rightRotate
							// 将NULL_NODE设置成另外一个值。比如在case 3中，如果x为NULL_NODE(这是可能的）,调用rightRotate之后，
							// 会改变NULL_NODE的parent，也就是改变了x.parent值，之后调用w=x.parent.right就得不到正确的值了。
							// 试着去掉测试语句，将会出现运行错误。

		y.parent = x.parent;
		if (x.parent != NULL_NODE) {
			if (x.parent.left == x) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		} else {
			this.root = y;
		}
		
		y.left = x;
		x.parent = y;
	}
	
	private void rightRotate(final Node x) {
		assert x != NULL_NODE && x.left != NULL_NODE;
		final Node y = x.left;
		x.left = y.right;
		if (y.right != NULL_NODE) y.right.parent = x; // 这里对y.right的测试是必须的，见leftRotate

		y.parent = x.parent;
		if (x.parent != NULL_NODE) {
			if (x.parent.left == x) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		} else {
			this.root = y;
		}
		
		y.right = x;
		x.parent = y;
	}
	
	public void insert(int d) {
		 Node prev = NULL_NODE;
		 Node cur = root;
		 while (cur != NULL_NODE) {
			 prev = cur;
			 if (d < cur.data) {
				 cur = cur.left;
			 } else {
				 cur = cur.right;
			 }
		 }
		 
		 Node newNode;
		 if (prev == NULL_NODE) {
			 root = newNode = new Node(d, Color.RED);
		 } else {
			 newNode = new Node(d, Color.RED, prev);
			 if (d < prev.data) {
				 prev.left = newNode;
			 } else {
				 prev.right = newNode;
			 }
		 }
		 size++;
		 
		 insertFixup(newNode);
		 if (CHECK_INVARIATN) checkInvarient();
	}
	
	private void insertFixup(Node z) {
		while (z.parent.color == Color.RED) {
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right;
				if (y.color == Color.RED) {
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.right) {
						z = z.parent;
						leftRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					rightRotate(z.parent.parent);
				}
			} else {
				Node y = z.parent.parent.left;
				if (y.color == Color.RED) {
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						rightRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		root.color = Color.BLACK;
	}
	
	public boolean delete(int d) {
		Node node = searchNode(d);
		if (node != NULL_NODE) {
			delete(node);
			return true;
		} else {
			return false;
		}
	}
	
	private void delete(Node node) {
		assert node != NULL_NODE;
		Node d; // d为要删除的结点
		if (node.left == NULL_NODE || node.right == NULL_NODE) {
			d = node;
		} else {
			d = successorNode(node);
		}
		Node c = d.left != NULL_NODE ? d.left : d.right; // c为要链接到的子结点
		c.parent = d.parent; // 不需要检测c是否为NULL_NODE，deleteFixup方法依赖于这里设置的值
		
		if (d.parent == NULL_NODE) {
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

		if (d.color == Color.BLACK) {
			deleteFixup(c);
		}
		
		if (CHECK_INVARIATN) checkInvarient();
	}
	
	private void deleteFixup(Node x) {
		while (x != root && x.color == Color.BLACK) {
			if (x == x.parent.left) {
				Node w = x.parent.right;
				// case 1
				if (w.color == Color.RED) {
					assert x.parent != NULL_NODE && x.parent.color == Color.BLACK;
					assert w.left != NULL_NODE && w.left.color == Color.BLACK;
					assert w.right != NULL_NODE && w.right.color == Color.BLACK;
					
					w.color = Color.BLACK;
					x.parent.color = Color.RED;
					leftRotate(x.parent);
					w = x.parent.right;

					assert w != NULL_NODE && w.color == Color.BLACK; // case 1结束时w.color为黑色从而进入case2,3或4
				}
				
				// case 2
				if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.parent;
				} else {
					// case 3
					if (w.right.color == Color.BLACK) {
						assert w.left != NULL_NODE && w.left.color == Color.RED;
						
						w.left.color = Color.BLACK;
						w.color = Color.RED;
						rightRotate(w);
						w = x.parent.right;
					}
					
					// case 4
					assert (w.right.color == Color.RED);
					w.color = x.parent.color;
					x.parent.color = Color.BLACK;
					w.right.color = Color.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				Node w = x.parent.left;
				// case 1
				if (w.color == Color.RED) {
					assert x.parent != NULL_NODE && x.parent.color == Color.BLACK;
					assert w.left != NULL_NODE && w.left.color == Color.BLACK;
					assert w.right != NULL_NODE && w.right.color == Color.BLACK;
					
					w.color = Color.BLACK;
					x.parent.color = Color.RED;
					rightRotate(x.parent);
					w = x.parent.left;
					
					assert w != NULL_NODE && w.color == Color.BLACK; // case 1结束时w.color为黑色从而进入case2,3或4
				}
				
				// case 2
				if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.parent;
				} else {
					// case 3
					if (w.left.color == Color.BLACK) {
						assert w.right != NULL_NODE && w.right.color == Color.RED;
						w.right.color = Color.BLACK;
						w.color = Color.RED;
						leftRotate(w);
						w = x.parent.left;
					}
					
					// case 4
					assert (w.left.color == Color.RED);
					w.color = x.parent.color;
					x.parent.color = Color.BLACK;
					w.left.color = Color.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = Color.BLACK;
	}

	public String toString() {
		if (root == NULL_NODE) return "[empty]";
		StringBuilder sb = new StringBuilder();
		Node node = minimumNode(root);
		while (node != NULL_NODE) {
			sb.append(String.format("%d: %s %s %s[%s]%n", node.data,
					node.left == NULL_NODE ? "-" : node.left.data,
					node.right == NULL_NODE ? "-" : node.right.data,
					node.parent == NULL_NODE ? "-" : node.parent.data,
					node.color));
			node = successorNode(node);
		}
		return sb.toString();
	}
	
	private void checkInvarient() {
		if (NULL_NODE.color != Color.BLACK) {
			throw new IllegalStateException("don't allow change NULL_NODE's color");
		}
		
		IdentityHashMap<Node, Integer> blackHeightMap = new IdentityHashMap<Node, Integer>();
		checkNode(root, blackHeightMap);
		
		if (blackHeightMap.size() != size) {
			throw new IllegalStateException(String.format("actual size(%d) is not the expected size(%d)", blackHeightMap.size(), size));
		}
	}
	
	
	private void checkNode(Node node, IdentityHashMap<Node, Integer> blackHeightMap) {
		// 所有的结点不能为null，只能为NULL_NODE
		if (node == null) {
			throw new IllegalStateException("should not have null, use NULL_NODE");
		}
		if (blackHeightMap.containsKey(node)) { // 每个结点只会被检测一次，否则出现循环引用
			throw new IllegalStateException("circular reference detected!");
		}
		if (node == NULL_NODE) return;
		if (node.color == null) { // 所有的结点要么是黑的要么是红的，不可能为null
			throw new IllegalStateException("Color must be red or black, cannot be null");
		}
		
		// 检测左、右和父结点之间的链接关系是否正确，以及是否满足二叉树基本性质
		Node l = node.left; Node r = node.right;
		if (l != NULL_NODE) {
			 if (l.parent != node) {
				 throw new IllegalStateException(String.format("left(%s)-parent(%s) relation violated", l, node));
			 }
			 if (l.data > node.data) {
				 throw new IllegalStateException(String.format("left node(%s) > parent node(%s)", l, node));
			 }
		}
		if (r != NULL_NODE) {
			if (r.parent != node) {
				throw new IllegalStateException(String.format("right(%s)-parent(%s) relation violated", r, node));
			}
			 if (r.data < node.data) {
				 throw new IllegalStateException(String.format("right node(%s) < parent node(%s)", r, node));
			 }
		}
		// 如果一个结点是红的，那么它的子结点必定是黑的
		if (node.color == Color.RED) {
			if (node.left.color != Color.BLACK) {
				throw new IllegalStateException(String.format("node(%s) is black, but its left child(%s) is not black", 
						node, node.left));
			}
			if (node.right.color != Color.BLACK) {
				throw new IllegalStateException(String.format("node(%s) is black, but its right child(%s) is not black", 
						node, node.right));
			}
		}
		
		checkNode(node.left, blackHeightMap);
		checkNode(node.right, blackHeightMap);
		
		// 检测左子树的黑结点高度是否与可子树的黑结点高度是不是相同
		int leftBlackHeight, rightBlackHeight;
		if (node.left == NULL_NODE) leftBlackHeight = 1;
		else if (node.left.color == Color.BLACK) {
			leftBlackHeight = blackHeightMap.get(node.left).intValue() + 1;
		} else {
			leftBlackHeight = blackHeightMap.get(node.left).intValue();
		}
		if (node.right == NULL_NODE) rightBlackHeight = 1;
		else if (node.right.color == Color.BLACK) {
			rightBlackHeight = blackHeightMap.get(node.right).intValue() + 1;
		} else {
			rightBlackHeight = blackHeightMap.get(node.right).intValue();
		}
		
		if (leftBlackHeight != rightBlackHeight) {
			throw new IllegalStateException(String.format("node(%s)'s left black heigth(%d) is not equal right black height(%d)",
					node, leftBlackHeight, rightBlackHeight));
		}
		blackHeightMap.put(node, leftBlackHeight);
	}
	
	private enum Color {
		RED, BLACK
	}
	
	private static class Node {
		private Color color;
		private int data;
		private Node left;
		private Node right;
		private Node parent;
		
		public Node(int d, Color c) { this(d, c, NULL_NODE, NULL_NODE, NULL_NODE); }
		public Node(int d, Color c, Node p) { this(d, c, p, NULL_NODE, NULL_NODE); }
		public Node(int d, Color c, Node p, Node l, Node r) {
			data = d; color =c; parent = p; left = l; right = r;
		}
		public String toString() { return Integer.toString(data); }
	}
	
	public static void main(String[] args) throws Exception {
		IntRedBlackTree btree = new IntRedBlackTree();
		
		int[] data = new int[] {  5, 6, 7, 8, 9, 13};
			//{20, 47, 27, 4, 23, 42, 0, 14, 18};
		for (int i = 0; i < data.length; i++) {
			btree.insert(data[i]);
		}
		System.out.println("height: " + btree.getHeight());
		
		System.out.println(btree);
		
		System.out.println("minimum: " + btree.minimum());
		System.out.println("maximum: " + btree.maximum());
		
		System.out.println("ascendingIterator");
		for (IntIterator itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		System.out.println("\ndesendingIterator");
		for (IntIterator itor = btree.desendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		btree.delete(7);
		System.out.println("\nascendingIterator");
		for (IntIterator itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		btree = new IntRedBlackTree();
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			btree.insert(random.nextInt(200));
		}
		System.out.println("\n200 elements, height: " + btree.getHeight());
		
		try {
		for (int i = 0; i < 1000; i++) {
			btree.delete(random.nextInt(200));
		}
		} catch (Exception e) {
			System.out.println(btree.size);
			System.out.println(btree);
			throw e;
		}
		
		System.out.println("\nnow size: " + btree.size);
		btree = new IntRedBlackTree();
		for (int i = 0; i < 1000; i++) {
			btree.insert(i);
		}
		System.out.println("\n" + btree.size + " elements, height: " + btree.getHeight());
	}
}
