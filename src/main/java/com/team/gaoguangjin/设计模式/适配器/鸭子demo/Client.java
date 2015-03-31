package com.team.gaoguangjin.设计模式.适配器.鸭子demo;

public class Client {
	public static void main(String[] args) {
		TurkeyAdapter tap = new TurkeyAdapter(new BlueTurkey());
		
		tap.quack();
		
		System.out.println("因为火鸡飞的比较短，多飞几次");
		
		for (int i = 0; i < 3; i++) {
			tap.fly();
		}
		
	}
}
