package com.team.gaoguangjin.acm.chapter14;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * 红黑树。
 */
public class RedBlackTree<T> extends BaseRedBlackTree<T> {
	public RedBlackTree() {}
	
	public RedBlackTree(Comparator<? super T> comparator) {
		super(comparator);
	}
	
	protected BaseNode<T> createNode(T k, Color c, BaseNode<T> p, BaseNode<T> l, BaseNode<T> r) {
		return new BaseNode<T>(k, c, p, l, r);
	}
	
	public static void main(String[] args) throws Exception {
		RedBlackTree<Integer> btree = new RedBlackTree<Integer>();
		
		Integer[] data = new Integer[] {  5, 6, 7, 8, 9, 13};
			//{20, 47, 27, 4, 23, 42, 0, 14, 18};
		for (int i = 0; i < data.length; i++) {
			btree.insert(data[i]);
		}
		System.out.println("height: " + btree.getHeight());
		
		System.out.println(btree);
		
		System.out.println("minimum: " + btree.minimum());
		System.out.println("maximum: " + btree.maximum());
		
		System.out.println("ascendingIterator");
		for (Iterator<Integer> itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		System.out.println("\ndesendingIterator");
		for (Iterator<Integer> itor = btree.desendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		btree.delete(7);
		System.out.println("\nascendingIterator");
		for (Iterator<Integer> itor = btree.ascendingIterator(); itor.hasNext();) {
			System.out.print(itor.next() + " ");
		}
		System.out.println();
		
		btree = new RedBlackTree<Integer>();
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			btree.insert(random.nextInt(200));
		}
		System.out.println("\n200 elements, height: " + btree.getHeight());
		
		try {
		for (int i = 0; i < 1000; i++) {
			btree.delete(random.nextInt(200));
		}
		} catch (Exception e) {
			System.out.println(btree.size);
			System.out.println(btree);
			throw e;
		}
		
		System.out.println("\nnow size: " + btree.size);
		btree = new RedBlackTree<Integer>();
		for (int i = 0; i < 1000; i++) {
			btree.insert(i);
		}
		System.out.println("\n" + btree.size + " elements, height: " + btree.getHeight());
	}
}
