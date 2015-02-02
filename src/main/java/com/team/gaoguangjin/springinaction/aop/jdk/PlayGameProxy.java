package com.team.gaoguangjin.springinaction.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class PlayGameProxy implements InvocationHandler {
	static Logger logger = Logger.getLogger(PlayGameProxy.class);
	Object traget;
	
	public PlayGameProxy(Object playGameInterface) {
		this.traget = playGameInterface;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		logger.info("打开电脑噢");
		Object object = method.invoke(traget, args);
		logger.info("关闭电脑");
		return object;
	}
	
}
