package com.team.gaoguangjin.acm.chapter21;

/**
 * 可合并集合的一种实现，具有很高的效率。为了简单，这里使用了过程化的方式，与书上的一致。
 * <p>
 * 
 * 这种集合的结点只有指向父结点的指针，不能根据一个结点来遍历它所有的子结点，
 * 也不能维护该Node所包含的子结点的个数，因为子结点有可能被垃圾回收。
 * 
 * 这种集合的具体使用可见第23章的Kruskal最小生成树算法。
 */
public class DisjointSetForest {
	
	public static Node makeSet(int k) {
		return makeSet(new Node(k));
	}
	
	public static Node makeSet(Node x) {
		x.parent = x;
		x.rank = 0;
		return x;
	}
	
	public static Node union(Node x, Node y) {
		if (x == y) throw new IllegalArgumentException("Cannot union with itself");
		
		return link(findSet(x), findSet(y));
	}
	
	private static Node link(Node x, Node y) {
		if (x.rank > y.rank) {
			y.parent = x;
			return x;
		} else {
			x.parent = y;
			if (x.rank == y.rank) {
				y.rank++;
			}
			return y;
		}
	}
	
	public static Node findSet(Node x) {
		if (x != x.parent) {
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}
	
	public static class Node {
		public Node parent;
		public int rank;
		public int key;
		
		public Node(int key) {
			this.key = key;
		}
		public String toString() {
			return String.valueOf(key);
		}
	}
	
	public static void main(String[] args) {
		Node set1 = makeSet(1);
		Node set2 = makeSet(2);
		Node set12 = union(set1, set2);
		Node set3 = makeSet(3); Node set4 = makeSet(4);
		Node set34 = union(set3, set4);
		
		Node set = union(set12, set34);
		
		System.out.println(set);
		
		findSet(set1);
		
		System.out.println("finished!");
	}
}
