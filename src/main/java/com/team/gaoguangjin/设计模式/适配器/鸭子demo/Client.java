package com.team.gaoguangjin.设计模式.适配器.鸭子demo;

/**
 * 对象适配器 ，它不是使用多继承或继承再实现的方式，而是使用直接关联，或者称为委托的方式，类图如下：
 */
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
