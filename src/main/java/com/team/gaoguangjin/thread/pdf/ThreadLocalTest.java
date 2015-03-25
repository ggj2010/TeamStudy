package com.team.gaoguangjin.thread.pdf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest implements Runnable {
	ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
	
	int key = 0;
	
	public static void main(String[] args) {
		ThreadLocalTest th = new ThreadLocalTest();
		ExecutorService ex = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 3; i++) {
			Thread thred = new Thread(th);
			thred.setName("线程" + i);
			thred.start();
			// ex.execute(thred);
		}
		
	}
	
	public void run() {
		tl.set(0);
		test();
	}
	
	private void test() {
		int inner=0;
		for (int i = 0; i < 3; i++) {
			tl.set(tl.get() + 1);
			key++;
			inner++;
			System.out.println(tl.get() + ":" + Thread.currentThread().getName());
			System.out.println(key + "线程不安全的key" + Thread.currentThread().getName());
			System.out.println(inner + "局部变量的key" + Thread.currentThread().getName());
		}
	}
}
