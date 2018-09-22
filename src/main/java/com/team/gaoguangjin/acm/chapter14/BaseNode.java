package com.team.gaoguangjin.acm.chapter14;


public class BaseNode<T> {	
	private Color color;
	private T key;
	private BaseNode<T> left;
	private BaseNode<T> right;
	private BaseNode<T> parent;
	
	public BaseNode() {}
	public BaseNode(T k, Color c, BaseNode<T> p, BaseNode<T> l, BaseNode<T> r) {
		key = k; color = c; left = l; right = r; parent = p;
	}
		
	public void setLeft(BaseNode<T> left) {
		this.left = left;
	}
	
	public BaseNode<T> getLeft() {
		return left;
	}
	
	public void setRight(BaseNode<T> right) {
		this.right = right;
	}
	
	public BaseNode<T> getRight() {
		return right;
	}
	
	public void setKey(T data) {
		this.key = data;
	}
	public T getKey() {
		return key;
	}
	
	void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	
	public void setParent(BaseNode<T> parent) {
		this.parent = parent;
	}
	public BaseNode<T> getParent() {
		return parent;
	}
	
	public String toString() { return key.toString(); }
}