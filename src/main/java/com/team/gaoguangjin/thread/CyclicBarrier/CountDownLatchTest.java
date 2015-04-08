package com.team.gaoguangjin.thread.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName:CountDownLatchTest.java
 * @Description: CountDownLatchTest,CyclicBarrier
 * 都能实现等待功能，比如。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 * @author gaoguangjin
 * @see：http://www.cnblogs.com/dolphin0520/p/3920397.html
 * @Date 2015-3-21 下午7:38:34
 */
public class CountDownLatchTest {
	public static void main(String[] args) {
		
		// test1();
		test2();
		
	}
	
	private static void test1() {
		final CountDownLatch latch = new CountDownLatch(2);
		thread(latch);
		thread(latch);
		try {
			System.out.println("等待2个子线程执行完毕...");
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		main();
		
	}
	
	/**
	 * @Description: 挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
	 * @return:void
	 */
	private static void test2() {
		CyclicBarrier latch = new CyclicBarrier(2, new MainThread());
		thread2(latch);
		thread2(latch);
	}
	
	private static void thread(final CountDownLatch latch) {
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("子线程执行结束");
				latch.countDown();// 将count值减1
			}
		}.start();
		
	}
	
	private static void thread2(final CyclicBarrier latch) {
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("子线程执行结束");
				try {
					latch.await();// 将count值减1
					System.out.println("所有线程写入完毕，继续处理其他任务...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	private static void main() {
		System.out.println("子线程执行之后执行主方法");
		
	}
	
}
