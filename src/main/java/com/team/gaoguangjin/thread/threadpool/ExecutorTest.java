package com.team.gaoguangjin.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ExecutorTest.java
 * @Description: 线程池使用
 * @author gaoguangjin
 * @Date 2015-3-20 上午11:58:31
 */
@Slf4j
public class ExecutorTest {
	static long begintime;
	static long endtime;
	
	public static void main(String[] args) throws InterruptedException {
		
		testNormal();
		
		// testByThreadPool();
		
	}
	
	private static void testByThreadPool() throws InterruptedException {
		begintime = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 2000; i++) {// 216
			pool.execute(new ThreadTest());
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);// 用于等待子线程结束，再继续执行下面的代码。该例中我设置一直等着子线程结束。输出时间
		endtime = System.currentTimeMillis();
		log.info("testByThreadPool总花费时间=========：" + (endtime - begintime));
	}
	
	private static void testNormal() {// 301
		begintime = System.currentTimeMillis();
		
		for (int i = 0; i < 2000; i++) {
			new ThreadTest().start();
		}
		endtime = System.currentTimeMillis();
		
		while (true) {
			if (Thread.activeCount() == 1)
				break;
		}
		log.info("总花费时间=========：" + (endtime - begintime));
	}
	
	public static class ThreadTest extends Thread {
		public void run() {
			log.info("执行线程噢。。。。。。");
			endtime = System.currentTimeMillis();
		}
	}
	
}
