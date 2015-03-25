package com.team.gaoguangjin.thread.style;

public class NormalRunnable implements Runnable {
	
	public static void main(String[] args) {
		NormalRunnable nr = new NormalRunnable();
		Thread thread = new Thread(nr);
		thread.setName("NormalRunnable");
		thread.start();
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("====");
		}
	}
	
}
