package com.team.gaoguangjin.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:InterruptTest.java
 * @Description: interrupt()方法去中断一个阻塞状态的线程 sleep()、wait() 、jion()
 * @author gaoguangjin
 * @Date 2015-3-23 下午2:33:53
 */
@Slf4j
public class InterruptTest {
	public static void main(String[] args) {
		Thread thread = thread("线程1");
		thread.start();
		thread.interrupt();// 中断一个线程，让线程抛出异常
	}
	
	private static Thread thread(String name) {
		Thread thread = new Thread(name) {
			public void run() {
				log.info("【1】运行线程=======" + Thread.currentThread().getName() + "开始执行,让线程一直睡眠");
				try {
					Thread.sleep(1000);
					log.info("线程=======" + Thread.currentThread().getName() + "睡眠结束了");
				} catch (InterruptedException e) {
					log.info("【2】线程被interrupt()中断了！！！");// 调用interrupt（）方法后会进入catch部分
				}
				log.info("【3】运行线程推出执行完毕");
			}
		};
		return thread;
	}
}
