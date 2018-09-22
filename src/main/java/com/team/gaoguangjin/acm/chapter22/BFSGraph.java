package com.team.gaoguangjin.acm.chapter22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 图的广度（Breadth-first search）优先算法。
 */
public class BFSGraph {
	private static final int MAX_VERTEX = 20;
	
	private int nextId;
	private VertexNode[] vertexNodes;
	private int vertexCount;
	private VertexNode s;
	
	public BFSGraph(int maxVertex) {
		vertexNodes = new VertexNode[maxVertex];
	}
	public BFSGraph() { this(MAX_VERTEX); }
	
	public void setSourceVertex(Vertex sourceVertex) {
		assert sourceVertex == vertexNodes[sourceVertex.id].vertex;
		s = vertexNodes[sourceVertex.id];
	}
	
	public Vertex addVertex(String data) {
		int id = nextId();
		vertexNodes[id] = new VertexNode(new Vertex(id, data));
		vertexCount++;
		
		return vertexNodes[id].vertex;
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		assert v1 == vertexNodes[v1.id].vertex;
		assert v2 == vertexNodes[v2.id].vertex;
		
		EdgeNode newNode = new EdgeNode(vertexNodes[v2.id]);
		newNode.next = vertexNodes[v1.id].firstEdge;
		vertexNodes[v1.id].firstEdge = newNode;
	}
	
	public void addUndirectionalEdge(Vertex v1, Vertex v2) {
		addEdge(v1, v2);
		addEdge(v2, v1);
	}
	
	private int nextId() {
		return nextId++;
	}

	
	public List<Vertex> BFS() {
		assert s == vertexNodes[s.vertex.id];
		
		List<Vertex> result = new ArrayList<Vertex>();
		
		for (int i = 0; i < vertexCount; i++) {
			vertexNodes[i].vertex.color = Vertex.WHITE;
			vertexNodes[i].vertex.distance = -1; // 用-1代表无限大
			vertexNodes[i].vertex.parent = null;
		}
		result.add(s.vertex);
		s.vertex.color = Vertex.GRAY;
		s.vertex.distance = 0;
		LinkedList<VertexNode> queue = new LinkedList<VertexNode>();
		queue.addLast(s);
		while (!queue.isEmpty()) {
			Vertex u = queue.removeFirst().vertex;
			for (EdgeNode vEdge = vertexNodes[u.id].firstEdge; vEdge != null; vEdge = vEdge.next) {
				Vertex v = vEdge.vertexNode.vertex;
				if (v.color == Vertex.WHITE) {
					result.add(v);
					v.color = Vertex.GRAY;
					v.distance = u.distance + 1;
					v.parent = u;
					queue.addLast(vEdge.vertexNode);
				}
			}
			u.color = Vertex.BLACK;
		}
		
		return result;
	}
	
	public static void printBFSResult(List<Vertex> vertexes) {
		for (int i = 0; i < vertexes.size(); i++) {
			Vertex v = vertexes.get(i);
			System.out.printf("vertex %s: parent=%s, distance=%d%n", v, v.parent, v.distance);
		}
	}
	
	public void printPath(Vertex targetVertex) {
		printPath(vertexNodes[targetVertex.id]);
	}
	private void printPath(VertexNode v) {
		if (s == v) System.out.print(s);
		else if (v.vertex.parent == null) {
			System.out.print("no path from " + s + " to " + v);
		} else {
			printPath(v.vertex.parent);
			System.out.print(", " + v);
		}
	}
	private static class VertexNode {
		Vertex vertex;
		EdgeNode firstEdge;
		
		public VertexNode(Vertex v) { vertex = v; }
		public String toString() { return String.valueOf(vertex); }
	}
	
	private static class EdgeNode {
		VertexNode vertexNode;
		EdgeNode next;
		
		public EdgeNode(VertexNode v) { vertexNode = v; }
		public String toString() { return String.valueOf(vertexNode); }
	}
	
	public static class Vertex {
		public static final int WHITE = 0;
		public static final int GRAY = 1;
		public static final int BLACK = 2;
		
		private int id;
		private String data;
		private int color;
		private Vertex parent;
		private int distance;
		
		public Vertex(int id, String data) {
			this.id = id; this.data = data;
		}
		public String toString() { return (data); }
	}
	
	
	public static void main(String[] args) {
		BFSGraph graph = new BFSGraph();
		Vertex r = graph.addVertex("r");
		Vertex s = graph.addVertex("s");
		Vertex t = graph.addVertex("t");
		Vertex u = graph.addVertex("u");
		Vertex v = graph.addVertex("v");
		Vertex w = graph.addVertex("w");
		Vertex x = graph.addVertex("x");
		Vertex y = graph.addVertex("y");

		graph.addUndirectionalEdge(r, v);
		graph.addUndirectionalEdge(r, s);
		graph.addUndirectionalEdge(s, w);
		graph.addUndirectionalEdge(w, t);
		graph.addUndirectionalEdge(w, x);
		graph.addUndirectionalEdge(t, x);
		graph.addUndirectionalEdge(t, u);
		graph.addUndirectionalEdge(x, u);
		graph.addUndirectionalEdge(x, y);
		graph.addUndirectionalEdge(y, u);
		
		System.out.println("BFS from vertex s:");
		graph.setSourceVertex(s);
		List<Vertex> result = graph.BFS();
		printBFSResult(result);
		System.out.println("visit order: " + result);
		graph.printPath(x);
		System.out.println();

		System.out.println("\nBFS from vertex r:");
		graph.setSourceVertex(r);
		result = graph.BFS();
		printBFSResult(result);
	}
	
}
