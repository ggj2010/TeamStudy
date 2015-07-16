package com.team.gaoguangjin.test.jicheng;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent extends GrandFather {
	public Parent(String name) {
		super(name);
		log.info("构造方法-父亲" + name);
	}
	
	public Parent() {
		// log.info("构造方法-无参数-父亲");
	}
	
	public void method1() {
		log.info("i am 父亲");
	}
	
	@Override
	public void test() {
		log.info("抽象方法 父亲");
	}
	
}
