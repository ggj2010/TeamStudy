package com.team.gaoguangjin.jdbc.transaction.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:TransactionProxy.java
 * @Description: 运用搭理类来代理dao层的事物和异常捕获
 * @author gaoguangjin
 * @Date 2015-3-6 上午9:35:00
 */
@Slf4j
public class TransactionProxy implements InvocationHandler {
	TransactionInterface target;
	Connection connection;
	TransactionDao dao = null;
	
	// 因为我只需要代理TransactionDao里面的某些方法，所以我穿两个对象过来
	public TransactionProxy(TransactionInterface target, TransactionDao dao) {
		this.target = target;
		this.dao = dao;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("【1】启用代理事物开始");
		connection = dao.getConnection();
		Object object = null;
		try {
			// 设置不自动提交
			connection.setAutoCommit(false);
			object = method.invoke(target, args);
			connection.setAutoCommit(true);
		} catch (Exception e) {
			connection.rollback();
			log.info("数据库处理异常，进行【回滚】操作！");
			return null;
		}
		finally {
			connection.close();
			log.info("finally关闭数据库连接！");
			log.info("【2】启用代理事物结束");
			return object;
		}
	}
}
