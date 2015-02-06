package com.team.gaoguangjin.深入java虚拟机.栈溢出;

public class JavaVMStackOOM {
	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					dontStop();
					
				}
			});
			thread.start();
		}
	}
	
	private void dontStop() {
		System.out.println("==");
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM over = new JavaVMStackOOM();
		over.stackLeakByThread();
		
	}
	
}
