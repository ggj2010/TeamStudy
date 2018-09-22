package com.team.gaoguangjin.acm.chapter25;


/**
 * 使用矩阵乘法来解决每对顶点的最短路径问题，其时间复杂度为O(V^3lgV)。该算法采用邻接矩阵表示法。
 */
public class MatrixMultiplyGraph {
	private int nextId;
	
	private Vertex[] vertexes;
	private int[][] edges;

	private static int[][] expandShortestPaths(int[][] L, int[][] M) {
		int n = L.length;
		int[][] N = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				N[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < n; k++) {
					if (L[i][k] != Integer.MAX_VALUE && M[k][j] != Integer.MAX_VALUE && N[i][j] > L[i][k] + M[k][j]) {
						N[i][j] = L[i][k] + M[k][j];
					}
				}
			}
		}
		return N;
	}
	
	/**
	 * 分别计算L1, L2, L3, Lm-1，效率较低，时间复杂度为O(V^4)。
	 */
	public int[][] getAllPairsShortestPaths() {
		int[][] L = edges;
		for (int m = 2; m < edges.length; m++) {
			L = expandShortestPaths(L, edges);
		}
		return L;
	}
	
	/**
	 * 计算L1, L2, L4, L8...，时间复杂度为O(V^3lgV)。
	 */
	public int[][] getAllPairsShortestPaths2() {
		int[][] L = edges;
		int m = 1;
		while (m < L.length - 1) {
			L = expandShortestPaths(L, L);
			m <<= 1;
		}
		return L;
	}
	
	public static void printMatrix(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%5s", matrix[i][j] == Integer.MAX_VALUE ? "inf" : String.valueOf(matrix[i][j]));
			}
			System.out.println();
		}
	}
	
	public void printEdges() {
		printMatrix(this.edges);
	}
	
	public MatrixMultiplyGraph(int vertexCount) {
		vertexes = new Vertex[vertexCount];
		edges = new int[vertexCount][vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				if (i != j) {
					edges[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}
	
	public Vertex addVertex(String data) {
		int id = nextId();
		vertexes[id] = new Vertex(id, data);
		
		return vertexes[id];
	}
	
	public void addEdge(Vertex v1, Vertex v2, int weight) {
		assert v1 == vertexes[v1.id];
		assert v2 == vertexes[v2.id];
		
		edges[v1.id][v2.id] = weight;
	}

	private int nextId() {
		return nextId++;
	}
	
	public static class Vertex {
		int id; /* 每个顶点有个不同的id值，它从零开始，如果没有对删除操作，它应该是连续的 */
		String data;
		
		public Vertex(int id, String data) {
			this.id = id; this.data = data;
		}
		public String toString() { return String.valueOf(data); }
	}
	
	public static void main(String[] args) {
		MatrixMultiplyGraph graph = new MatrixMultiplyGraph(5);
		Vertex v1 = graph.addVertex("1");
		Vertex v2 = graph.addVertex("2");
		Vertex v3 = graph.addVertex("3"); 
		Vertex v4 = graph.addVertex("4");
		Vertex v5 = graph.addVertex("5");
		
		graph.addEdge(v1, v2, 3);
		graph.addEdge(v1, v3, 8);
		graph.addEdge(v1, v5, -4);
		graph.addEdge(v2, v5, 7);
		graph.addEdge(v2, v4, 1);
		graph.addEdge(v3, v2, 4);
		graph.addEdge(v4, v1, 2);
		graph.addEdge(v4, v3, -5);
		graph.addEdge(v5, v4, 6);
		System.out.println("=====original graph=======");
		graph.printEdges();
		
		
		int[][] L = graph.getAllPairsShortestPaths();
		System.out.println("=====shortest path matrix=======");
		printMatrix(L);
		
		L = graph.getAllPairsShortestPaths2();
		System.out.println("=====shortest path matrix(faster alogrithm)=======");
		printMatrix(L);
	}
}
