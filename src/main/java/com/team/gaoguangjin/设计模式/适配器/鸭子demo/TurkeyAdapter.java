package com.team.gaoguangjin.设计模式.适配器.鸭子demo;

/**
 * 我们有鸭子接口和 火鸡接口 如果我们缺少鸭子对象，想让 火鸡对象去冒充
 * @author 高广金
 * 
 */
public class TurkeyAdapter implements Duck {
	
	Turkey turkey;
	
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	
	public void fly() {
		turkey.fly();
		
	}
	
	public void quack() {
		turkey.gobble();
		
	}
	
}
