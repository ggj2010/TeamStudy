package com.team.gaoguangjin.acm.chapter22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 图的深度（Depth-first search）优先算法以及拓扑排序算法。
 * 
 * TODO:
 * 这里使用<code>Node</code>来代表VertexNode和EdgeNode，最开始没有考虑清楚，也懒得改了。
 * 由于边上没有额外的信息（如权重），这样表示也是可以的。
 */
public class DFSGraph {
	private static final int MAX_VERTEX = 20;
	
	private int nextId;
	private Node[] nodes;
	private int vertexCount;
	
	public DFSGraph(int maxVertex) {
		nodes = new Node[maxVertex];
	}
	public DFSGraph() { this(MAX_VERTEX); }

	int time = 0;
	public List<Vertex> DFS() {
		for (int i = 0; i < vertexCount; i++) {
			nodes[i].vertex.color = Vertex.WHITE;
			nodes[i].vertex.parent = null;
		}
		time = 0;
		List<Vertex> result = new ArrayList<Vertex>();
		for (int i = 0; i < vertexCount; i++) {
			Node u = nodes[i];
			if (u.vertex.color == Vertex.WHITE) {
				dfsVisit(u, result);
			}
		}
		return result;
	}
	
	private void dfsVisit(Node u, List<Vertex> vertexes) {
		vertexes.add(u.vertex);
		u.vertex.color = Vertex.GRAY;
		time++;
		u.vertex.start = time;
		for (Node v = nodes[u.vertex.id].next; v != null; v = v.next) {
			if (v.vertex.color == Vertex.WHITE) {
				v.vertex.parent = u.vertex;
				dfsVisit(v, vertexes);
			}
		}
		u.vertex.color = Vertex.BLACK;
		time++;
		u.vertex.finish = time;
	}
	
	public static void printDFSResult(List<Vertex> vertexes) {
		for (int i = 0; i < vertexes.size(); i++) {
			Vertex v = vertexes.get(i);
			System.out.printf("Vertex %s: parent=%s, start=%d, finish=%d%n", v, v.parent, v.start, v.finish);
		}
	}
	
	public List<Vertex> toplogicalSort() {
		for (int i = 0; i < vertexCount; i++) {
			nodes[i].vertex.color = Vertex.WHITE;
			nodes[i].vertex.parent = null;
		}
		time = 0;
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		for (int i = 0; i < vertexCount; i++) {
			Node u = nodes[i];
			if (u.vertex.color == Vertex.WHITE) {
				dfsVisit2(u, result);
			} else if (u.vertex.color == Vertex.GRAY) {
				throw new IllegalStateException("a circular has detected.");
			}
		}
		return result;
	}
	
	private void dfsVisit2(Node u, LinkedList<Vertex> vertexes) {
		u.vertex.color = Vertex.GRAY;
		time++;
		u.vertex.start = time;
		for (Node v = nodes[u.vertex.id].next; v != null; v = v.next) {
			if (v.vertex.color == Vertex.WHITE) {
				v.vertex.parent = u.vertex;
				dfsVisit2(v, vertexes);
			} else if (v.vertex.color == Vertex.GRAY) {
				throw new IllegalStateException("a circular has detected.");
			}
		}
		u.vertex.color = Vertex.BLACK;
		time++;
		u.vertex.finish = time;
		vertexes.addFirst(u.vertex);
	}
	
	public Vertex addVertex(String data) {
		int id = nextId();
		nodes[id] = new Node(new Vertex(id, data));
		vertexCount++;
		
		return nodes[id].vertex;
	}
	
	private int nextId() {
		return nextId++;
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		assert v1 == nodes[v1.id].vertex;
		assert v2 == nodes[v2.id].vertex;
		
		Node newNode = new Node(v2);
		newNode.next = nodes[v1.id].next;
		nodes[v1.id].next = newNode;
	}
	
	public void addUndirectionalEdge(Vertex v1, Vertex v2) {
		addEdge(v1, v2);
		addEdge(v2, v1);
	}
	
	private static class Node {
		Vertex vertex;
		Node next;
		
		public Node(Vertex v) { vertex = v; }
		public String toString() { return vertex.toString(); }
	}
	
	public static class Vertex {
		public static final int WHITE = 0;
		public static final int GRAY = 1;
		public static final int BLACK = 2;
		
		int id;
		String data;
		Vertex parent;
		int start;
		int finish;
		int color;
		
		public Vertex(int id, String data) {
			this.id = id; this.data = data;
		}
		public String toString() { return (data); }
	}
	
	public static void main(String[] args) {
		DFSGraph graph = new DFSGraph();
		Vertex u = graph.addVertex("u");
		Vertex v = graph.addVertex("v");
		Vertex w = graph.addVertex("w");
		Vertex x = graph.addVertex("x");
		Vertex y = graph.addVertex("y");
		Vertex z = graph.addVertex("z");

		graph.addEdge(u, x);
		graph.addEdge(v, y);
		graph.addEdge(w, z);
		graph.addEdge(w, y);
		graph.addEdge(x, v);
		graph.addEdge(y, x);
		graph.addEdge(z, z);
		
		List<Vertex> vertexes = graph.DFS();
		printDFSResult(vertexes);
		
		System.out.println("==========================");
		graph = new DFSGraph();
		Vertex s = graph.addVertex("s");
		u = graph.addVertex("u");
		Vertex t = graph.addVertex("t");
		v = graph.addVertex("v");
		w = graph.addVertex("w");
		x = graph.addVertex("x");
		y = graph.addVertex("y");
		z = graph.addVertex("z");
		
		graph.addEdge(s, z);
		graph.addEdge(s, w);
		graph.addEdge(t, v);
		graph.addEdge(t, u);
		graph.addEdge(u, v);
		graph.addEdge(u, t);
		graph.addEdge(v, w);
		graph.addEdge(v, s);
		graph.addEdge(w, x);
		graph.addEdge(x, z);
		graph.addEdge(y, x);
		graph.addEdge(z, w);
		graph.addEdge(z, y);
		vertexes = graph.DFS();
		printDFSResult(vertexes);
		
		System.out.println("==========================");
		graph = new DFSGraph();
		Vertex undershorts = graph.addVertex("undershorts");
		Vertex socks = graph.addVertex("socks");
		/*Vertex watch = */graph.addVertex("watch");
		Vertex pants = graph.addVertex("pants");
		Vertex shoes = graph.addVertex("shoes");
		Vertex belt = graph.addVertex("belt");
		Vertex shirt = graph.addVertex("shirt");
		Vertex tie = graph.addVertex("tie");
		Vertex jacket = graph.addVertex("jacket");
		graph.addEdge(undershorts, pants);
		graph.addEdge(undershorts, shoes);
		graph.addEdge(socks, shoes);
		graph.addEdge(pants, shoes);
		graph.addEdge(pants, belt);
		graph.addEdge(shirt, belt);
		graph.addEdge(shirt, tie);
		graph.addEdge(tie, jacket);
		graph.addEdge(belt, jacket);
		
		vertexes = graph.toplogicalSort();
		printDFSResult(vertexes);
		

		System.out.println("==========================");
		// 练习22.4-1
		graph = new DFSGraph();
		Vertex m = graph.addVertex("m");
		Vertex n = graph.addVertex("n");
		Vertex o = graph.addVertex("o");
		Vertex p = graph.addVertex("p");
		Vertex q = graph.addVertex("q");
		Vertex r = graph.addVertex("r");
		s = graph.addVertex("s");
		t = graph.addVertex("t");
		u = graph.addVertex("u");
		v = graph.addVertex("v");
		w = graph.addVertex("w");
		x = graph.addVertex("x");
		y = graph.addVertex("y");
		z = graph.addVertex("z");

		graph.addEdge(m, x);
		graph.addEdge(m, r);
		graph.addEdge(m, q);
		graph.addEdge(n, u);
		graph.addEdge(n, q);
		graph.addEdge(n, o);
		graph.addEdge(o, v);
		graph.addEdge(o, s);
		graph.addEdge(o, r);
		graph.addEdge(p, z);
		graph.addEdge(p, o);
		graph.addEdge(q, t);
		graph.addEdge(r, y);
		graph.addEdge(r, u);
		graph.addEdge(s, r);
		graph.addEdge(u, t);
		graph.addEdge(v, x);
		graph.addEdge(v, w);
		graph.addEdge(w, z);
		graph.addEdge(y, v);
		vertexes = graph.toplogicalSort();
		printDFSResult(vertexes);
	}
}
