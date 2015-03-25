package com.team.gaoguangjin.thread.pdf;

import org.mortbay.log.Log;

/**
 * @ClassName:ThreadTest.java
 * @Description: 阿里内部培训的pdf
 * @author gaoguangjin
 * @Date 2015-3-20 下午3:27:58
 */
public class ThreadTest {
	
	public static void main(String[] args) {
		// 创建线程要记得带名称，方便定位
		// demo();
		
		demo1();
	}
	
	private static void demo1() {
		InnerThread it = new InnerThread();
		for (int i = 0; i < 10; i++) {
			Thread th = new Thread(it);
			th.setName("线程" + (i + 1));
			th.start();
		}
		
	}
	
	public static class InnerThread implements Runnable {
		
		private int m;
		boolean flag = true;
		
		public void run() {
			test();
		}
		
		/**
		 * @Description: 解除那些在该对象上调用wait()方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。
		 * @see如果当前线程不是对象所得持有者，该方法抛出一个java.lang.IllegalMonitorStateException 异常” 如果不加synchronized
		 */
		private synchronized void test() {
			// private void test() {
			m++;
			int temp = m;
			if (m > 5 & m < 10) {
				try {
					System.out.println(temp + "我在等待....." + Thread.currentThread().getName());
					wait();
					System.out.println(temp + "被唤醒了噢！" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
				}
			} else if (m > 0 && m < 5) {
				System.out.println(temp + "我是小于5的线程 在跑的噢" + Thread.currentThread().getName());
			} else if (m == 5) {
				dointrerupt(temp);
			} else {
				notifyAll();
				System.out.println(temp + "我是10的线程 调用唤醒了噢噢" + Thread.currentThread().getName());
			}
		}
		
		private void dointrerupt(int key) {
			while (!Thread.interrupted()) {
				for (int i = 0; i < 10; i++) {
					System.out.println("我是线程" + key + "我一直在跑内部循环" + i);
					if (i == 5) {
						Thread.currentThread().interrupt();
						System.out.println("我是线程" + key + "我被interrupt了" + Thread.currentThread().getState());
					}
				}
			}
			
		}
	}
	
	private static void demo() {
		try {
			Thread thread = new Thread() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(100);
							System.out.println("什么也不做");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			thread.setName("测试线程");
			thread.start();// 线程的state 5RUNNABLE
			System.out.println(thread.getPriority() + "" + thread.getState());
		} catch (Exception e) {
			Log.info("");
		}
		
	}
	
}
