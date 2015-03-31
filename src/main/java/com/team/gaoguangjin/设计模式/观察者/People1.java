package com.team.gaoguangjin.设计模式.观察者;

public class People1 implements PeopleInterface {
	public String name;
	
	People1(String name) {
		this.name = name;
	}
	
	@Override
	public void notice(String message) {
		System.out.println(name + "被通知   :" + message);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}
