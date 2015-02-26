package com.team.gaoguangjin.springinaction.job;

import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class SimpleJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("执行定时任务");
		log.info(context.getTrigger().getName());
	}
}
