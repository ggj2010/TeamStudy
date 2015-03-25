package com.team.gaoguangjin.thread.test;

public class ThreaSafe implements Runnable {
	public boolean isRunning = true;
	int m = 0;
	
	public static void main(String[] args) {
		ThreaSafe pc = new ThreaSafe();
		for (int i = 0; i < 2; i++) {
			new Thread(pc).start();
		}
		
	}
	
	public void run() {
		while (isRunning) {
			// synchronized (this) {//线程安全
			for (int i = 0; i < 10000; i++) {
				m = m + 1;
			}
			
			System.out.println(m);
			// }
			break;
			
		}
	}
	
	public void stop() {
		isRunning = false;
	}
	
}
