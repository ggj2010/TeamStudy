package com.team.gaoguangjin.thread.test;

public class ThreaViolate implements Runnable {
	// public volatile boolean isRunning = true;
	
	public boolean isRunning = true;
	
	public static void main(String[] args) {
		ThreaViolate pc = new ThreaViolate();
		for (int i = 0; i < 4000; i++) {
			new Thread(pc).start();
		}
		pc.stop();
	}
	
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("1");
		}
		System.out.println(Thread.currentThread().getId() + " stop");
	}
	
	public void stop() {
		System.out.println("stop" + "被调用了=====================" + isRunning);
		isRunning = false;
		
	}
	
}
