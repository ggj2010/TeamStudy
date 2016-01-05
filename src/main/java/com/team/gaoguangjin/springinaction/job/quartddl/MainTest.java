package com.team.gaoguangjin.springinaction.job.quartddl;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:MainTest.java
 * @Description:    测试
 * @author gaoguangjin
 * @Date 2015-12-7 下午3:01:21
 */
@Slf4j
public class MainTest {
	/**
	 * jobName需要唯一
	 * */
	public static void main(String[] args) {
		try {
			// testNormal();
			testParam();
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	public void testNormal() throws InterruptedException {
		String job_name = "动态任务调度";
		String job_name_two = "动态任务调度2";
		log.info("【系统启动】开始(每1秒输出一次)...");
		QuartzManager.addJob(job_name, QuartzJob.class, "0/1 * * * * ?");
		// QuartzManager.addJob(job_name_two, QuartzJobTwo.class, "0/1 * * * * ?");
		
		Thread.sleep(5000);
		log.info("【修改时间】开始(每2秒输出一次)...");
		QuartzManager.modifyJobTime(job_name, "10/2 * * * * ?");
		Thread.sleep(6000);
		log.info("【移除定时】开始...");
		QuartzManager.removeJob(job_name);
		log.info("【移除定时】成功");
		
		// log.info("【再次添加定时任务】开始(每10秒输出一次)...");
		// QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?");
		// Thread.sleep(60000);
		// log.info("【移除定时】开始...");
		// QuartzManager.removeJob(job_name);
		// log.info("【移除定时】成功");
	}
	
	public static void testParam() {
		String job_name = "动态任务调度";
		String job_name_two = "动态任务调度2";
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("fileName", "文件1名称");
			}
		};
		QuartzManager.addJobWithParam(job_name, QuartzJobTwo.class, "0/5 * * * * ?", map);
	}
}
