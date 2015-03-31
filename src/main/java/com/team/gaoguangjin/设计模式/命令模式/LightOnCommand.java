package com.team.gaoguangjin.设计模式.命令模式;

public class LightOnCommand implements Command {
	Light light;
	
	public LightOnCommand(Light light) {
		this.light = light;
	}
	
	public void execute() {
		light.on();
	}
	
}
