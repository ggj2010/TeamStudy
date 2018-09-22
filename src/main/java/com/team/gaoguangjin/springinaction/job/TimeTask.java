package com.team.gaoguangjin.springinaction.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:TimeTask.java
 * @Description: jdk自带的timerTask测试,所有任务都是串行执行,前一个任务的延迟或异常都将会影响到之后的任务。
 * @see:可以在run里面调用线程去执行
 * @author gaoguangjin
 * @Date 2015-2-25 上午10:26:21
 */
@Slf4j
public class TimeTask extends TimerTask {
	public void run() {
		System.out.println("这是测试"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	public static void main(String[] args) throws InterruptedException {
		final Timer timer = new Timer();
		TimerTask task = new TimeTask();
		timer.schedule(task, 0L, 1000L);// 在一秒后执行此任务，每0.5秒执行一次
	}
}
