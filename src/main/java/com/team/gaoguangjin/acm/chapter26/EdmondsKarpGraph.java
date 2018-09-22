package com.team.gaoguangjin.acm.chapter26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 使用Edmonds-Karp算法来最大流。Edmonds-Karp算法是Ford-Fulkerson算法的特化，
 * Edmonds-Karp算法使用广度优先搜索来找到一条增广路径，而Ford-Fulkerson没有规定
 * 使用何种算法来找到增广路径，这可能选择非常坏的增广路径，从而致使运行时间很坏，最坏
 * 情况可能为O(V|f|)，|f|为最大流的值。Ford-Fulkerson算法的复杂度为O(VE^2)。
 */
public class EdmondsKarpGraph {
	private static final boolean DEBUG = true;
	
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();

	private VertexNode sourceNode;
	private VertexNode targetNode;
		
	public void setSourceVertex(Vertex sourceVertex) {
		this.sourceNode = getVertexNode(sourceVertex);
	}
	public void setTargetVertex(Vertex targetVertex) {
		this.targetNode = getVertexNode(targetVertex);
	}
	
	public int computeMaxFlow() {
		for (VertexNode vertexNode : vertexNodes) {
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				edgeNode.flow = 0;
			}
		}
		while (true) {
			BFS(sourceNode);
			Edge edge = targetNode.vertex.parent;
			if (edge == null) break;
			int residualCapacity = Integer.MAX_VALUE;
			StringBuilder sbPath = null;
			if (DEBUG) { sbPath = new StringBuilder(targetNode.toString()); }
			for (; edge != null; edge = edge.sourceNode.vertex.parent) {
				if (DEBUG) sbPath.append(" <--(").append(edge.edgeNode.capacity - edge.edgeNode.flow)
								.append(")-- ").append(edge.sourceNode);
				if (residualCapacity > (edge.edgeNode.capacity - edge.edgeNode.flow)) {
					residualCapacity = edge.edgeNode.capacity - edge.edgeNode.flow;
				}
			}
			if (DEBUG) System.out.println(sbPath);
			
			for (edge = targetNode.vertex.parent; edge != null; edge = edge.sourceNode.vertex.parent) {
				edge.edgeNode.flow += residualCapacity;
				edge.edgeNode.reverseEdge.flow = -edge.edgeNode.flow;
			}
		}
		int maxflow = 0;
		for (EdgeNode edgeNode = sourceNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
			if (edgeNode.flow > 0) maxflow += edgeNode.flow;
		}
		return maxflow;
	}
	
	public void printMaxFlowResult() {
		for (VertexNode vertexNode : vertexNodes) {
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				if (edgeNode.flow > 0) {
					System.out.printf("%-5s -> %5s: %2d/%-2d%n", vertexNode, edgeNode.vertexNode, edgeNode.flow, edgeNode.capacity);
				}
			}
		}
	}
	
	public void BFS(VertexNode s) {
		for (VertexNode vertexNode : vertexNodes) {
			vertexNode.vertex.color = Vertex.WHITE;
			vertexNode.vertex.parent = null;
		}
		s.vertex.color = Vertex.GRAY;
		LinkedList<VertexNode> queue = new LinkedList<VertexNode>();
		queue.addLast(s);
		while (!queue.isEmpty()) {
			VertexNode uNode = queue.removeFirst();
			for (EdgeNode vEdge = uNode.firstEdge; vEdge != null; vEdge = vEdge.next) {
				if (vEdge.capacity == vEdge.flow) continue; // it's not an edge
				Vertex v = vEdge.vertexNode.vertex;
				if (v.color == Vertex.WHITE) {
					v.color = Vertex.GRAY;
					v.parent = new Edge(uNode, vEdge);
					queue.addLast(vEdge.vertexNode);
				}
			}
			uNode.vertex.color = Vertex.BLACK;
		}
	}
	
	public Vertex addVertex(Vertex v) {
		VertexNode node = new VertexNode(v);
		vertexNodes.add(node);
		nodeByVertex.put(v, node);
		return v;
	}
	
	/**
	 * 添加v1到v2的边，其容量为capacity;添加v2到v1的边其容量为reverseCapacity。
	 */
	public void addEdge(Vertex v1, Vertex v2, int capacity, int reverseCapacity) {
		VertexNode v1Node = getVertexNode(v1);
		VertexNode v2Node = getVertexNode(v2);
		EdgeNode edgeNode = new EdgeNode(v2Node, capacity);
		EdgeNode reverseEdgeNode = new EdgeNode(v1Node, reverseCapacity);
		edgeNode.reverseEdge = reverseEdgeNode;
		reverseEdgeNode.reverseEdge = edgeNode;
		
		edgeNode.next = v1Node.firstEdge;
		v1Node.firstEdge = edgeNode;
		reverseEdgeNode.next = v2Node.firstEdge;
		v2Node.firstEdge = reverseEdgeNode;
	}
	/**
	 * 添加v1到v2的边，其容量为capacity;添加v2到v1的边其容量为0。
	 */
	public void addEdge(Vertex v1, Vertex v2, int capacity) {
		addEdge(v1, v2, capacity, 0);
	}
	
	private VertexNode getVertexNode(Vertex v) {
		return nodeByVertex.get(v);
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
		int capacity;
		int flow;
		EdgeNode reverseEdge; // 保持对反向边的引用
		
		public EdgeNode(VertexNode v, int c) { vertexNode = v; capacity = c;}
		public String toString() { return vertexNode.toString()+"("+capacity+")"; }
		public int compareTo(EdgeNode o) { return capacity - o.capacity; }
	}
	
	private static class Edge {
		VertexNode sourceNode;
		EdgeNode edgeNode;
		public Edge(VertexNode s, EdgeNode e) { sourceNode = s; edgeNode = e; }
	}
	public static class Vertex {
		public static final int WHITE = 0;
		public static final int GRAY = 1;
		public static final int BLACK = 2;
		
		String data;
		Edge parent;
		private int color;
		
		public Vertex(String data) { this.data = data; }
		public String toString() { return (data); }
	}
	
	public static void main(String[] args) {
		EdmondsKarpGraph graph = new EdmondsKarpGraph();
		Vertex s = graph.addVertex(new Vertex("s"));
		Vertex v1 = graph.addVertex(new Vertex("v1"));
		Vertex v2 = graph.addVertex(new Vertex("v2"));
		Vertex v3 = graph.addVertex(new Vertex("v3"));
		Vertex v4 = graph.addVertex(new Vertex("v4"));
		Vertex t = graph.addVertex(new Vertex("t"));
		graph.addEdge(s, v1, 16);
		graph.addEdge(s, v2, 13);
		graph.addEdge(v1, v3, 12);
		graph.addEdge(v1, v2, 10, 4);
		graph.addEdge(v3, v2, 9);
		graph.addEdge(v2, v4, 14);
		graph.addEdge(v4, v3, 7);
		graph.addEdge(v3, t, 20);
		graph.addEdge(v4, t, 4);
		
		graph.setSourceVertex(s);
		graph.setTargetVertex(t);
		int maxflow = graph.computeMaxFlow();
		System.out.println("max flow is : " + maxflow);
		graph.printMaxFlowResult();
	}
}
