package com.team.gaoguangjin.acm.chapter16;


public class HuffmanCode {
	private int[] freq;
	Node root;
	
	public HuffmanCode(int[] freq) {
		this.freq = freq;
	}
	
	public void build() {
		Node[] nodes = new Node[freq.length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(freq[i], i);
		}
		
		PriorityQueue queue = new PriorityQueue(nodes);
		for (int i = 1; i < nodes.length; i++) { // 执行(freq.length - 1)次
			Node left = queue.extractMin();
			Node right = queue.extractMin();
			Node newNode = new Node(left.freq + right.freq, -1, left, right);
			queue.insert(newNode);
		}
		
		root = queue.extractMin();
	}
	
	public void printCode() {
		printCode(null, root, "");
	}
	
	private void printCode(Node parent, Node node, String prefix) {
		if (node.id >= 0) {
			System.out.println(node + ": " + prefix);
		} else {
			printCode(node, node.left, prefix + "0");
			printCode(node, node.right, prefix + "1");
		}
	}

	private static class Node implements Comparable<Node>{
		int freq;
		int id; // 结点的编号，从0开始
		Node left;
		Node right;
		
		public Node(int freq, int index) { this.freq = freq; this.id = index; }
		public Node(int freq, int index, Node left, Node right) {
			this(freq, index); this.left = left; this.right = right;
		}
		public int compareTo(Node o) {
			return freq - o.freq;
		}
		public String toString() { return freq + "@" + id; }
	}
	
	public static class PriorityQueue {
		private Node[] data; // 这里不使用索引为0的数据也就是数据从1开始
		private int size = 0;
		
		public PriorityQueue() {
			data = new Node[8];
		}
		
		public PriorityQueue(Node[] d) {
			data = new Node[d.length + 1];
			System.arraycopy(d, 0, data, 1, d.length);
			size = d.length;
			
			for (int i = (size >> 1); i > 0; i--) {
				downHeap(i);
			}
		}
		
		public void insert(Node d) {
			++size;
			if (size >= data.length) {
				ensureSize(size + 1);
			}
			data[size] = d;
			upHeap(size);
		}
		
		private void upHeap(int i) {
			while (i > 1 && data[i >> 1].freq > data[i].freq) {
				swap(data, i >> 1, i);
				i = i >> 1;
			}
		}
		
		public Node extractMin() {
			if (size < 1) throw new IndexOutOfBoundsException("no elements");
			
			Node result = data[1];
			swap(data, 1, size);
			data[size--]=null;
			downHeap(1);
			return result;
		}
		
		private void downHeap(int i) {
			while (i <= (size >> 1)) {
				int l = i << 1; int r = l + 1;
				int min = i;
				
				if (l <= size && data[l].freq < data[min].freq) min = l;
				if (r <= size && data[r].freq < data[min].freq) min = r;
				
				if (i == min) break;
				
				swap(data, i, min);
				i = min;
			}
		}
		
		private void swap(Object[] a, int i, int j) {
			Object temp = a[i]; a[i] = a[j]; a[j] = temp;
		}
		
		private void ensureSize(int cap) {
			int newLength = data.length;
			while (newLength < cap) {
				newLength <<= 1;
			}
			if (newLength != data.length) {
				Node[] newData = new Node[newLength];
				System.arraycopy(data, 0, newData, 0, data.length);
				this.data = newData;
			}
		}
	}
	
	public static void main(String[] args) {
		HuffmanCode hc = new HuffmanCode(new int[] {45, 13, 12, 16, 9, 5});
		hc.build();
		hc.printCode();
		System.out.println("finished!");
	}
}
