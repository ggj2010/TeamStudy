package com.team.gaoguangjin.设计模式.适配器.鸭子demo;

public class BlueTurkey implements Turkey {
	public void fly() {
		System.out.println("飞行5米");
		
	}
	
	public void gobble() {
		System.out.println("发声音：咯咯");
	}
	
}
