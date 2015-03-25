package com.team.gaoguangjin.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.druid.util.DaemonThreadFactory;

/**
 * @ClassName:ThreadPoolExecutor.java
 * @Description: 有界队列使用
 * @author gaoguangjin
 * @Date 2015-3-20 下午2:32:16
 */
@Slf4j
public class ThreadPoolTest {
	private final static String poolName = "mypool";
	static private ThreadPoolTest threadFixedPool = null;
	public ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2);
	private ExecutorService executor;
	
	static public ThreadPoolTest getFixedInstance() {
		return threadFixedPool;
	}
	
	private ThreadPoolTest(int num) {
		executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, queue, new DaemonThreadFactory(poolName), new ThreadPoolExecutor.AbortPolicy());
	}
	
	public void execute(Runnable r) {
		executor.execute(r);
	}
	
	public static void main(String[] params) {
		class MyRunnable implements Runnable {
			public void run() {
				System.out.println("OK!");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		int count = 0;
		
		for (int i = 0; i < 2; i++) {
			try {
				ThreadPoolTest.getFixedInstance().execute(new MyRunnable());
			} catch (RejectedExecutionException e) {
				e.printStackTrace();
				count++;
			}
			
		}
		
		try {
			
			log.info("queue size:" + ThreadPoolTest.getFixedInstance().queue.size());
			
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Reject task: " + count);
		
	}
	
}
