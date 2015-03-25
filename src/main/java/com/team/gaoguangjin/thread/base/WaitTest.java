package com.team.gaoguangjin.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:WaitTest.java
 * @Description: wait和notify会释放线程锁，必需要搭配synchronized使用
 * @author gaoguangjin
 * @Date 2015-3-23 下午3:00:49
 */
@Slf4j
public class WaitTest {
	Object object = new Object();
	
	public String state = "【等待】";
	
	public static void main(String[] args) {
		WaitTest wt = new WaitTest();
		wt.waitTest();
		wt.notifyTest();
	}
	
	private void waitTest() {
		new Thread() {
			public void run() {
				synchronized (object) {
					for (int i = 0; i < 10; i++) {
						if (i == 5) {
							try {
								log.info("调用等待");
								object.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						log.info("waitTest" + i + state);
					}
				}
			}
		}.start();
		
	}
	
	private void notifyTest() {
		new Thread() {
			public void run() {
				synchronized (object) {
					for (int i = 0; i < 10; i++) {
						log.info("notifyTest" + i);
						if (i == 7) {
							log.info("调用通知");
							state = "【通知】";
							object.notify();
						}
					}
				}
			}
		}.start();
	}
	
}
