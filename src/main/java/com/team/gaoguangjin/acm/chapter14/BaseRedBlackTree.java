package com.team.gaoguangjin.acm.chapter14;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现了红黑树的基本框架，可以用来作为红黑树数据扩张的基类。该类以<code>nullNode</code>
 * 作为一个冗余结点，在判断一个结点是否为空时，应该与<code>nullNode</code>进行比较，而
 * 不是直接下<code>null</code>进行比较。
 */
public abstract class BaseRedBlackTree<T> {
	
	private static final boolean CHECK_INVARIATN = true;

	protected final BaseNode<T> nullNode = createNode(null, Color.BLACK, null, null, null);
	
	protected BaseNode<T> root = nullNode;
	protected int size;
	protected Comparator<? super T> comparator;

	public BaseRedBlackTree() {}
	
	public BaseRedBlackTree(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}
		
	@SuppressWarnings("unchecked")
	private int compare(T t1, T t2) {
		return comparator == null ? ((Comparable) t1).compareTo(t2)
				: comparator.compare(t1, t2);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	private BaseNode<T> minimumNode(BaseNode<T> node) {
		assert node != nullNode;
		while (node.getLeft() != nullNode) {
			node = node.getLeft();
		}
		return node;
	}
	
	public T minimum() {
		if (size == 0) throw new IllegalStateException("no data");
		return minimumNode(root).getKey();
	}
	
	private BaseNode<T> maximumNode(BaseNode<T> node) {
		assert node != nullNode;
		while (node.getRight() != nullNode) {
			node = node.getRight();
		}
		return node;
	}
	
	public T maximum() {
		if (size == 0) throw new IllegalStateException("no data");
		return maximumNode(root).getKey();
	}
	
	private BaseNode<T> successorNode(BaseNode<T> node) {
		assert node != nullNode;
		if (node.getRight() != nullNode) {
			return minimumNode(node.getRight());
		} else {
			BaseNode<T> parent = node.getParent();
			while (parent != nullNode && parent.getRight() == node) {
				node = parent;
				parent = node.getParent();
			}
			return parent;
		}
	}
	
	public Iterator<T> ascendingIterator() {
		return new Iterator<T>() {
			private BaseNode<T> next = root == nullNode ? nullNode : minimumNode(root);

			public boolean hasNext() {
				return next != nullNode;
			}

			public T next() {
				if (next == nullNode) throw new NoSuchElementException();
				T result = next.getKey();
				next = successorNode(next);
				return result;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	private BaseNode<T> predecessorNode(BaseNode<T> node) {
		assert node != nullNode;
		if (node.getLeft() != nullNode) {
			return maximumNode(node.getLeft());
		} else {
			BaseNode<T> parent = node.getParent();
			while (parent != nullNode && parent.getLeft() == node) {
				node = parent;
				parent = node.getParent();
			}
			return parent;
		}
	}
	
	public Iterator<T> desendingIterator() {
		return new Iterator<T>() {
			private BaseNode<T> next = root == nullNode ? nullNode : maximumNode(root);
			
			public boolean hasNext() {
				return next != nullNode;
			}

			public T next() {
				if (next == nullNode) throw new NoSuchElementException();
				T result = next.getKey();
				next = predecessorNode(next);
				return result;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(BaseNode<T> node) {
		if (node == nullNode) return 0;
		return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
	}
	
	BaseNode<T> searchNode(T key) {
		return searchNode(root, key);
	}
	
	private BaseNode<T> searchNode(BaseNode<T> node, T key) {
		while (node != nullNode && compare(node.getKey(), key) != 0) {
			if (compare(key, node.getKey()) < 0) node = node.getLeft();
			else node = node.getRight();
		}
		return node;
	}

	protected void leftRotate(final BaseNode<T> x) {
		assert x != nullNode && x.getRight() != nullNode;
		final BaseNode<T> y = x.getRight();
		x.setRight(y.getLeft());
		if (y.getLeft() != nullNode) y.getLeft().setParent(x); // 这里对y.left的测试是必须的，书上认为不需要测试，这是不正确的。
							// 这里的关键是如果不测试则有可能改变NULL_NODE的parent值，而delete(Node)中调用deleteFixUp
							// 之前也可能改变NULL_NODE的parent的值，在deleteFixUp中多次调用leftRotate, rightRotate
							// 将NULL_NODE设置成另外一个值。比如在case 3中，如果x为NULL_NODE(这是可能的）,调用rightRotate之后，
							// 会改变NULL_NODE的parent，也就是改变了x.parent值，之后调用w=x.parent.right就得不到正确的值了。
							// 试着去掉测试语句，将会出现运行错误。

		y.setParent(x.getParent());
		if (x.getParent() != nullNode) {
			if (x.getParent().getLeft() == x) {
				x.getParent().setLeft(y);
			} else {
				x.getParent().setRight(y);
			}
		} else {
			this.root = y;
		}
		
		y.setLeft(x);
		x.setParent(y);
		
		afterLeftRotate(x, y);
	}
	
	protected void afterLeftRotate(BaseNode<T> oldRoot, BaseNode<T> newRoot) {}
	
	protected void rightRotate(final BaseNode<T> x) {
		assert x != nullNode && x.getLeft() != nullNode;
		final BaseNode<T> y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != nullNode) y.getRight().setParent(x); // 这里对y.right的测试是必须的，见leftRotate

		y.setParent(x.getParent());
		if (x.getParent() != nullNode) {
			if (x.getParent().getLeft() == x) {
				x.getParent().setLeft(y);
			} else {
				x.getParent().setRight(y);
			}
		} else {
			this.root = y;
		}
		
		y.setRight(x);
		x.setParent(y);
		
		afterRightRotate(x, y);
	}
	
	protected void afterRightRotate(BaseNode<T> oldRoot, BaseNode<T> newRoot) {}
	
	public void insert(T key) {
		BaseNode<T> newNode = createNode(key, Color.RED, nullNode, nullNode, nullNode);
		insert(newNode);
	}

	protected abstract BaseNode<T> createNode(T k, Color c, BaseNode<T> p, BaseNode<T> l, BaseNode<T> r);
	
	protected void insert(BaseNode<T> newNode) {
		 BaseNode<T> prev = nullNode;
		 BaseNode<T> cur = root;
		 while (cur != nullNode) {
			 prev = cur;
			 if (compare(newNode.getKey(), cur.getKey()) < 0) {
				 cur = cur.getLeft();
			 } else {
				 cur = cur.getRight();
			 }
		 }
		 
		 //BaseIntNode newNode;
		 if (prev == nullNode) {
			 root = newNode;
		 } else {
			 newNode.setParent(prev);
			 if (compare(newNode.getKey(), prev.getKey()) < 0) {
				 prev.setLeft(newNode);
			 } else {
				 prev.setRight(newNode);
			 }
		 }
		 size++;
		 
		 beforeInsertFixUp(newNode);
		 
		 insertFixup(newNode);
		 if (CHECK_INVARIATN) checkInvarient();
	}
	
	protected void beforeInsertFixUp(BaseNode<T> node) {
		
	}
	
	protected void insertFixup(BaseNode<T> z) {
		while (z.getParent().getColor() == Color.RED) {
			if (z.getParent() == z.getParent().getParent().getLeft()) {
				BaseNode<T> y = z.getParent().getParent().getRight();
				if (y.getColor() == Color.RED) {
					z.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();
				} else {
					if (z == z.getParent().getRight()) {
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					rightRotate(z.getParent().getParent());
				}
			} else {
				BaseNode<T> y = z.getParent().getParent().getLeft();
				if (y.getColor() == Color.RED) {
					z.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();
				} else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(Color.BLACK);
	}
	
	public boolean delete(T k) {
		BaseNode<T> node = searchNode(k);
		if (node != nullNode) {
			delete(node);
			return true;
		} else {
			return false;
		}
	}
	
	protected void delete(BaseNode<T> node) {
		assert node != nullNode;
		BaseNode<T> d; // d为要删除的结点
		if (node.getLeft() == nullNode || node.getRight() == nullNode) {
			d = node;
		} else {
			d = successorNode(node);
		}
		BaseNode<T> c = d.getLeft() != nullNode ? d.getLeft() : d.getRight(); // c为要链接到的子结点
		c.setParent(d.getParent()); // 不需要检测c是否为NULL_NODE，deleteFixup方法依赖于这里设置的值
		
		if (d.getParent() == nullNode) {
			root = c;
		} else {
			if (d.getParent().getLeft() == d) {
				d.getParent().setLeft(c);
			} else {
				d.getParent().setRight(c);
			}
		}
		
		if (d != node) {
			node.setKey(d.getKey());
		}
		size--;

		if (d.getColor() == Color.BLACK) {
			deleteFixup(c);
		}
		
		if (CHECK_INVARIATN) checkInvarient();
	}
	
	protected void deleteFixup(BaseNode<T> x) {
		while (x != root && x.getColor() == Color.BLACK) {
			if (x == x.getParent().getLeft()) {
				BaseNode<T> w = x.getParent().getRight();
				// case 1
				if (w.getColor() == Color.RED) {
					assert x.getParent() != nullNode && x.getParent().getColor() == Color.BLACK;
					assert w.getLeft() != nullNode && w.getLeft().getColor() == Color.BLACK;
					assert w.getRight() != nullNode && w.getRight().getColor() == Color.BLACK;
					
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();

					assert w != nullNode && w.getColor() == Color.BLACK; // case 1结束时w.color为黑色从而进入case2,3或4
				}
				
				// case 2
				if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else {
					// case 3
					if (w.getRight().getColor() == Color.BLACK) {
						assert w.getLeft() != nullNode && w.getLeft().getColor() == Color.RED;
						
						w.getLeft().setColor(Color.BLACK);
						w.setColor(Color.RED);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					
					// case 4
					assert (w.getRight().getColor() == Color.RED);
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Color.BLACK);
					w.getRight().setColor(Color.BLACK);
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				BaseNode<T> w = x.getParent().getLeft();
				// case 1
				if (w.getColor() == Color.RED) {
					assert x.getParent() != nullNode && x.getParent().getColor() == Color.BLACK;
					assert w.getLeft() != nullNode && w.getLeft().getColor() == Color.BLACK;
					assert w.getRight() != nullNode && w.getRight().getColor() == Color.BLACK;
					
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
					
					assert w != nullNode && w.getColor() == Color.BLACK; // case 1结束时w.color为黑色从而进入case2,3或4
				}
				
				// case 2
				if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else {
					// case 3
					if (w.getLeft().getColor() == Color.BLACK) {
						assert w.getRight() != nullNode && w.getRight().getColor() == Color.RED;
						w.getRight().setColor(Color.BLACK);
						w.setColor(Color.RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					
					// case 4
					assert (w.getLeft().getColor() == Color.RED);
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Color.BLACK);
					w.getLeft().setColor(Color.BLACK);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor(Color.BLACK);
	}

	public String toString() {
		if (root == nullNode) return "[empty]";
		StringBuilder sb = new StringBuilder();
		BaseNode<T> node = minimumNode(root);
		while (node != nullNode) {
			sb.append(toString(node)).append("\n");
			node = successorNode(node);
		}
		return sb.toString();
	}
	
	protected String toString(BaseNode<T> node) {
		return (String.format("%s: %s %s %s[%s]", node.getKey(),
				node.getLeft() == nullNode ? "-" : node.getLeft().getKey(),
				node.getRight() == nullNode ? "-" : node.getRight().getKey(),
				node.getParent() == nullNode ? "-" : node.getParent().getKey(),
				node.getColor()));
	}
	
	private void checkInvarient() {
		if (nullNode.getColor() != Color.BLACK) {
			throw new IllegalStateException("don't allow change NULL_NODE's color");
		}
		
		IdentityHashMap<BaseNode<T>, Integer> blackHeightMap = new IdentityHashMap<BaseNode<T>, Integer>();
		checkNode(root, blackHeightMap);
		
		if (blackHeightMap.size() != size) {
			throw new IllegalStateException(String.format("actual size(%d) is not the expected size(%d)", blackHeightMap.size(), size));
		}
	}
	
	
	protected void checkNode(BaseNode<T> node, IdentityHashMap<BaseNode<T>, Integer> blackHeightMap) {
		// 所有的结点不能为null，只能为NULL_NODE
		if (node == null) {
			throw new IllegalStateException("should not have null, use NULL_NODE");
		}
		if (blackHeightMap.containsKey(node)) { // 每个结点只会被检测一次，否则出现循环引用
			throw new IllegalStateException("circular reference detected!");
		}
		if (node == nullNode) return;
		if (node.getColor() == null) { // 所有的结点要么是黑的要么是红的，不可能为null
			throw new IllegalStateException("Color must be red or black, cannot be null");
		}
		
		// 检测左、右和父结点之间的链接关系是否正确，以及是否满足二叉树基本性质
		BaseNode<T> l = node.getLeft(); BaseNode<T> r = node.getRight();
		if (l != nullNode) {
			 if (l.getParent() != node) {
				 throw new IllegalStateException(String.format("left(%s)-parent(%s) relation violated", l, node));
			 }
			 if (compare(l.getKey(), node.getKey()) > 0) {
				 throw new IllegalStateException(String.format("left node(%s) > parent node(%s)", l, node));
			 }
		}
		if (r != nullNode) {
			if (r.getParent() != node) {
				throw new IllegalStateException(String.format("right(%s)-parent(%s) relation violated", r, node));
			}
			 if (compare(r.getKey(), node.getKey()) < 0) {
				 throw new IllegalStateException(String.format("right node(%s) < parent node(%s)", r, node));
			 }
		}
		// 如果一个结点是红的，那么它的子结点必定是黑的
		if (node.getColor() == Color.RED) {
			if (node.getLeft().getColor() != Color.BLACK) {
				throw new IllegalStateException(String.format("node(%s) is black, but its left child(%s) is not black", 
						node, node.getLeft()));
			}
			if (node.getRight().getColor() != Color.BLACK) {
				throw new IllegalStateException(String.format("node(%s) is black, but its right child(%s) is not black", 
						node, node.getRight()));
			}
		}
		
		checkNode(node.getLeft(), blackHeightMap);
		checkNode(node.getRight(), blackHeightMap);
		
		// 检测左子树的黑结点高度是否与可子树的黑结点高度是不是相同
		int leftBlackHeight, rightBlackHeight;
		if (node.getLeft() == nullNode) leftBlackHeight = 1;
		else if (node.getLeft().getColor() == Color.BLACK) {
			leftBlackHeight = blackHeightMap.get(node.getLeft()).intValue() + 1;
		} else {
			leftBlackHeight = blackHeightMap.get(node.getLeft()).intValue();
		}
		if (node.getRight() == nullNode) rightBlackHeight = 1;
		else if (node.getRight().getColor() == Color.BLACK) {
			rightBlackHeight = blackHeightMap.get(node.getRight()).intValue() + 1;
		} else {
			rightBlackHeight = blackHeightMap.get(node.getRight()).intValue();
		}
		
		if (leftBlackHeight != rightBlackHeight) {
			throw new IllegalStateException(String.format("node(%s)'s left black heigth(%d) is not equal right black height(%d)",
					node, leftBlackHeight, rightBlackHeight));
		}
		blackHeightMap.put(node, leftBlackHeight);
	}

}
