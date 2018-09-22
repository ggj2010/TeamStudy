package com.team.gaoguangjin.acm.chapter20;

import com.team.gaoguangjin.acm.chapter12.IntIterator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class FibonacciHeap {
	private Node head; // point to the minimum node
	private int size;
	
	public FibonacciHeap() {}
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	
	public void insert(int k) {
		Node x = new Node(k);
		
		if (head == null) {
			head = x;
		} else {
			Node headprev = head.left;
			headprev.right = x;
			x.left = headprev;
			x.right = head;
			head.left = x;
			
			if (head.key > k) {
				head = x;
			}
		}
		
		size++;
	}
	
	public int minimum() {
		if (size == 0) throw new IndexOutOfBoundsException("empty heap");
		return head.key;
	}
	
	/**
	 * <p>箭头所指方向是right，相反方向是left.</p>
	 * 
	 * <pre>
	 *    +--<---------------<---+                    +--<---------------<---+
	 *    |                      |                    |       ph1 h1         |
	 *    +-->--------------->---+                    +-->------  ------->---+
	 *                             after union                 |  |	
	 *                                ----->                   v  ^
	 *                                                         |  |	
	 *    +--<---------------<---+                    +--<------  -------<---+
	 *    |                      |                    |        h2 ph2        |
	 *    +-->--------------->---+                    +-->--------------->---+
	 *</pre>
	 */
	public void union(FibonacciHeap h) {
		if (h == this) {
			throw new IllegalArgumentException("cannot union with itself");
		}
		if (h.head == null) return;
		
		Node head1 = this.head;
		Node head2 = h.head;
		Node prevhead1 = head1.left;
		Node prevhead2 = head2.left;
		
		prevhead1.right = head2;
		head2.left = prevhead1;
		prevhead2.right = head1;
		head1.left = prevhead2;
		
		if (head1.key > head2.key) {
			this.head = head2;
		}
		size += h.size;
	}
	
	public int extractMin() {
		if (size == 0) throw new IndexOutOfBoundsException("empty heap");
		Node z = head;
		deleteRootNode(z);
		return z.key;
	}

	private void deleteRootNode(Node z) {
		// 将z的孩子结点添加根链表中去（这可以在常量的时间内完成），并将它们的父结点置空（这必须遍历)
		if (z.child != null) {
			Node c = z.child;
			do {
				Node cnext = c.right; Node zprev = z.left;
				zprev.right = c; c.left = zprev;
				c.right = z; z.left = c;
				c.parent = null;
				c = cnext;
			} while (c != z.child);
		}
		Node zprev = z.left;
		zprev.right = z.right;
		z.right.left = zprev;
		
		if (z == z.right) {
			head = null;
		} else {
			head = z.right;
			consolidate();
		}
		size--;
	}
	
	private void consolidate() {
		/*
		 * 在内层while循环中可能访问byDegree[maxDegree+1]，因此需要创建的数组须能够容纳
		 * 0..maxDegree+1种不同的degree的Node，共maxDegree+2个Node，这个数组可能会比实际
		 * 要大一些，我在使用maxDegree+1的数组大小时也没有发生任何数组出界异常， 甚至使用
		 * maxDegree似乎也可以，但是由于没有经过完全测试或理论上的证明， 因此并不可靠。
		 */
		Node[] byDegree = new Node[maxDegree(size)+2];
		
		Node prevhead = head.left;
		Node w = head;
		while (true) {
			Node x = w;
			Node wnext = w.right;
			int d = x.degree;
			while (byDegree[d] != null) {
				Node y = byDegree[d]; // another node with degree d
				if (x.key > y.key) {
					Node temp = x; x = y; y = temp;
				}
				link(y, x);
				byDegree[d] = null;
				d++;
			}
			byDegree[d] = x;
			if (w == prevhead) { // has visited the last node
				break;
			}
			w = wnext;
		}
		
		Node min = null;
		for (int i = 0; i < byDegree.length; i++) {
			if (byDegree[i] == null) continue;
			if (min == null) {
				min = byDegree[i];
				min.left = min.right = min;
			} else {
				Node minprev = min.left;
				minprev.right = byDegree[i];
				byDegree[i].left = minprev;
				byDegree[i].right = min;
				min.left = byDegree[i];
				if (byDegree[i].key < min.key) {
					min = byDegree[i];
				}
			}
		}
		head = min;
	}

	private void link(Node y, Node x) {
		// remove y from the root list of heap
		Node yprev = y.left; Node ynext = y.right;
		yprev.right = ynext; ynext.left = yprev;
		// make y a child of x
		if (x.child == null) {
			x.child = y;
			y.left = y.right = y;
		} else {
			Node xchildprev = x.child.left;
			xchildprev.right = y; y.left = xchildprev;
			y.right = x.child; x.child.left = y;
		}
		y.parent = x;
		x.degree++;
		y.mark = false;
	}

	/**
	 * 1.5 * (log2(size) + 1)，这比理论最大可能的度数要大一些。
	 * 对于不需要支持decreaseKey和delete操作的FibonacciHeap来说，取log2(d)就足够了。
	 */
	static int maxDegree(int d) {
		int i = log2(d);
		return i + (i >> 1);
	}
	private static int log2(int d) {// log2(size)+1
		int i;
		for (i = 0; d > 0; d >>= 1) {
			i++;
		}
		return i;
	}

	
	/** 
	 * 所有结点的遍历。先输出深度为0的结点，也就是根结点，然后输出深度为1的结点，依次下去。
	 */
	public IntIterator iterator() {
		return new IntIterator() {
			LinkedList<Node> items = new LinkedList<Node>();
			{
				Node node = head;
				do {
					items.addLast(node);
					node = node.right;
				} while (node != head);
			}
			
			public boolean hasNext() {
				return items.size() > 0;
			}

			public int next() {
				Node n = items.removeFirst();
				if (n.child != null) {
					Node c = n.child;
					do {
						items.addLast(c);
						c = c.right;
					} while (c != n.child);
				}
				return n.key;
			}
			
		};
	}
	
	void decreaseKey(Node x, int k) {
		if (k > x.key) throw new IllegalArgumentException("new key is greater than current key");
		x.key = k;
		Node y = x.parent;
		if (y != null && x.key < y.key) {
			cut(x, y);
			cascadingCut(y);
		}
		if (x.key < head.key) {
			head = x;
		}
	}
	
	private void cut(Node x, Node y) {
		// remove x from the child list of y, decrementing degree[y]
		Node xprev = x.left; Node xnext = x.right;
		if (x == xnext) { // only one child
			y.child = null;
		} else {
			xprev.right = xnext; xnext.left = xprev;
			if (y.child == x) y.child = xnext;
		}
		y.degree--;
		// add x to the root list
		Node headprev = head.left;
		headprev.right = x; x.left = headprev;
		x.right = head; head.left = x; 
		x.parent = null;
		x.mark = false;
	}
	private void cascadingCut(Node y) {
		Node z = y.parent;
		if (z != null) {
			if (y.mark == false) {
				y.mark = true;
			} else {
				cut(y, z);
				cascadingCut(z);
			}
		}
	}
	
	void delete(Node x) {
		Node y = x.parent;
		if (y != null) {
			cut(x, y);
			cascadingCut(y);
		}
		deleteRootNode(x);
	}
	
	public boolean contains(int k) {
		return search(k) != null;
	}
	
	Node search(int k) {
		if (head == null) return null;
		Node n = this.head;
		do {
			Node r = search(n, k);
			if (r != null) return r;
			n = n.right;
		} while (n != this.head);
		return null;
	}
	
	private Node search(Node n, int k) {
		if (n.key == k) return n;
		if (k < n.key) return null;
		if (n.child != null) {
			Node c = n.child;
			do {
				Node r = search(c, k);
				if (r != null) return r;
				c = c.right;
			} while (c != n.child);
		}
		return null;
	}
	
	void checkConstraint() {
		if ((size == 0) != (head == null)) {
			throw new IllegalStateException("when size is 0, the head must be null, vice versa");
		}
		if (head == null) return;
		checkCiricular(head);
		
		Set<Node> nodes = new HashSet<Node>();
		Node n = head;
		do {
			if (n.parent != null) {
				throw new IllegalStateException(String.format("root node(%s)'s parent is not null", n));
			}
			checkNode(n, nodes);

			n = n.right;
		} while (n != head);
		
		if (size != nodes.size()) {
			throw new IllegalStateException(String.format("expected size is %d, but is %d", size, nodes.size()));
		}
	}

	private static int MAX_LINK_NODES = 8000;
	private void checkCiricular(Node n) {
		int i = 0;
		for (Node x = n; x != n && i < MAX_LINK_NODES; i++, x = x.right) {
			continue;
		}
		if (i >= MAX_LINK_NODES) {
			throw new IllegalStateException(String.format("Node(%s) is not in a ring", n));
		}
	}
	
	private void checkNode(Node n, Set<Node> nodes) {
		nodes.add(n);
		
		if (n.child != null) {
			checkCiricular(n.child);
			int degree = 0;
			Node c = n.child;
			do {
				if (c.left.right != c || c.right.left != c) {
					throw new IllegalStateException(String.format("for node(%s), c.left.right != c || c.right.left != c", c));
				}
				if (c.parent != n) {
					throw new IllegalStateException(String.format("node(%s).parent should be node(%s), but is node(%s)", c, n, c.parent));
				}
				degree++;
				checkNode(c, nodes);
				
				c = c.right;
			} while (c != n.child);
			
			if (degree != n.degree) {
				throw new IllegalStateException(String.format("for Node(%s), expected degree is %d, but is %d", n, n.degree, degree));
			}
		}
	}
	
	static class Node {
		Node parent;
		Node child;
		Node left;
		Node right;
		int degree;
		boolean mark;
		int key;
		
		public Node(int k) {
			key = k; left = this; right = this;
		}
		public String toString() { return String.valueOf(key); }
	}
	
	public static void main(String[] args) {
		System.out.println(Math.log(2)/Math.log((1+Math.sqrt(5))/2));
		for (int i = 1; i < 10000; i++) {
			int a = (int)(Math.log(i) / Math.log((1+Math.sqrt(5))/2)); // 理论上的最大值
			int b = maxDegree(i); // 近似值
//			int c = 2 * ((int)(Math.log(i)/Math.log(2)));
			System.out.println(a + ", " + b);
			if (a > b) {
				throw new RuntimeException();
			}
		}
	}
}
