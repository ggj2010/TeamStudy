package com.team.gaoguangjin.thread.style;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:NotUseThread.java
 * @Description:业务场景：上班时间
 * @author gaoguangjin
 * @Date 2015-3-19 下午2:16:05
 */
@Slf4j
public class NotUseThread {
	public static void main(String[] args) throws InterruptedException {
		// 早上上班路上50分钟时间
		gotoWorkNormal();
	}
	
	private static void gotoWorkNormal() throws InterruptedException {
		long beginTime = System.currentTimeMillis();
		// 1、坐车30
		takeTheSubWay();
		// 2、吃饭10
		eatBreakFast();
		// 3、看新闻10
		readNews();
		long endTime = System.currentTimeMillis();
		log.info("总耗费时间：" + (endTime - beginTime) / 100 + "分钟");
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
	}
}
