package com.team.gaoguangjin.设计模式.ioc与事件驱动;

public class User {
	Order order;
	
	public User(Order order) {
		this.order = order;
	}
	
	public void diplsy() {
		order.getDetail();
	}
}
