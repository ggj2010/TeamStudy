package com.team.gaoguangjin.acm.chapter24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Bellman-ford算法来解决单源最短路径问题。
 */
public class BellmanFordGraph {
	private List<VertexNode> vertexNodes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();
	
	private VertexNode sourceNode;
	
	public void setSourceVertex(Vertex s) {
		sourceNode = getVertexNode(s);
	}
	
	public boolean getSingleSourceShortedPath() {
		init();
		
		for (int i = 0; i < vertexNodes.size() - 1; i++) {
			for (VertexNode vertexNode : vertexNodes) {
				for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
					relax(vertexNode, edgeNode);
				}
			}
		}
		for (VertexNode vertexNode : vertexNodes) {
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
		BellmanFordGraph graph = new BellmanFordGraph();
		Vertex s = graph.addVertex(new Vertex("s"));
		Vertex t = graph.addVertex(new Vertex("t"));
		Vertex x = graph.addVertex(new Vertex("x"));
		Vertex y = graph.addVertex(new Vertex("y"));
		Vertex z = graph.addVertex(new Vertex("z"));
		graph.addEdge(t, x, 5);
		graph.addEdge(t, y, 8);
		graph.addEdge(t, z, -4);
		graph.addEdge(x, t, -2);
		graph.addEdge(y, x, -3);
		graph.addEdge(y, z, 9);
		graph.addEdge(z, x, 7);
		graph.addEdge(z, s, 2);
		graph.addEdge(s, t, 6);
		graph.addEdge(s, y, 7);
		graph.setSourceVertex(s);
		if (graph.getSingleSourceShortedPath()) {
			graph.printSingleSourceResult();
		} else {
			System.out.println("found negative circular!");
		}
	}
}
