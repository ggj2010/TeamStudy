package com.team.gaoguangjin.javabase.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import lombok.extern.slf4j.Slf4j;

/**
 *自己实现链表结构
 * header
 *A->header
 * A->B->header
 * @author:gaoguangjin
 * @date 2017/6/22 16:39
 */
@Slf4j
public class MyLinkedList<E> implements List<E> {
	
	Node first = null;
	
	Node end = null;
	
	Node head = null;
	
	int size = 0;
	
	MyLinkedList() {
		// head 节点
		head = new Node(null);
	}
	
	public static void main(String[] args) {
		List<String> list = new MyLinkedList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		log.info(list.get(0));
		log.info(list.get(1));
		log.info(list.get(2));
		log.info(list.get(3));
		log.info(list.get(4));
	}
	
	@Override
	public int size() {
		return size;
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
	
	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<E>(e);
		// ab->acb ->adcb
		// newNode.prev=head.next;
		// newNode.next=head;
		// head.next.prev=newNode;
		// head.next=newNode;
		// ab ->abc->abcd
		newNode.prev = head.prev;
		newNode.next = head;
		head.prev.next = newNode;
		head.prev = newNode;
		if (size() == 0) {
			first = newNode;
		}
		end = newNode;
		size++;
		return true;
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
		if (! (index >= 0 && index <= size - 1)) {
			throw new IndexOutOfBoundsException("数组越出" + index);
		}
		Node<E> value = null;
		// 小于一般那就从头开始搜索
		if (index < (size >> 1)) {
			value = first;
			for(int i = 0; i < index; i++) {
				value = value.next;
			}
		} else {
			value = end;
			for(int i = size - 1; i > index; i--) {
				value = value.prev;
			}
		}
		return value.item;
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
	
	class Node<E> {
		
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
}
