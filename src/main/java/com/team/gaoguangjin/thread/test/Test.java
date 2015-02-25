package com.team.gaoguangjin.thread.test;

public class Test {
	int i = 0, j = 0;
	
	// static volatile int i = 0, j = 0;
	
	// static synchronized void one() {
	void one() {
		i++;
		j++;
	}
	
	void two() {
		if (i != j) {
			System.out.println("i=" + i + " j=" + j);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		demo();
	}
	
	private static void demo() throws InterruptedException {
		final Test test = new Test();
		for (int i = 0; i < 20000; i++) {
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					test.one();
				}
			});
			Thread t2 = new Thread(new Runnable() {
				public void run() {
					test.two();
				}
			});
			
			t1.start();
			t2.start();
		}
		Thread.sleep(5000);
	}
}
