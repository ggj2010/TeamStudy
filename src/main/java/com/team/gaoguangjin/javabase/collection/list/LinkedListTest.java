package com.team.gaoguangjin.javabase.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 双向链表
 * @author:gaoguangjin
 * @date 2016/12/21 10:01
 */
public class LinkedListTest<E> implements List<E> {
	
	int size = 0;
	
	// 定义头结点
	private Node head = new Node(null);
	
	public static void main(String[] args) {
		LinkedListTest<String> myLinkedList = new LinkedListTest<String>();
		myLinkedList.add("a");
		myLinkedList.add("b");
		myLinkedList.add("c");
		System.out.println("" + myLinkedList.get(0));
	}
	
	@Override
	public int size() {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public boolean contains(Object o) {
		return false;
	}
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}
	
	@Override
	public Object[] toArray() {
		return new Object[0];
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
	/**
	 * 默认是往后追加
	 * @param e
	 * @return
	 */
	@Override
	public boolean add(E e) {
		addLast(e);
		return false;
	}
	
	private void addLast(E e) {
		addBefore(new Node(e), head);
	}
	
	private void addBefore(Node newNode, Node node) {
		// AB往这中间添加C 也就是ACB，新增加的节点肯定是没有任何关联的,是未知关系的，所以得从A节点下手，
		// // 将指定节点做为新节点的前向指针
		// newNode.prev = node;
		// // 将指定节点的后向指针，做为新节点的后向指针
		// newNode.next = node.next;
		// node.next.prev = newNode;
		// node.next = newNode;
		// AB往A前面添加C也就是CAB
		newNode.next = node;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.prev = newNode;
	}
	
	@Override
	public boolean remove(Object o) {
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	
	@Override
	public void clear() {
	}
	
	@Override
	public E get(int index) {
		return null;
	}
	
	@Override
	public E set(int index, E element) {
		return null;
	}
	
	@Override
	public void add(int index, E element) {
	}
	
	@Override
	public E remove(int index) {
		return null;
	}
	
	@Override
	public int indexOf(Object o) {
		return 0;
	}
	
	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}
	
	@Override
	public ListIterator<E> listIterator() {
		return null;
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}
	
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}
	
	private static class Node<E> {

		E item;

		Node<E> next;

		Node<E> prev;

		Node(E element, Node<E> next, Node<E> prev) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}

		Node(E element) {
			this.item = element;
			this.next = this;
			this.prev = this;
		}

		public String toString() {
			return item.toString();
		}
	}
	
	class MyListIterator<E> implements Iterator<E> {

		Node<E> currentNode;
		int size;

		MyListIterator(Node<E> currentNode) {
			this.currentNode = currentNode;
			size=0;
		}

		@Override
		public boolean hasNext() {

			return false;
		}

		@Override
		public E next() {
			return null;
		}

		@Override
		public void remove() {
		}

	}
}
