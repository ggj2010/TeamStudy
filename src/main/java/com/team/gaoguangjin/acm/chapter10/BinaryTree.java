package com.team.gaoguangjin.acm.chapter10;

public class BinaryTree {
	private Node root;
	
	public Node getRoot() {
		return this.root;
	}
	
	public static class Node {
		private int data;
		private Node parent;
		private Node left;
		private Node right;
		
		public Node getLeft() {
			return this.left;
		}
		
		public Node getRight() {
			return this.right;
		}
		
		public Node getParent() {
			return this.parent;
		}
		
		public int getData() {
			return data;
		}
	}
}
