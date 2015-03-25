package com.team.gaoguangjin.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
	}
	
	public static void main(String[] args) {
		// 创建一个单线程的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		// 创建固定大小的线程池
		// ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// 创建一个可缓存的线程池
		// ExecutorService pool = Executors.newCachedThreadPool();
		
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new SingleThreadExecutor();
		Thread t2 = new SingleThreadExecutor();
		Thread t3 = new SingleThreadExecutor();
		Thread t4 = new SingleThreadExecutor();
		Thread t5 = new SingleThreadExecutor();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}
}
