package com.team.gaoguangjin.test.junittest;

import org.junit.Test;

/**
 * @ClassName:ThreadSleep.java
 * @Description:junit里面使用thread 和 sleep
 * @author gaoguangjin
 * @Date 2015-5-16 上午12:07:13
 */
public class ThreadSleep {
	
	// 单元测试里面 结束休眠 输出不出来
	@Test
	public void test() {
		new Thread() {
			public void run() {
				try {
					System.out.println("之前");
					Thread.sleep(2000);
					System.out.println("结束休眠");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		System.out.println("end");
	}
}
