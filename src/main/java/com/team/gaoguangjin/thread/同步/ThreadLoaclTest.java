package com.team.gaoguangjin.thread.同步;

/**
 * @ClassName:ThreadLoaclTest.java
 * @Description: 本地线程变量，每一个线程都可以有一个独立线程安全变量。
 * @author gaoguangjin
 * @Date 2015-4-7 下午5:27:46
 */
public class ThreadLoaclTest {
	public static void main(String[] args) {
		// demo1();
		
		demo2();// 测试多个线程访问同一个变量
	}
	
	/**
	 * @Description:tl这个变量是共享的，每个线程都有一个独立的本地线程变量，不会产生线程安全问题
	 * @return:void
	 */
	private static void demo2() {
		
		final ThreadLocal<Integer> tl = new ThreadLocal<Integer>() {
			// 重写初始化值
			protected Integer initialValue() {
				return 1;
			}
		};
		
		new Thread() {
			public void run() {
				tl.set(2);
				try {
					System.out.println(Thread.currentThread().getName() + "线程休眠" + "==》赋值2");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "获取值：" + tl.get());
			}
		}.start();
		
		new Thread() {
			public void run() {
				tl.set(3);
				try {
					System.out.println(Thread.currentThread().getName() + "线程休眠" + "==》赋值3");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "获取值：" + tl.get());
			}
		}.start();
		
	}
	
	private static void demo1() {
		ThreadLocal<String> tl = new ThreadLocal<String>() {
			// 重写初始化值
			protected String initialValue() {
				return "a";
			}
		};
		
		String a = tl.get();
		System.out.println(a);
		tl.set("b");
		System.out.println(tl.get());
	}
}
