package com.team.gaoguangjin.acm.chapter22.represent;

/**
 * 图的邻接表示法。
 * 
 * @author marlon
 */
public class AdjacencyListRepresentation {
	private static final int MAX_VERTEX = 20;
	
	private int nextId;
	
	VertexNode[] nodes = new VertexNode[MAX_VERTEX];
	int vertexCount;
	
	public Vertex addVertex(int data) {
		int id = nextId();
		nodes[id] = new VertexNode(new Vertex(id, data));
		vertexCount++;
		
		return nodes[id].vertex;
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		assert v1 == nodes[v1.id].vertex;
		assert v2 == nodes[v2.id].vertex;
		
		EdgeNode newNode = new EdgeNode(v2.id);
		newNode.next = nodes[v1.id].firstEdge;
		nodes[v1.id].firstEdge = newNode;
	}
	
	private int nextId() {
		return nextId++;
	}
	
	private static class VertexNode {
		Vertex vertex;
		EdgeNode firstEdge;
		
		public VertexNode(Vertex v) { vertex = v; }
		public String toString() { return String.valueOf(vertex); }
	}
	
	private static class EdgeNode {
		int vertexId;
		EdgeNode next;
		
		public EdgeNode(int v) { vertexId = v; }
		public String toString() { return String.valueOf(vertexId); }
	}
	
	public static class Vertex {
		int id; /* 每个顶点有个不同的id值，它从零开始，如果没有对删除操作，它应该是连续的 */
		int data;
		
		public Vertex(int id, int data) {
			this.id = id; this.data = data;
		}
		public String toString() { return String.valueOf(data); }
	}
	
	public static void main(String[] args) {
		AdjacencyListRepresentation graph1 = new AdjacencyListRepresentation();
		Vertex v1 = graph1.addVertex(1), 
		v2 = graph1.addVertex(2), 
		v3 = graph1.addVertex(3), 
		v4 = graph1.addVertex(4), 
		v5 = graph1.addVertex(5);
		graph1.addEdge(v1, v2); graph1.addEdge(v2, v1);
		graph1.addEdge(v2, v3); graph1.addEdge(v3, v2);
		graph1.addEdge(v3, v4); graph1.addEdge(v4, v3);
		graph1.addEdge(v4, v5); graph1.addEdge(v5, v4);
		graph1.addEdge(v5, v1); graph1.addEdge(v1, v5);
		graph1.addEdge(v2, v5); graph1.addEdge(v5, v2);
		graph1.addEdge(v2, v4); graph1.addEdge(v4, v2);
		
		AdjacencyListRepresentation graph2 = new AdjacencyListRepresentation();
		v1 = graph2.addVertex(1);
		v2 = graph2.addVertex(2);
		v3 = graph2.addVertex(3); 
		v4 = graph2.addVertex(4); 
		v5 = graph2.addVertex(5);
		Vertex v6 = graph2.addVertex(6);
		
		graph2.addEdge(v1, v2);
		graph2.addEdge(v1, v4);
		graph2.addEdge(v4, v2);
		graph2.addEdge(v5, v4);
		graph2.addEdge(v2, v5);
		graph2.addEdge(v3, v5);
		graph2.addEdge(v3, v6);
		graph2.addEdge(v6, v6);
		
		System.out.println("finished!");
	}
}
