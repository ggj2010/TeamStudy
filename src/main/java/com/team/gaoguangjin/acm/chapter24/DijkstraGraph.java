package com.team.gaoguangjin.acm.chapter24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单源最短路径的Dijkstra算法，它要求所有边的权值不能为负值。
 */
public class DijkstraGraph {
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();
	
	
	private VertexNode sourceNode;
	
	public void setSourceVertex(Vertex s) {
		sourceNode = getVertexNode(s);
	}
	
	/**
	 * 运行时间分析：
	 * init时间：O(V)
	 * 接着执行了V次extractMin()和E次decreaseKey()（在relax方法中被调用），对于使用二叉堆的最小优先队列的实现来说，
	 * 执行时间为：O(VlgV+ElgE)=O((V+E)lgV)，这也是总的运行时间。
	 * 如果使用Fibonacci堆实现最小优先队列，其执行时间为O(VlgV+E)。
	 */
	public void getSingleSourceShortedPath() {
		init();
		
		PriorityQueue queue = new PriorityQueue(vertexNodes.toArray(new VertexNode[vertexNodes.size()]));
		while (queue.size > 0) {
			VertexNode vertexNode = queue.extractMin();
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				relax(vertexNode, edgeNode, queue);
			}
		}
	}

	private void init() {
		for (VertexNode vertexNode : vertexNodes) {
			vertexNode.vertex.distance = Integer.MAX_VALUE; // 用Integer.MAX_VALUE代表正无穷大
			vertexNode.vertex.parent = null;
		}
		sourceNode.vertex.distance = 0;
	}
	
	private void relax(VertexNode vertexNode, EdgeNode edgeNode, PriorityQueue queue) {
		Vertex u = vertexNode.vertex;
		Vertex v = edgeNode.vertexNode.vertex;
		int w = edgeNode.weight;
		if (v.distance -w > u.distance) { // v.distance > u.distance + w，之所以不用加法，是为也避免溢出错误
										  // 也可以为 (u.distance != Integer.MAX_VALUE && v.distance > u.distance + w)
			queue.decreaseKey(v.index, u.distance + w);
			v.parent = u;
		}
	}
	
	public void printSingleSourceResult() {
		for (VertexNode vertexNode : vertexNodes) {
			Vertex v = vertexNode.vertex;
			System.out.printf("vertex %s: parent=%s, distance=%d%n", v, v.parent, v.distance);
		}
	}
	
	public void printPath(Vertex targetVertex) {
		printPath(getVertexNode(targetVertex));
	}
	
	private void printPath(VertexNode targetNode) {
		if (sourceNode == targetNode) System.out.print(sourceNode);
		else if (targetNode.vertex.parent == null) {
			System.out.print("no path from " + sourceNode + " to " + targetNode);
		} else {
			printPath(targetNode.vertex.parent);
			System.out.print(", " + targetNode);
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
	
	public static void main(String[] args) {
		DijkstraGraph graph = new DijkstraGraph();
		Vertex s = graph.addVertex(new Vertex("s"));
		Vertex t = graph.addVertex(new Vertex("t"));
		Vertex x = graph.addVertex(new Vertex("x"));
		Vertex y = graph.addVertex(new Vertex("y"));
		Vertex z = graph.addVertex(new Vertex("z"));
		graph.addEdge(s, t, 10);
		graph.addEdge(s, y, 5);
		graph.addEdge(t, x, 1);
		graph.addEdge(t, y, 2);
		graph.addEdge(x, z, 4);
		graph.addEdge(y, t, 3);
		graph.addEdge(y, x, 9);
		graph.addEdge(y, z, 2);
//		graph.addEdge(y, s, 5);
		graph.addEdge(z, x, 6);
		graph.addEdge(z, s, 7);
		
		graph.setSourceVertex(s);
		graph.getSingleSourceShortedPath();
		graph.printSingleSourceResult();
	}
}
