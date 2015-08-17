package com.team.gaoguangjin.设计模式.ioc与事件驱动.event;

import com.google.common.eventbus.EventBus;

public class User {
	EventBus event;
	
	public User(EventBus event) {
		this.event = event;
	}
	
	public void diplsy() {
		event.post("利用");
	}
}
