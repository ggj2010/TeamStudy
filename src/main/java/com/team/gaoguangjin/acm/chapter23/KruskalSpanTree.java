package com.team.gaoguangjin.acm.chapter23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KruskalSpanTree {
	private List<VertexNode> vertexes = new ArrayList<VertexNode>();
	private Map<Vertex, VertexNode> nodeByVertex = new HashMap<Vertex, VertexNode>();
	
	public KruskalSpanTree() {}

	/**
	 * 得到最小生成树。返回结果是最小生成树的所有边，其顶点就是该图的所有顶点。
	 */
	public List<Edge> getMST() {
		for (VertexNode vertexNode : vertexes) {
			SetNode.makeSet(vertexNode.vertex);
		}
		List<Edge> mstEdges = new ArrayList<Edge>();
		List<Edge> edges = sortEdges();
		for (Edge edge : edges) {
			if (SetNode.findSet(edge.sourceVertex.setNode) != SetNode.findSet(edge.targetVertex.setNode)) {
				mstEdges.add(edge);
				SetNode.union(edge.sourceVertex.setNode, edge.targetVertex.setNode);
			}
		}
		return mstEdges;
	}
	
	/**
	 * 对图的所有边按权重进行排序。这里直接使用了Collection.sort方法，它使用的是MergeSort算法。
	 * 可以改变这里的排序算法。
	 */
	private List<Edge> sortEdges() {
		List<Edge> edges = new ArrayList<Edge>();
		for (VertexNode vertexNode : vertexes) {
			for (EdgeNode edgeNode = vertexNode.firstEdge; edgeNode != null; edgeNode = edgeNode.next) {
				edges.add(new Edge(vertexNode.vertex, edgeNode.vertexNode.vertex, edgeNode.weight));
			}
		}
		Collections.sort(edges);
		return edges;
	}


	public Vertex addVertex(Vertex v) {
		VertexNode node = new VertexNode(v);
		vertexes.add(node);
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
	
	/**
	 * 代表图的一条边。
	 */
	public static class Edge implements Comparable<Edge> {
		Vertex sourceVertex;
		Vertex targetVertex;
		int weight;
		
		public Edge(Vertex s, Vertex t, int w) { sourceVertex = s; targetVertex = t; weight = w; }
		public int compareTo(Edge o) { return weight - o.weight; }
		public String toString() { return sourceVertex+"->"+targetVertex+"("+weight+")"; }
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
		SetNode setNode;
		
		public Vertex(String data) { this.data = data; }
		public String toString() { return (data); }
	}
	
	// 实现可合并的集合。见第21章。
	private static class SetNode {
		private SetNode parent;
		private int rank;
		private Vertex vertex;

		public SetNode(Vertex v) { this.vertex = v; }

		public static SetNode makeSet(Vertex v) {
			SetNode x = new SetNode(v);
			v.setNode = x.parent = x;
			return x;
		}

		public static SetNode union(SetNode x, SetNode y) {
			return link(findSet(x), findSet(y));
		}

		private static SetNode link(SetNode x, SetNode y) {
			if (x.rank > y.rank) {
				y.parent = x;
				return x;
			} else {
				x.parent = y;
				if (x.rank == y.rank) {
					y.rank++;
				}
				return y;
			}
		}

		private static SetNode findSet(SetNode x) {
			if (x != x.parent) {
				x.parent = findSet(x.parent);
			}
			return x.parent;
		}
		
		public String toString() { return String.valueOf(vertex); }
	}
	
	public static void main(String[] args) {
		KruskalSpanTree graph = new KruskalSpanTree();
		Vertex a = graph.addVertex(new Vertex("a"));
		Vertex b = graph.addVertex(new Vertex("b"));
		Vertex c = graph.addVertex(new Vertex("c"));
		Vertex d = graph.addVertex(new Vertex("d"));
		Vertex e = graph.addVertex(new Vertex("e"));
		Vertex f = graph.addVertex(new Vertex("f"));
		Vertex g = graph.addVertex(new Vertex("g"));
		Vertex h = graph.addVertex(new Vertex("h"));
		Vertex i = graph.addVertex(new Vertex("i"));
		graph.addUndirectionalEdge(a, b, 4);
		graph.addUndirectionalEdge(a, h, 8);
		graph.addUndirectionalEdge(b, h, 11);
		graph.addUndirectionalEdge(b, c, 8);
		graph.addUndirectionalEdge(h, i, 7);
		graph.addUndirectionalEdge(h, g, 1);
		graph.addUndirectionalEdge(i, c, 2);
		graph.addUndirectionalEdge(i, g, 6);
		graph.addUndirectionalEdge(c, d, 7);
		graph.addUndirectionalEdge(c, f, 4);
		graph.addUndirectionalEdge(g, f, 2);
		graph.addUndirectionalEdge(d, f, 14);
		graph.addUndirectionalEdge(d, e, 9);
		graph.addUndirectionalEdge(f, e, 10);
		
		List<Edge> edges = graph.getMST();
		for (Edge edge : edges) {
			System.out.println(edge);
		}
	}
}
