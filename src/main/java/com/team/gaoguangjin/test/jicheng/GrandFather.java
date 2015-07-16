package com.team.gaoguangjin.test.jicheng;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GrandFather {
	public GrandFather(String name) {
		// log.info("构造方法-祖父");
	}
	
	public GrandFather() {
		// log.info("构造方法-无参数-祖父");
	}
	
	public void method1() {
		log.info("i am 祖父");
	}
	
	public abstract void test();
}
