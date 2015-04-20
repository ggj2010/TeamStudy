package com.team.gaoguangjin.thread.pdf;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQ {
	private Object notEmpty = new Object();
	private Queue<Object> linkedList = new LinkedList<Object>();
	
	public static void main(String[] args) throws InterruptedException {
		// test();
		test2();
	}
	
	private static void test() throws InterruptedException {
		BlockingQ bq = new BlockingQ();
		// bq.take();
		bq.offer("123");
		System.out.println(bq.take());
	}
	
	private static void test2() {
		final BlockingQ bq = new BlockingQ();
		bq.offer("123");
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					try {
						Object m = bq.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
	}
	
	public Object take() throws InterruptedException {
		synchronized (notEmpty) {
			if (linkedList.size() == 0) {
				notEmpty.wait();
			}
			return linkedList.poll();
		}
	}
	
	public void offer(Object object) {
		synchronized (notEmpty) {
			if (linkedList.size() == 0) {
				notEmpty.notifyAll();
			}
			linkedList.add(object);
		}
	}
}
