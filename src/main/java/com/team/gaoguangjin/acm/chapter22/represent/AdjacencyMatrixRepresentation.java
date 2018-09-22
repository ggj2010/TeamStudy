package com.team.gaoguangjin.acm.chapter22.represent;

/**
 * 图的邻接矩阵表示法。
 * 
 * @author marlon
 *
 */
public class AdjacencyMatrixRepresentation {
	private int nextId;
	
	private Vertex[] vertexes;
	private int[][] edges;

	public AdjacencyMatrixRepresentation(int vertexCount) {
		vertexes = new Vertex[vertexCount];
		edges = new int[vertexCount][vertexCount];
	}
	
	public Vertex addVertex(int data) {
		int id = nextId();
		vertexes[id] = new Vertex(id, data);
		
		return vertexes[id];
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		assert v1 == vertexes[v1.id];
		assert v2 == vertexes[v2.id];
		
		edges[v1.id][v2.id] = 1;
	}

	private int nextId() {
		return nextId++;
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
		AdjacencyMatrixRepresentation graph1 = new AdjacencyMatrixRepresentation(5);
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
		
		AdjacencyMatrixRepresentation graph2 = new AdjacencyMatrixRepresentation(6);
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
