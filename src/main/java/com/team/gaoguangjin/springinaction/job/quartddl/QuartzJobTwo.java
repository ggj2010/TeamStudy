package com.team.gaoguangjin.springinaction.job.quartddl;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
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
@Slf4j
public class QuartzJobTwo implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info((DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss") + "====22222222222"));
		String fileName = jobExecutionContext.getJobDetail().getJobDataMap().getString("fileName");
		if (!StringUtils.isEmpty(fileName)) {
			log.info("定时任务参数：" + fileName);
		}
		
		log.info("执行业务代码");
	}
}
