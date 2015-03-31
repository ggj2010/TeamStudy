package com.team.gaoguangjin.设计模式.继承和接口;

public class GreenDuck extends Duck {
	
	public GreenDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new QuackWithGuaGua();
	}
	
	public void display() {
		System.out.println("这个是绿头鸭");
	}
}
