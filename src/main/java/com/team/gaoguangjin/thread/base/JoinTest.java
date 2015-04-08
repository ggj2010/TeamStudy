package com.team.gaoguangjin.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:JoinTest.java
 * @Description:join()方法使用 。sleep(),stop(),wait().notify(),notifyall()
 * @author gaoguangjin
 * @Date 2015-3-23 下午1:33:57
 */
@Slf4j
public class JoinTest {
	public static void main(String[] args) {
		Thread thread = thread("线程1");
		Thread thread2 = thread("线程2");
		thread.start();
		thread2.start();
		try {
			log.info("主线程进入等待");
			thread.join();// 如果不加这个就可以看到效果了
			thread2.join();// 如果不加这个就可以看到效果了
			// 运行主方法
			log.info("开始运行主线程");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static Thread thread(String name) {
		Thread thread = new Thread(name) {
			public void run() {
				log.info("运行线程=======" + Thread.currentThread().getName() + "开始执行");
				try {
					Thread.sleep(3000);
					log.info("线程=======" + Thread.currentThread().getName() + "执行结束");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		return thread;
	}
	
	/**
	 * @Description: thread.join()实际上调用了wait()方法
	 * @param millis
	 * @throws InterruptedException
	 * @return:void
	 */
	public final synchronized void joins(long millis) throws InterruptedException {
		long base = System.currentTimeMillis();
		long now = 0;
		
		if (millis < 0) {
			throw new IllegalArgumentException("timeout value is negative");
		}
		
		if (millis == 0) {
			// while (isAlive()) {
			// wait(0);
			// }
		} else {
			// while (isAlive()) {
			// long delay = millis - now;
			// if (delay <= 0) {
			// break;
			// }
			// wait(delay);
			// now = System.currentTimeMillis() - base;
			// }
		}
	}
}
