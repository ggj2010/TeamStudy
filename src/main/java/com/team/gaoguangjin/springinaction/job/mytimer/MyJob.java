package com.team.gaoguangjin.springinaction.job.mytimer;

/**
 * @author:gaoguangjin
 * @date 2017/8/9 16:21
 */
public class MyJob extends MyTimerTask {
	
	@Override
	public void run() {
		System.out.println("----执行job----");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
