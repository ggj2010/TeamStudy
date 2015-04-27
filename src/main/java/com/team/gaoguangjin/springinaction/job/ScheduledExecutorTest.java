package com.team.gaoguangjin.springinaction.job;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:ScheduledExecutorTest.java
 * @Description: 定时任务调度,可以并行执行
 * @author gaoguangjin
 * @Date 2015-4-24 上午10:47:47
 */
public class ScheduledExecutorTest {
	
	public static void main(String[] args) {
		// ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		TheadTest t = new TheadTest();
		Thread thread = new Thread(t);
		// exec.scheduleAtFixedRate(thread, 500, 1000, TimeUnit.MILLISECONDS);
		service.schedule(thread, 50, TimeUnit.MILLISECONDS);
		service.schedule(thread, 50, TimeUnit.MILLISECONDS);
	}
	
	public static class TheadTest implements Runnable {
		public void run() {
			System.out.println("begin    :" + new Date());
			for (long i = 0; i < 10; i++) {
				for (long a = 0; a < 100000000; a++) {
				}
			}
			System.out.println("end");
			
		}
		
	}
}
