package com.team.gaoguangjin.shiro.realproject.test;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class TestProject {
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void test() {
		login("classpath:shiro/realproject/shiro.ini", "ggj", "zjy");
		subject().checkRole("student");
		// subject().checkPermission("student:find");
		// subject().checkPermission("teacher:insert");
		// boolean flag = subject().isPermittedAll("student:find");
		// log.info("是否存在此权限：" + flag);
	}
	
	protected void login(String configFile, String username, String password) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
	}
	
	public Subject subject() {
		return SecurityUtils.getSubject();
	}
	
	@After
	public void after() {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}
}
