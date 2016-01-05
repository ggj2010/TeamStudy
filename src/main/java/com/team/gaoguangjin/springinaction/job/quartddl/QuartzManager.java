package com.team.gaoguangjin.springinaction.job.quartddl;

import java.util.Iterator;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @ClassName:MainJob.java
 * @Description:   定时任务入口 ,jobName必须要唯一
 * @author gaoguangjin
 * @Date 2015-12-7 上午10:27:08
 */
@Slf4j
public class QuartzManager {
	// 获取调度器实例
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	// 任务组名称
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	// 触发器名称
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
	
	/**
	 * @Description:添加一个定时任务，使用默认的任务组名，触发器名，触发器组名   
	 * @param jobName
	 * @param cls
	 * @param time     
	 * @return:void
	 */
	public static void addJob(String jobName, Class cls, String time) {
		try {
			// 获取scheduler实例
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			// 触发器时间设定
			trigger.setCronExpression(time);
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 ,参数  
	 * @param jobName
	 * @param cls
	 * @param time     
	 * @return:void
	 */
	public static void addJobWithParam(String jobName, Class cls, String time, Map<String, Object> map) {
		try {
			// 获取scheduler实例
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);// 任务名，任务组，任务执行类
			
			// 存放参数
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				jobDetail.getJobDataMap().put(key, map.get(key));
			}
			
			// 触发器
			CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			// 触发器时间设定
			trigger.setCronExpression(time);
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:  添加一个定时任务 
	 * @param jobName 任务名 
	 * @param jobGroupName 任务组名 
	 * @param triggerName 触发器名 
	 * @param triggerGroupName 触发器组名 
	 * @param jobClass 任务 
	 * @param time     cron时间
	 * @return:void
	 */
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, jobGroupName, jobClass);// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
			
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description:  修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @param time     
	 * @return:void
	 */
	public static void modifyJobTime(String jobName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(jobName, JOB_GROUP_NAME);
				Class objJobClass = jobDetail.getJobClass();
				removeJob(jobName);
				addJob(jobName, objJobClass, time);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description:  修改一个任务的触发时间 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time     
	 * @return:void
	 */
	public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				ct.setCronExpression(time);
				// 重启触发器
				sched.resumeTrigger(triggerName, triggerGroupName);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)  
	 * @param jobName     
	 * @return:void
	 */
	public static void removeJob(String jobName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
			sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
			sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description:移除一个任务   
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName     
	 * @return:void
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
			sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
			sched.deleteJob(jobName, jobGroupName);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description:  启动所有定时任务 
	 *      
	 * @return:void
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description: 关闭所有定时任务  
	 *      
	 * @return:void
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
