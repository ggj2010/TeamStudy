package com.team.gaoguangjin.springinaction.job.quartddl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.team.util.DateUtil;

/**
 * @ClassName:QuartzJob.java
 * @Description:    
 * @author gaoguangjin
 * @Date 2015-12-7 下午3:01:21
 */
public class QuartzJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss") + "11111111111");
	}
}
