package com.team.gaoguangjin.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:SynchronizedTest.java
 * @Description:synchronized同步关键字使用
 * @author gaoguangjin
 * @Date 2015-3-23 下午11:36:42
 */
@Slf4j
public class SynchronizedTest {
	public static void main(String[] args) {
		SynchronizedTest st = new SynchronizedTest();
		st.test1();// 当一个线程正在访问一个对象的synchronized方法，那么其他线程不能访问该对象的其他synchronized方法
	}
	
	private void test1() {
		new Thread() {
			public void run() {
				method1();
			}
		}.start();
		new Thread() {
			public void run() {
				// method2();// 不可以访问的
				method3();// 不是synchronized方法，当method1是synchronized方法被线程访问时候，可以访问此方法
			}
		}.start();
	}
	
	protected synchronized void method2() {
		while (true) {
			log.info("do nothing2");
		}
	}
	
	protected synchronized void method1() {
		while (true) {
			log.info("do nothing1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Description:非synchronized方法
	 * @return:void
	 */
	protected void method3() {
		while (true) {
			log.info("do nothing3");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
