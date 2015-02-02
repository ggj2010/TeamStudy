package com.team.gaoguangjin.springinaction.aop.cglib;

import org.junit.Test;

import com.team.gaoguangjin.springinaction.aop.jdk.StartPlayGame;

public class CglibTest {
	
	@Test
	public void test() {
		CglibProxy cglibProxy = new CglibProxy();
		
		StartPlayGame startPlayGame = (StartPlayGame) cglibProxy.getProxy(StartPlayGame.class);
		startPlayGame.palyGame();
		
	}
}
