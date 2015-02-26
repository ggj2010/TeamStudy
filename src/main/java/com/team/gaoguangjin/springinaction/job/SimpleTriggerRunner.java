package com.team.gaoguangjin.springinaction.job;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @ClassName:SimpleTriggerRunner.java
 * @Description: 调用测试的定时任务
 * @author gaoguangjin
 * @Date 2015-2-25 上午9:40:31
 */
@Slf4j
public class SimpleTriggerRunner {
	public static void main(String[] args) {
		demo1();
	}
	
	/**
	 * @Description:将jobdetail和trigger注册到Scheduler中，用trigger对jobdetail中的任务进行调度
	 * 当scheduler启动时候，trigger就会触发SimpleJob的execute方法
	 */
	private static void demo1() {
		try {
			JobDetail jobDetail = new JobDetail("测试定时任务", "jdgroup1", SimpleJob.class);
			
			SimpleTrigger trigger = new SimpleTrigger("trigger_1", "stgroup1");
			// 马上执行、间隔2秒、循环10次
			trigger.setStartTime(new Date());
			trigger.setRepeatInterval(2000);
			trigger.setRepeatCount(10);
			
			// 获取调度器实例
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
			
		}
		catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
}
