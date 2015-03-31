package com.team.gaoguangjin.设计模式.命令模式;

public class SimpleRemoteControl {
	public static void main(String[] args) {
		Command cd = new LightOnCommand(new Light());
		cd.execute();
	}
}
