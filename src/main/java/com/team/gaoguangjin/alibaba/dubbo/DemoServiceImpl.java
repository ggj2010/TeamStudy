package com.team.gaoguangjin.alibaba.dubbo;

public class DemoServiceImpl implements DemoService {
	
	public String sayHello(String name) {
		
		System.out.println("【返回值：】");
		return "Hello Dubbo,Hello " + name;
	}
	
}