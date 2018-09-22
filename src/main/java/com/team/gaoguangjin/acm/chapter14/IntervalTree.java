package com.team.gaoguangjin.acm.chapter14;

import java.util.IdentityHashMap;


public class IntervalTree extends BaseRedBlackTree<Interval> {

	public Interval search(Interval i) {
		Node x = (Node) root;
		while (x != nullNode && !(i.overlap(x.getKey()))) {
			if (x.getLeft() != nullNode && ((Node)x.getLeft()).max >= i.getLow()) {
				x = (Node) x.getLeft();
			} else {
				x = (Node) x.getRight();
			}
		}
		return x == nullNode ? null : x.getKey();
	}
	
	@Override
	protected BaseNode<Interval> createNode(Interval k, Color c,
			BaseNode<Interval> p, BaseNode<Interval> l, BaseNode<Interval> r) {
		return new Node(k, c, (Node)p, (Node)l, (Node)r, 0);
	}

	
	@Override
	protected void beforeInsertFixUp(final BaseNode<Interval> node) {
		Node n = (Node) node;
		n.max = n.getKey().getHigh();
		Node p = (Node) n.getParent();
		while (n != nullNode && n.max > p.max) {
			p.max = n.max;
			n = p;
			p = (Node) n.getParent();
		}
	}
	
	@Override
	protected void afterLeftRotate(BaseNode<Interval> oldRoot, BaseNode<Interval> newRoot) {
		Node x = (Node) oldRoot;
		Node y = (Node) newRoot;
		y.max = x.max;
		int xmax = x.getKey().getHigh();
		if (x.getLeft() != nullNode && ((Node) x.getLeft()).max > xmax) xmax = ((Node) x.getLeft()).max;
		if (x.getRight() != nullNode && ((Node) x.getRight()).max > xmax) xmax = ((Node) x.getRight()).max;
		x.max = xmax;
	}
	

	@Override
	protected void afterRightRotate(BaseNode<Interval> oldRoot, BaseNode<Interval> newRoot) {
		afterLeftRotate(oldRoot, newRoot);
	}
	
	@Override
	protected void checkNode(BaseNode<Interval> node, IdentityHashMap<BaseNode<Interval>, Integer> blackHeightMap) {
		super.checkNode(node, blackHeightMap);
		Node n = (Node) node;
		Node l = (Node) n.getLeft();
		Node r = (Node) n.getRight();
		if (n != nullNode) {
			int expectedMax = n.getKey().getHigh();
			if (l != nullNode && l.max > expectedMax) {
				expectedMax = l.max;
			}
			if (r != nullNode && r.max > expectedMax) {
				expectedMax = r.max;
			}
			if (n.max != expectedMax) {
				throw new IllegalStateException(String.format("don't sastify interval tree property for node(%s) (n.max=%d, n.high=%d, left.max=%d, right.max=%d)", n, n.max, n.getKey().getHigh(), l.max, r.max));
			}
		}
	}
	
	@Override
	protected String toString(BaseNode<Interval> node) {
		return super.toString(node) + 
			String.format("(max=%d)", ((Node)node).max);
	}
	
	private static class Node extends BaseNode<Interval> {
		private int max;
		
		public Node(Interval k, Color c, Node p, Node l, Node r, int m) {
			super(k, c, p, l, r);
			this.max = m;
		}
	}
	
	public static void main(String[] args) {
		Interval[] is = new Interval[] {
			new Interval(16, 21), new Interval(8, 9), new Interval(25, 30),
			new Interval(5, 8), new Interval(15, 23), new Interval(17, 19),
			new Interval(26, 26), new Interval(0, 3), new Interval(6, 10),
			new Interval(19, 20),
		};
		
		IntervalTree tree = new IntervalTree();
		for (int i = 0; i < is.length; i++) {
			tree.insert(is[i]);
		}
		
		System.out.println(tree);
		
		Interval i = new Interval(22, 25);
		Interval result = tree.search(i);
		if (result == null) {
			System.out.printf("cannot find interval that overlaps interval %s%n", i);
		} else {
			System.out.printf("found interval %s that overlaps interval %s%n", result, i);
		}
		
		i = new Interval(11, 14);
		result = tree.search(i);
		if (result == null) {
			System.out.printf("cannot find interval that overlaps interval %s%n", i);
		} else {
			System.out.printf("found interval %s that overlaps interval %s%n", result, i);
		}
	}
}


