package com.team.gaoguangjin.thread.style;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:UseThread.java
 * @Description: 业务场景。多线程上班
 * @author gaoguangjin
 * @Date 2015-3-19 下午5:59:04
 */
@Slf4j
public class UseThread extends Thread {
	static long beginTime = 0;
	static long endTime = 0;
	
	public static void main(String[] args) throws InterruptedException {
		beginTime = System.currentTimeMillis();
		// 早上上班路上50分钟时间
		new UseThread().start();
		gotoWorkNormal();
		log.info("利用坐车时间吃饭和看新闻");
	}
	
	public void run() {
		try {
			// 1、坐车30
			takeTheSubWay();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void gotoWorkNormal() throws InterruptedException {
		// 2、吃饭10
		eatBreakFast();
		// 3、看新闻10
		readNews();
	}
	
	private static void readNews() throws InterruptedException {
		Thread.sleep(1000);
		log.info("看新闻花费10分钟");
	}
	
	private static void eatBreakFast() throws InterruptedException {
		Thread.sleep(1000);
		log.info("吃饭花费10分钟");
	}
	
	private static void takeTheSubWay() throws InterruptedException {
		Thread.sleep(3000);
		log.info("坐车花费30分钟");
		endTime = System.currentTimeMillis();
		log.info("总耗费时间：" + (endTime - beginTime) / 100 + "分钟");
	}
}
