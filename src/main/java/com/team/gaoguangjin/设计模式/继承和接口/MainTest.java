package com.team.gaoguangjin.设计模式.继承和接口;

/**
 * @ClassName:MainTest.java
 * @Description: 测试主入口
 * @author gaoguangjin
 * @Date 2015-3-27 下午2:42:33
 */
public class MainTest {
	public static void main(String[] args) {
		Duck greenDuck = new GreenDuck();
		doMethod(greenDuck);
		// -----------------------------模型鸭子------------
		Duck modelDuck = new ModelDuck();
		doMethod(modelDuck);
	}
	
	private static void doMethod(Duck duck) {
		duck.flyBehavior.performFlay();
		duck.quackBehavior.peformQuack();
		duck.display();
		duck.swim();
		System.out.println("=============================");
	}
}
