package com.team.gaoguangjin.jdbc.transaction.cglib;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @ClassName:TestDaoProxy.java
 * @Description: 测试利用动态代理，代理jdbc常用的事物、连接等等
 * @author gaoguangjin
 * @Date 2015-3-6 上午9:57:51
 */
@Slf4j
public class TestDaoProxy {
	@Test
	public void test() throws SQLException {
		
		TransactionDao dao = new TransactionDao();
		TransactionInterface target = dao;
		TransactionProxy tproxy = new TransactionProxy(target, dao);
		TransactionInterface proxyTarget = (TransactionInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass()
				.getInterfaces(), tproxy);
		// 测试成功
		// proxyTarget.insert();
		System.out.println();
		// 测试失败的
		proxyTarget.insertErrow();
		
	}
}
