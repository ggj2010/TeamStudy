package com.team.gaoguangjin.设计模式.继承和接口;

/**
 * 
 * @author 高广金 前提： 有一个鸭子类，鸭子（某些）会飞，发出声音，展示，游泳。
 * 
 * 有继承父类方法的，有重写父类方法的,有接口的 因为某些鸭子会飞，某些鸭子不会飞，所有就不能有飞行的方法，所以得用到接口
 */
public abstract class Duck {
	
	public FlyBehavior flyBehavior;
	public QuackBehavior quackBehavior;
	
	public Duck() {
	};
	
	public abstract void display();
	
	// 发声
	public void peformQuack() {
		quackBehavior.peformQuack();
	}
	
	// 飞行
	public void peformFly() {
		flyBehavior.performFlay();
	}
	
	public void swim() {
		System.out.println("所有鸭子 都回游泳的噢");
	}
	
}
