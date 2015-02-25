package com.team.gaoguangjin.深入java虚拟机.类解析;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent {
	static {
		log.info("我是父类的static代码块");
	}
	{
		log.info("我是父类的构造函数代码块");
	}
}
