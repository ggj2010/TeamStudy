package com.team.gaoguangjin.thread.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:WatiTest2.java
 * @Description: 测试wait
 * @author gaoguangjin
 * @Date 2015-3-23 下午11:18:13
 */
@Slf4j
public class WatiTest2 {
	boolean flag;
	String value;
	
	public static void main(String[] args) {
		WatiTest2 wt = new WatiTest2();
		wt.main(wt);
	}
	
	public void main(WatiTest2 wt) {
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					setValue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		log.info("得到结果：" + wt.getValue());
		log.info("得到结果：" + wt.getValue());
	}
	
	public synchronized String getValue() {
		try {
			log.info("【1】获取值");
			while (!flag) {
				log.info("【2】我在等待");
				wait();
				log.info("值已经获取到，被通知了");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public synchronized void setValue() {
		flag = true;
		value = "测试的噢";
		notifyAll();
	}
}
