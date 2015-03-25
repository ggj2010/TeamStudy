package com.team.gaoguangjin.thread.test;

public class TestWithoutVolatile {
	// private static boolean bChanged;
	private volatile static boolean bChanged;
	
	public static void main(String[] args) throws InterruptedException {
		
		thread();
		Thread.sleep(1);
		thread2();
		
	}
	
	private static void thread() {
		new Thread() {
			public void run() {
				for (;;) {
					// 按照常理 这个会显示为false 程序永远不退出，但是因为没有加volatile，多多执行几次会出现退出的
					if (bChanged != bChanged) {
						System.out.println("!=" + bChanged);
						System.exit(0);
					}
				}
			}
		}.start();
	}
	
	private static void thread2() {
		new Thread() {
			public void run() {
				for (;;) {
					bChanged = !bChanged;
				}
			}
		}.start();
		
	}
}
