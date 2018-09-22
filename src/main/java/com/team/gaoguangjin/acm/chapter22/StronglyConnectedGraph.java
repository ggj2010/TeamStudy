package com.team.gaoguangjin.acm.chapter22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class StronglyConnectedGraph {
	private static final boolean DEBUG = true;
	
	private static final int MAX_VERTEX = 20;
	
	private int nextId;
	private Node[] nodes;
	private int vertexCount;
	
	public StronglyConnectedGraph(int maxVertex) {
		nodes = new Node[maxVertex];
	}
	public StronglyConnectedGraph() { this(MAX_VERTEX); }
	
	public Vertex addVertex(String data) {
		int id = nextId();
		nodes[id] = new Node(new Vertex(id, data));
		vertexCount++;
		
		return nodes[id].vertex;
	}
	
	private int nextId() {
		return nextId++;
	}
	
	public static void printResult(List<Vertex> vertexes) {
		for (int i = 0; i < vertexes.size(); i++) {
			Vertex v = vertexes.get(i);
			System.out.printf("Vertex %s: parent=%s, start=%d, finish=%d%n", v, v.parent, v.start, v.finish);
		}
	}
	
	public List<Vertex> getStronglyConnectedComponents() {
		return getStronglyConnectedComponents(this);
	}
	
	static int time = 0;

	/**
	 * 得到连通子图。返回结点是一个顶点的集合，顶点中<code>parent</code>指示了该顶点的所在的连通子图。 每次遇到顶点的
	 * <code>parent</code>为<code>null</code>时表示一个连通子图的开始，在下一个连通
	 * 子图开始前遇到的所有的结点都属于同一个连通子图。如：
	 * 
	 * <pre>
	 * Vertex a: parent=null, start=1, finish=6
	 * Vertex e: parent=a, start=2, finish=5
	 * Vertex b: parent=e, start=3, finish=4
	 * Vertex c: parent=null, start=7, finish=10
	 * Vertex d: parent=c, start=8, finish=9
	 * Vertex f: parent=null, start=11, finish=14
	 * Vertex g: parent=f, start=12, finish=13
	 * Vertex h: parent=null, start=15, finish=16
	 * </pre>
	 * 
	 * 这表示存在四个连通子图，它们分别是{a,e,b}, {c,d}, {f,g}和{h}
	 */
	public static List<Vertex> getStronglyConnectedComponents(StronglyConnectedGraph graph) {
		// 第一次DFS
		for (int i = 0; i < graph.vertexCount; i++) {
			graph.nodes[i].vertex.color = Vertex.WHITE;
			graph.nodes[i].vertex.parent = null;
		}
		time = 0;
		LinkedList<Vertex> vertexes = new LinkedList<Vertex>();
		for (int i = 0; i < graph.vertexCount; i++) {
			Node u = graph.nodes[i];
			if (u.vertex.color == Vertex.WHITE) {
				firstDfsVisit(graph, u, vertexes);
			}
		}
		if (DEBUG) {
			System.out.println("=========first DFS result:");
			printResult(vertexes);
		}
		
		StronglyConnectedGraph tGraph = transpose(graph);
		// 第二次遍历
		for (int i = 0; i < tGraph.vertexCount; i++) {
			tGraph.nodes[i].vertex.color = Vertex.WHITE;
			tGraph.nodes[i].vertex.parent = null;
		}
		time = 0;
		List<Vertex> result = new ArrayList<Vertex>();
		for (Vertex v : vertexes) {
			Node u = tGraph.nodes[v.id];
			if (u.vertex.color == Vertex.WHITE) {
				secondDfsVisit(tGraph, u, result);
			}
		}
		
		if (DEBUG) {
			System.out.println("==========second DFS result:");
			printResult(result);
		}
		return result;
	}
	
	private static StronglyConnectedGraph clone(StronglyConnectedGraph graph) {
		StronglyConnectedGraph result = new StronglyConnectedGraph();
		result.nextId = graph.nextId;
		result.vertexCount = graph.vertexCount;
		result.nodes = new Node[graph.nodes.length];
		for (int i = 0; i < graph.vertexCount; i++) {
			result.nodes[i] = new Node(graph.nodes[i].vertex);
		}
		return result;
	}
	
	private static StronglyConnectedGraph transpose(StronglyConnectedGraph graph) {
		StronglyConnectedGraph result = clone(graph);
		for (int i = 0; i < graph.vertexCount; i++) {
			Node u = graph.nodes[i];
			for (Node v = u.next; v != null; v = v.next) {
				// add edge (v,u) to result
				Node newNode = new Node(u.vertex, result.nodes[v.vertex.id].next);
				result.nodes[v.vertex.id].next = newNode;
			}
		}
		return result;
	}
	
	private static void firstDfsVisit(StronglyConnectedGraph graph, Node u, LinkedList<Vertex> vertexes) {
		u.vertex.color = Vertex.GRAY;
		time++;
		u.vertex.start = time;
		for (Node v = graph.nodes[u.vertex.id].next; v != null; v = v.next) {
			if (v.vertex.color == Vertex.WHITE) {
				v.vertex.parent = u.vertex;
				firstDfsVisit(graph, v, vertexes);
			}
		}
		u.vertex.color = Vertex.BLACK;
		time++;
		u.vertex.finish = time;
		vertexes.addFirst(u.vertex);
	}
	
	private static void secondDfsVisit(StronglyConnectedGraph graph, Node u, List<Vertex> vertexes) {
		vertexes.add(u.vertex);
		u.vertex.color = Vertex.GRAY;
		time++;
		u.vertex.start = time;
		for (Node v = graph.nodes[u.vertex.id].next; v != null; v = v.next) {
			if (v.vertex.color == Vertex.WHITE) {
				v.vertex.parent = u.vertex;
				secondDfsVisit(graph, v, vertexes);
			}
		}
		u.vertex.color = Vertex.BLACK;
		time++;
		u.vertex.finish = time;
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
		public Node(Vertex v, Node n) { vertex = v; next = n; }
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
		StronglyConnectedGraph graph = new StronglyConnectedGraph();
		Vertex a = graph.addVertex("a");
		Vertex b = graph.addVertex("b");
		Vertex c = graph.addVertex("c");
		Vertex d = graph.addVertex("d");
		Vertex e = graph.addVertex("e");
		Vertex f = graph.addVertex("f");
		Vertex g = graph.addVertex("g");
		Vertex h = graph.addVertex("h");
		
		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(b, e);
		graph.addEdge(b, f);
		graph.addEdge(c, d);
		graph.addEdge(c, g);
		graph.addEdge(d, c);
		graph.addEdge(d, h);
		graph.addEdge(e, a);
		graph.addEdge(e, f);
		graph.addEdge(f, g);
		graph.addEdge(g, f);
		graph.addEdge(g, h);
		graph.addEdge(h, h);
		
		graph.getStronglyConnectedComponents();
	}
}
