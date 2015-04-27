package com.team.gaoguangjin.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor extends Thread {
	
	public SingleThreadExecutor(String string) {
		super(string);
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
	}
	
	public static void main(String[] args) {
		
		// Java通过Executors提供四种线程池，分别为：
		// newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		// newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		// newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
		// newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		
		// demo1();
		
		demo2();
	}
	
	private static void demo1() {
		// 创建一个单线程的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		// 创建固定大小的线程池
		// ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// 创建一个可缓存的线程池
		// ExecutorService pool = Executors.newCachedThreadPool();
		
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new SingleThreadExecutor("1");
		Thread t2 = new SingleThreadExecutor("2");
		Thread t3 = new SingleThreadExecutor("3");
		Thread t4 = new SingleThreadExecutor("4");
		Thread t5 = new SingleThreadExecutor("5");
		
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
		
	}
	
	private static void demo2() {
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		
	}
}
