package com.team.gaoguangjin.thread.pdf;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName:ProductAndComsume.java
 * @Description: 生产者消费者模式，用blockquen实现
 * @author gaoguangjin
 * @Date 2015-4-20 下午1:37:43
 */
public class ProductAndComsume {
	final static BlockingQueue<Object> blocking = new ArrayBlockingQueue<Object>(5);
	
	public static void main(String[] args) {
		demo1();
	}
	
	private static void demo1() {
		
		new Thread("生产者") {
			public void run() {
				for (int i = 1; i < 21; i++) {
					try {
						blocking.put(i);
						System.out.println("【生产者】==》开始生产:" + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread("消费者") {
			public void run() {
				for (int i = 1; i < 11; i++) {
					try {
						int m = (Integer) blocking.take();
						System.out.println("【消费者】开始消费:" + m);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread("消费者") {
			public void run() {
				for (int i = 11; i < 21; i++) {
					try {
						int m = (Integer) blocking.take();
						System.out.println("【消费者】开始消费:" + m);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
