package com.team.gaoguangjin.设计模式.继承和接口;

/**
 * @ClassName:ModelDuck.java
 * @Description: 模型鸭
 * @author gaoguangjin
 * @Date 2015-3-27 下午2:44:01
 */
public class ModelDuck extends Duck {
	
	public ModelDuck() {
		flyBehavior = new FlyWihtRocket();
		quackBehavior = new QuackWithHaHa();
	}
	
	public void display() {
		System.out.println("这个是模型鸭");
	}
}
