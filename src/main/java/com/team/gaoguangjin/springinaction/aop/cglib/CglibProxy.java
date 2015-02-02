package com.team.gaoguangjin.springinaction.aop.cglib;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 运用cglib进行代理
 * @author ggj 实现 MethodInterceptor类
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {
	
	Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);// 设置子类需要创建的类
		enhancer.setCallback(this);
		return enhancer.create();// 创建子类实例
	}
	
	public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
		
		log.info("【1】打开电脑噢");
		Object result = proxy.invokeSuper(obj, args);
		log.info("【3】关闭电脑");
		
		return result;
	}
	
}