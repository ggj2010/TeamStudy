package com.team.gaoguangjin.springinaction.quart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
//Scheduled 并行
//@Scope("prototype")
public class JobTask {
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void run() {
		System.out.println("run"+new Date());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Scheduled(cron = "0/5 * * * * ?" )
	public void run2() {
		System.out.println("run2"+new Date());
		while (true){
			//用来证明串行的，必须等待当前定时任务执行完之后再继续执行;

			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
