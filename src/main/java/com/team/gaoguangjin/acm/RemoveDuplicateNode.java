package com.team.gaoguangjin.acm;

/**
 * 给定一个排序好的队列，去除重复的队列
 * Given 1->1->2, return 1->2.
 Given 1->1->2->3->3, return 1->2->3.
 * @author:gaoguangjin
 * @date 2017/7/17 17:25
 */
public class RemoveDuplicateNode {
	
	public static void main(String[] args) {
		Node head = new Node<Integer>(null, 1);
		head.next = new Node<Integer>(null, 1);
        head.next.next= new Node<Integer>(null, 2);
        head.next.next.next= new Node<Integer>(null, 3);
	}
	
	static class Node<T> {
		Node<T> next;
		T value;
		Node(Node<T> next, T value) {
			this.next = next;
			this.value = value;
		}
	}
}
