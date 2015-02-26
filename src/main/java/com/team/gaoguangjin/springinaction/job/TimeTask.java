package com.team.gaoguangjin.springinaction.job;

import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:TimeTask.java
 * @Description: jdk自带的timerTask测试
 * @author gaoguangjin
 * @Date 2015-2-25 上午10:26:21
 */
@Slf4j
public class TimeTask extends TimerTask {
	
	public void run() {
		log.info("这是测试");
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new TimeTask();
		timer.schedule(task, 1000L, 5000L);
	}
	
}
