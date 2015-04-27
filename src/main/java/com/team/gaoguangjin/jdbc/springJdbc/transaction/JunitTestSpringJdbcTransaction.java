package com.team.gaoguangjin.jdbc.springJdbc.transaction;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class JunitTestSpringJdbcTransaction {
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("jdbcconfig/springjdbc/springjdbc.xml");
		// 获取事务代理Bean
		TransactionDao transactionDao = (TransactionDao) ap.getBean("transactionDao", TransactionDao.class);
		System.out.println("===");
		transactionDao.findValule();
	}
	
}
