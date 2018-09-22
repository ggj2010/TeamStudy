package com.team.gaoguangjin.acm.chapter23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimSpanTree {
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();
	
	public List<Edge> getMST(Vertex r) {
		for (VertexNode vertexNode : vertexNodes) {
			vertexNode.vertex.key = Integer.MAX_VALUE;   // 使用Integer.MAX_VALUE来替代正无穷大，虽然不是很通用
			vertexNode.vertex.parent = null;
		}
		r.key = 0;
		List<Edge> mstEdges = new ArrayList<Edge>();
		PriorityQueue queue = new PriorityQueue(vertexNodes.toArray(new VertexNode[vertexNodes.size()]));
		while (queue.size > 0) {
			VertexNode uNode = queue.extractMin();
			Vertex u = uNode.vertex;
			if (u != r)
				mstEdges.add(new Edge(u.parent, u, u.key));
			for (EdgeNode vEdge = uNode.firstEdge; vEdge != null; vEdge = vEdge.next) {
				Vertex v = vEdge.vertexNode.vertex;
				if (v.index <= queue.size/*v在队列中*/ && vEdge.weight < v.key) {
					v.parent = u;
					queue.decreaseKey(v.index, vEdge.weight);
				}
			}
		}
		return mstEdges;
	}
		
	public Vertex addVertex(Vertex v) {
		VertexNode node = new VertexNode(v);
		vertexNodes.add(node);
		nodeByVertex.put(v, node);
		return v;
	}
	
	public void addEdge(Vertex v1, Vertex v2, int weight) {
		EdgeNode newNode = new EdgeNode(getVertexNode(v2), weight);
		newNode.next = getVertexNode(v1).firstEdge;
		getVertexNode(v1).firstEdge = newNode;
	}
	
	private VertexNode getVertexNode(Vertex v) {
		return nodeByVertex.get(v);
	}
	
	public void addUndirectionalEdge(Vertex v1, Vertex v2, int weight) {
		addEdge(v1, v2, weight);
		addEdge(v2, v1, weight);
	}
	
	/**
	 * 代表图的一条边。
	 */
	public static class Edge implements Comparable<Edge> {
		Vertex sourceVertex;
		Vertex targetVertex;
		int weight;
		
		public Edge(Vertex s, Vertex t, int w) { sourceVertex = s; targetVertex = t; weight = w; }
		public int compareTo(Edge o) { return weight - o.weight; }
		public String toString() { return sourceVertex+"->"+targetVertex+"("+weight+")"; }
	}
	
	private static class VertexNode {
		Vertex vertex;
		EdgeNode firstEdge;
		
		public VertexNode(Vertex v) { vertex = v; }
		public String toString() { return vertex.toString(); }
	}
	
	private static class EdgeNode implements Comparable<EdgeNode> {
		VertexNode vertexNode;
		EdgeNode next;
		int weight;
		
		public EdgeNode(VertexNode v, int w) { vertexNode = v; weight = w;}
		public String toString() { return vertexNode.toString()+"("+weight+")"; }
		public int compareTo(EdgeNode o) { return weight - o.weight; }
	}
	
	public static class Vertex {
		String data;
		Vertex parent; // 最小生成树的父亲
		int key;	// 基于该值来作比较
		int index;  // 记录Vertex在堆中的位置，有约束queue.data[vertex.index]==vertex，decreaseKey依赖于该值
					// 另外也可以根据该值与queue.size进行比较来判断该vertex是否在queue中。
		
		public Vertex(String data) { this.data = data; }
		public String toString() { return String.format("%s[parent:%s,key:%d,index:%d]", data, parent==null?"<null>":parent.data, key, index); }
	}
	
	/**
	 * 最小优先队列的实现，使用二叉堆。
	 */
	public static class PriorityQueue {
		private VertexNode[] data; // 这里不使用索引为0的数据也就是数据从1开始
		private int size = 0;
		
		public PriorityQueue() {
			data = new VertexNode[8];
		}
		
		public PriorityQueue(VertexNode[] d) {
			data = new VertexNode[d.length + 1];
			System.arraycopy(d, 0, data, 1, d.length);
			size = d.length;
			
			for (int i = 1; i <= size; i++) {
				data[i].vertex.index = i;
			}
			
			for (int i = (size >> 1); i > 0; i--) {
				downHeap(i);
			}
		}

		private void upHeap(int i) {
			while (i > 1 && data[i >> 1].vertex.key > data[i].vertex.key) {
				swap(data, i >> 1, i);
				i = i >> 1;
			}
		}
		
		public VertexNode extractMin() {
			if (size < 1) throw new IndexOutOfBoundsException("no elements");
			
			VertexNode result = data[1];
			swap(data, 1, size);
			data[size--] = null;
			downHeap(1);
			return result;
		}
		
		public void decreaseKey(int i, int k) {
			if (k > data[i].vertex.key) throw new IllegalArgumentException("key is greater than current key");
			data[i].vertex.key = k;
			upHeap(i);
		}
		
		private void downHeap(int i) {
			while (i <= (size >> 1)) {
				int l = i << 1; int r = l + 1;
				int min = i;
				
				if (l <= size && data[l].vertex.key < data[min].vertex.key) min = l;
				if (r <= size && data[r].vertex.key < data[min].vertex.key) min = r;
				
				if (i == min) break;
				
				swap(data, i, min);
				i = min;
			}
		}
		
		private void swap(VertexNode[] a, int i, int j) {
			VertexNode temp = a[i]; a[i] = a[j]; a[j] = temp;
			a[i].vertex.index = i; a[j].vertex.index = j; // 同时更新索引
		}

		public String toString() {
			StringBuilder sb = new StringBuilder("[");
			for (int i = 1; i <= size; i++) {
				sb.append(data[i]);
				if (i < size) sb.append(", ");
			}
			sb.append("]");
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		PrimSpanTree graph = new PrimSpanTree();
		Vertex a = graph.addVertex(new Vertex("a"));
		Vertex b = graph.addVertex(new Vertex("b"));
		Vertex c = graph.addVertex(new Vertex("c"));
		Vertex d = graph.addVertex(new Vertex("d"));
		Vertex e = graph.addVertex(new Vertex("e"));
		Vertex f = graph.addVertex(new Vertex("f"));
		Vertex g = graph.addVertex(new Vertex("g"));
		Vertex h = graph.addVertex(new Vertex("h"));
		Vertex i = graph.addVertex(new Vertex("i"));
		graph.addUndirectionalEdge(a, b, 4);
		graph.addUndirectionalEdge(a, h, 8);
		graph.addUndirectionalEdge(b, h, 11);
		graph.addUndirectionalEdge(b, c, 8);
		graph.addUndirectionalEdge(h, i, 7);
		graph.addUndirectionalEdge(h, g, 1);
		graph.addUndirectionalEdge(i, c, 2);
		graph.addUndirectionalEdge(i, g, 6);
		graph.addUndirectionalEdge(c, d, 7);
		graph.addUndirectionalEdge(c, f, 4);
		graph.addUndirectionalEdge(g, f, 2);
		graph.addUndirectionalEdge(d, f, 14);
		graph.addUndirectionalEdge(d, e, 9);
		graph.addUndirectionalEdge(f, e, 10);
		
		List<Edge> edges = graph.getMST(a);
		for (Edge edge : edges) {
			System.out.println(edge);
		}
	}
}
