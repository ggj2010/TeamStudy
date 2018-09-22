package com.team.gaoguangjin.acm.chapter24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对有向无回路图的单源最短路径算法。
 */
public class DagGraph {
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();
	
	
	private VertexNode sourceNode;
	
	public void setSourceVertex(Vertex s) {
		sourceNode = getVertexNode(s);
	}
	
	/**
	 * 运行时间分析：
	 * 	拓扑排序时间为O(V+E)
	 *  init调用时间为O(V)
	 *  E次relax执行时间为O(E)
	 * 因此总共时间为： O(V+E)
	 */
	public void getSingleSourceShortedPath() {
		List<VertexNode> vertexes = toplogicalSort();
		init();
		for (VertexNode vertexNode : vertexes) {
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				relax(vertexNode, edgeNode);
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
	
	private void relax(VertexNode vertexNode, EdgeNode edgeNode) {
		Vertex u = vertexNode.vertex;
		Vertex v = edgeNode.vertexNode.vertex;
		int w = edgeNode.weight;
		if (v.distance -w > u.distance) { // v.distance > u.distance + w，之所以不用加法，是为也避免溢出错误
			  							  // 也可以为 (u.distance != Integer.MAX_VALUE && v.distance > u.distance + w)
			v.distance = u.distance + w;
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
	
	public List<VertexNode> toplogicalSort() {
		LinkedList<VertexNode> result = new LinkedList<VertexNode>();
		Set<VertexNode> visitedNodes = new HashSet<VertexNode>();
		for (VertexNode vertexNode : vertexNodes) {
			if (!visitedNodes.contains(vertexNode)) {
				dfsVisit(vertexNode, result, visitedNodes);
			}
		}
		return result;
	}
	
	private void dfsVisit(VertexNode vertexNode, LinkedList<VertexNode> vertexes, Set<VertexNode> visitedNodes) {
		visitedNodes.add(vertexNode);
		for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
			if (!visitedNodes.contains(edgeNode.vertexNode)) {
				dfsVisit(edgeNode.vertexNode, vertexes, visitedNodes);
			}
		}
		vertexes.addFirst(vertexNode);
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

		public Vertex(String data) { this.data = data; }
		public String toString() { return (data); }
	}
	
	public static void main(String[] args) {
		DagGraph graph = new DagGraph();
		Vertex r = graph.addVertex(new Vertex("r"));
		Vertex s = graph.addVertex(new Vertex("s"));
		Vertex t = graph.addVertex(new Vertex("t"));
		Vertex x = graph.addVertex(new Vertex("x"));
		Vertex y = graph.addVertex(new Vertex("y"));
		Vertex z = graph.addVertex(new Vertex("z"));
		graph.addEdge(r, s, 5);
		graph.addEdge(r, t, 3);
		graph.addEdge(s, x, 6);
		graph.addEdge(s, t, 2);
		graph.addEdge(t, x, 7);
		graph.addEdge(t, y, 4);
		graph.addEdge(t, z, 2);
		graph.addEdge(x, y, -1);
		graph.addEdge(x, z, 1);
		graph.addEdge(y, z, -2);
		graph.setSourceVertex(s);
		graph.getSingleSourceShortedPath();
		
		graph.printSingleSourceResult();
	}
}
