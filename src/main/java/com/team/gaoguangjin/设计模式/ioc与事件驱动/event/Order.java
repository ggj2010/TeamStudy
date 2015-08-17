package com.team.gaoguangjin.设计模式.ioc与事件驱动.event;

import lombok.extern.slf4j.Slf4j;

import com.google.common.eventbus.Subscribe;

@Slf4j
public class Order {
	
	@Subscribe
	public void getDetail(String message) {
		log.info("购买了书籍20本！！！" + message);
	}
	
}
