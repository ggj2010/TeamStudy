package com.team.gaoguangjin.设计模式.适配器;

public class ConcreteTarget implements Target {
	public void request() {
		System.out.println("普通类 具有 普通功能...");
	}
}
