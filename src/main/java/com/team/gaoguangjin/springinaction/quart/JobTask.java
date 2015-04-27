package com.team.gaoguangjin.springinaction.quart;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTask {
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void run() {
		System.out.println(123123);
	}
}
