package com.team.gaoguangjin.thread.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:TestDeadLock.java
 * @Description: 死锁的产生，synchronized的对象 依赖另外一个对象
 * @author gaoguangjin
 * @Date 2015-3-23 下午1:25:18
 */
@Slf4j
public class TestDeadLock implements Runnable {
	public int flag = 1;
	static Object o1 = new Object(), o2 = new Object();// 必须要是static的噢
			
	public void run() {
		System.out.println("flag=" + flag);
		// 当flag==1锁住o1
		if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.sleep(500);// 不会释放线程锁
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 只要锁住o2就完成
				synchronized (o2) {
					System.out.println("1");
				}
			}
		}
		// 如果flag==0锁住o2
		if (flag == 0) {
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 只要锁住o1就完成
				synchronized (o1) {
					System.out.println("0");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// 实例2个线程类
		TestDeadLock td1 = new TestDeadLock();
		TestDeadLock td2 = new TestDeadLock();
		td1.flag = 1;
		td2.flag = 0;
		// 开启2个线程
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		t1.start();
		t2.start();
	}
}
