package com.team.gaoguangjin.thread.pdf;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BlockingQueueTest {
	static CyclicBarrier sb = new CyclicBarrier(20);
	static BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			concurrentEnvironment(i);
		}
		
		for (int i = 0; i < 25; i++) {
			bq.put("" + i);
		}
	}
	
	private static void concurrentEnvironment(int i) {
		new Thread(i + "线程") {
			public void run() {
				try {
					Thread.sleep(1000);
					sb.await();
					domethod();// 达到零界点时候同时执行这个方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	protected static void domethod() throws InterruptedException {
		// String result = bq.take();
		System.out.println(Thread.currentThread().getName() + ":" + new Date() + "===" + bq.take());
		
		// domethod(result);
	}
	
	private static void domethod(String result) {
		// System.out.println("处理result" + result);
	}
}
