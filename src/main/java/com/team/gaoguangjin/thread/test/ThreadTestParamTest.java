package com.team.gaoguangjin.thread.test;

public class ThreadTestParamTest {
	public static void main(String[] args) {
		Runnable r = new HelloThread();
		
		Thread t1 = new Thread(r);
		
		// r = new HelloThread();
		
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}

class HelloThread implements Runnable {
	int i;
	
	public void run() {
		// int i = 0;
		while (true) {
			System.out.println("number: " + i++);
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (50 == i) {
				break;
			}
		}
	}
}
