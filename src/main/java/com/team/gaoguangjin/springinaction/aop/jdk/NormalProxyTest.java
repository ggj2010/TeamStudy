package com.team.gaoguangjin.springinaction.aop.jdk;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class NormalProxyTest {
	@Test
	public void test() {
		PlayGameInterface pg = new StartPlayGame();
		PlayGameProxy pgProxy = new PlayGameProxy(pg);
		// Proxy.newProxyInstance()最主要是这个方法
		PlayGameInterface PlayGaemProxy = (PlayGameInterface) Proxy.newProxyInstance(pg.getClass().getClassLoader(), pg.getClass().getInterfaces(),
				pgProxy);
		PlayGaemProxy.palyGame();
		PlayGaemProxy.palyGame2();
	}
}
