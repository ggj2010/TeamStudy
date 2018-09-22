package com.team.gaoguangjin.acm.chapter25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JohnsonGraph {
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();

	public int[][] getAllPairsShortestPaths() {
		Vertex sourceVertex = new Vertex(null);
		JohnsonGraph helperGraph = createHelperGraph(sourceVertex);
		
		if (bellmanFordAlogrithm(helperGraph, helperGraph.getVertexNode(sourceVertex))) {
			Map<Vertex, Integer> h = new HashMap<Vertex, Integer>();
			for (VertexNode vertexNode : helperGraph.vertexNodes) {
				h.put(vertexNode.vertex, vertexNode.vertex.distance);
			}
			// 重新赋权
			for (VertexNode vertexNode : this.vertexNodes) {
				for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
					edgeNode.weight += vertexNode.vertex.distance - edgeNode.vertexNode.vertex.distance;
				}
			}
			int[][] distances = new int[vertexNodes.size()][vertexNodes.size()];
			// 对每个顶点运行 Dijkstra算法
			for (int i = 0; i < vertexNodes.size(); i++) {
				VertexNode uNode = vertexNodes.get(i);
				dijkstraAlogrithm(uNode);
				for (int j = 0; j < vertexNodes.size(); j++) {
					VertexNode vNode = vertexNodes.get(j);
					distances[i][j] = vNode.vertex.distance + h.get(vNode.vertex) - h.get(uNode.vertex);
				}
			}
			return distances;
		} else {
			throw new IllegalStateException("the input graph contains a negative-weight cycle");
		}
	}

	private JohnsonGraph createHelperGraph(Vertex sourceVertex) {
		JohnsonGraph graph = new JohnsonGraph();
		graph.vertexNodes.addAll(vertexNodes);
		graph.nodeByVertex.putAll(nodeByVertex);
		// add the source vertex
		graph.addVertex(sourceVertex);
		// add edge from source vertex to other vertex
		for (VertexNode vertexNode : vertexNodes) {
			graph.addEdge(sourceVertex, vertexNode.vertex, 0);
		}
		return graph;
	}

	public static boolean bellmanFordAlogrithm(JohnsonGraph helperGraph, VertexNode sourceNode) {
		init(helperGraph, sourceNode);
		
		for (int i = 0, loopCount = helperGraph.vertexNodes.size() - 1; i < loopCount; i++) {
			for (VertexNode vertexNode : helperGraph.vertexNodes) {
				for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
					Vertex u = vertexNode.vertex;
					Vertex v = edgeNode.vertexNode.vertex;
					int w = edgeNode.weight;
					if (v.distance -w > u.distance) {
						v.distance = u.distance + w;
						v.parent = u;
					}
				}
			}
		}
		for (VertexNode vertexNode : helperGraph.vertexNodes) {
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				Vertex u = vertexNode.vertex;
				Vertex v = edgeNode.vertexNode.vertex;
				if (v.distance - edgeNode.weight > u.distance) {
					return false;
				}
			}
		}
		return true;
	}
		
	public void dijkstraAlogrithm(VertexNode sourceNode) {
		init(this, sourceNode);
		
		PriorityQueue queue = new PriorityQueue(vertexNodes.toArray(new VertexNode[vertexNodes.size()]));
		while (queue.size > 0) {
			VertexNode vertexNode = queue.extractMin();
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				relax(vertexNode, edgeNode, queue);
			}
		}
	}

	private static void init(JohnsonGraph graph, VertexNode sourceNode) {
		for (VertexNode vertexNode : graph.vertexNodes) {
			vertexNode.vertex.distance = Integer.MAX_VALUE; // 用Integer.MAX_VALUE代表正无穷大
			vertexNode.vertex.parent = null;
		}
		sourceNode.vertex.distance = 0;
	}
	
	private void relax(VertexNode vertexNode, EdgeNode edgeNode, PriorityQueue queue) {
		Vertex u = vertexNode.vertex;
		Vertex v = edgeNode.vertexNode.vertex;
		int w = edgeNode.weight;
		if (v.distance -w > u.distance) {
			queue.decreaseKey(v.index, u.distance + w);
			v.parent = u;
		}
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
		Vertex parent;
		int distance;
		
		int index; // 记录Vertex在堆中的位置，有约束queue.data[vertex.index]==vertex，decreaseKey依赖于该值

		public Vertex(String data) { this.data = data; }
		public String toString() { return (data); }
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
			while (i > 1 && data[i >> 1].vertex.distance > data[i].vertex.distance) {
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
			if (i > size) throw new IllegalArgumentException("index out bounds: " + i);
			if (k > data[i].vertex.distance) throw new IllegalArgumentException("key is greater than current key");
			data[i].vertex.distance = k;
			upHeap(i);
		}
		
		private void downHeap(int i) {
			while (i <= (size >> 1)) {
				int l = i << 1; int r = l + 1;
				int min = i;
				
				if (l <= size && data[l].vertex.distance < data[min].vertex.distance) min = l;
				if (r <= size && data[r].vertex.distance < data[min].vertex.distance) min = r;
				
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

	public static void printMatrix(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%5s", matrix[i][j] == Integer.MAX_VALUE ? "inf" : String.valueOf(matrix[i][j]));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		JohnsonGraph graph = new JohnsonGraph();
		Vertex v1 = graph.addVertex(new Vertex("1"));
		Vertex v2 = graph.addVertex(new Vertex("2"));
		Vertex v3 = graph.addVertex(new Vertex("3")); 
		Vertex v4 = graph.addVertex(new Vertex("4"));
		Vertex v5 = graph.addVertex(new Vertex("5"));
		
		graph.addEdge(v1, v2, 3);
		graph.addEdge(v1, v3, 8);
		graph.addEdge(v1, v5, -4);
		graph.addEdge(v2, v5, 7);
		graph.addEdge(v2, v4, 1);
		graph.addEdge(v3, v2, 4);
		graph.addEdge(v4, v1, 2);
		graph.addEdge(v4, v3, -5);
		graph.addEdge(v5, v4, 6);
		
		int[][] distances = graph.getAllPairsShortestPaths();
		printMatrix(distances);
	}
}
