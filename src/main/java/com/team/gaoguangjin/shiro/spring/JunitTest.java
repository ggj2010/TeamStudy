package com.team.gaoguangjin.shiro.spring;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @ClassName:JunitTest.java
 * @Description:    测试javase的spring-shiro。注意会话session管理器是DefaultSessionManager
 * @author gaoguangjin
 * @Date 2015-7-29 下午2:02:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:shiro/spring/spring-beans.xml", "classpath:shiro/spring/spring-shiro.xml" })
@TransactionConfiguration(defaultRollback = false)
@Slf4j
public class JunitTest {
	@Autowired
	private AnnationClass annationClass;
	
	// 权限设定是在UserRealm里面
	@Test
	public void test() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("ggj", "zjy");
		subject.login(token);
		log.info(annationClass.getName());
		log.info(annationClass.find());
		// 没有这个权限，所以会报错
		// log.info(annationClass.notExistsRole());
	}
	
}
