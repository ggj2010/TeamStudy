package com.team.gaoguangjin.设计模式.继承和接口;

public class FlyWithWings implements FlyBehavior {
	
	public void performFlay() {
		System.out.println("用翅膀飞行噢");
		// Logger.getLogger(FlyWithWings.class).info("用翅膀飞行噢");
	}
	
}
