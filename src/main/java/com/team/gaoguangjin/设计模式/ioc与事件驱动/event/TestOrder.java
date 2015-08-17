package com.team.gaoguangjin.设计模式.ioc与事件驱动.event;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class TestOrder {
	// 事件模型的代码和依赖注入代码的区别是：原来A直接依赖B，现在我们更改为A依赖事件总线对象，注意，这个事件总线其实类似Ioc容器是全局的。换句话说，A对B的直接依赖加入了第三者：
	// 原来:
	// A --> B
	// A ----> 第三方 ----> B
	@Test
	public void Test() {
		Order order = new Order();
		EventBus bus = new EventBus("test");
		bus.register(order);
		new User(bus).diplsy();
	}
}
