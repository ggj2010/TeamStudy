package com.team.gaoguangjin.thread.style;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:DeadLoopThread.java
 * @Description: 弄个死循环验证线程
 * @author gaoguangjin
 * @Date 2015-3-19 下午6:33:52
 */
@Slf4j
public class DeadLoopThread extends Thread {
	public static void main(String[] args) {
		
		 normal();
		//thread();// test方法会被执行到
	}
	
	/**
	 * @Description: test方法执行不到
	 * @return:void
	 */
	private static void normal() {
		loop();
		test();
	}
	
	private static void thread() {
		new DeadLoopThread().start();
		test();
	}
	
	public void run() {
		loop();
	}
	
	public static void test() {
		log.info("执行test方法");
	}
	
	public static void loop() {
		System.out.println("死循环");
		while (true) {
			// doNothing;
		}
		
	}
}
