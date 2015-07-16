package com.team.gaoguangjin.test.jicheng;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:Child.java
 * @Description:子类继承父类的时候，默认调用不带参数的构造方法，如果有带参数的构造方法，那就得调带参数的构造方法
 * @author gaoguangjin
 * @Date 2015-6-8 上午11:53:01
 */
@Slf4j
public class Child extends Parent {
	public Child() {
		// log.info("构造方法-无参数-子类");
	}
	
	public Child(String name) {
		
	}
	
	public void method1() {
		log.info("i am child");
	}
}
