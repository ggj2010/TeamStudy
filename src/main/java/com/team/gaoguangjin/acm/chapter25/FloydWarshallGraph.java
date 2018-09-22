package com.team.gaoguangjin.acm.chapter25;


/**
 * 使用Floyd Warshall算法来解决每对顶点的最短路径问题，其时间复杂度为O(V^3)。该算法采用邻接矩阵表示法。
 */
public class FloydWarshallGraph {
	private int nextId;
	
	private Vertex[] vertexes;
	private int[][] edges;
	
	public FloydWarshallGraph(int vertexCount) {
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
		
	/**
	 * dij(k)表示从顶点i到顶点j的路径中，且满足所有顶点皆属于集合{0,1,2,...k}
	 * 的一条最短路径。这与书本中的定义有些不同，主要是由于书中数组索引是从1开始，
	 * 而java中数组是从0开始。
	 * 其递归定义为：
	 * <pre>
	 * dij(k) = wij          if k = -1
	 * 		    min{dij(k-1), dik(k-1)+dkj(k-1)} if k >= 0		
	 */
	public int[][] getAllPairsShortestPaths() {
		int n = edges.length;
		int[][] dk_1 = new int[n][n];
		int[][] dk = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dk_1[i][j] = edges[i][j];
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dk_1[i][k] == Integer.MAX_VALUE || dk_1[k][j] == Integer.MAX_VALUE || dk_1[i][j] < dk_1[i][k] + dk_1[k][j]) {
						dk[i][j] = dk_1[i][j];
					} else {
						dk[i][j] = dk_1[i][k] + dk_1[k][j];
					}
				}
			}
			int[][] temp = dk_1;
			dk_1 = dk;
			dk = temp;
		}
		return dk_1;
	}
	
	
	/**
	 * 得到传输闭包。
	 */
	public int[][] getTransitiveClosure() {
		int n = edges.length;
		int[][] tk_1 = new int[n][n];
		int[][] tk = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tk_1[i][j] = edges[i][j] < Integer.MAX_VALUE ? 1 : 0;
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tk[i][j] = tk_1[i][j] | (tk_1[i][k] & tk_1[k][j]);
				}
			}
			int[][] temp = tk_1;
			tk_1 = tk;
			tk = temp;
		}
		return tk_1;
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
		FloydWarshallGraph graph = new FloydWarshallGraph(5);
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
		
		int[][] t = graph.getTransitiveClosure();
		System.out.println("=====transitive closure=======");
		printMatrix(t);
		
		System.out.println();
		graph = new FloydWarshallGraph(4);
		v1 = graph.addVertex("1");
		v2 = graph.addVertex("2");
		v3 = graph.addVertex("3"); 
		v4 = graph.addVertex("4");
		graph.addEdge(v2, v4, 3);
		graph.addEdge(v2, v3, 6);
		graph.addEdge(v3, v2, -1);
		graph.addEdge(v4, v1, 5);
		graph.addEdge(v4, v3, 4);
		L = graph.getAllPairsShortestPaths();
		System.out.println("=====shortest path matrix=======");
		printMatrix(L);
		
		t = graph.getTransitiveClosure();
		System.out.println("=====transitive closure=======");
		printMatrix(t);
	}
}
