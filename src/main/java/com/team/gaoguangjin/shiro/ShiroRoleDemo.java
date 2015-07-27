package com.team.gaoguangjin.shiro;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName:ShiroRoleDemo.java
 * @Description: 测试shiro 授权   
 * @author gaoguangjin
 * @Date 2015-7-17 下午4:50:28
 */
@Slf4j
public class ShiroRoleDemo {
	
	/**
	 * @Description: role 
	 * @return:void
	 * 基于角色的访问控制（即隐式角色）就完成了，这种方式的缺点就是如果很多地方进行了角色判断，
	 * 但是有一天不需要了那么就需要修改相应代码把所有相关的地方进行删除；这就是粗粒度造成的问题
	 */
	@Test
	public void testRole() {
		/* 这样写会导致 subject 和subject2 的Principals 一样！ 需要一个一个判断 */
		// Subject subject = login("classpath:shiro/shiro-role.ini", "zhang", "123");
		// Subject subject2 = login("classpath:shiro/shiro-role.ini", "wang", "123");
		
		// 判断单个权限
		Subject subject = login("classpath:shiro/shiro-role.ini", "zhang", "123");
		boolean flag1 = subject.hasRole("role1");
		
		Assert.assertTrue(flag1);
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		
		// 判断多个权限
		boolean[] flags = subject.hasRoles(Arrays.asList("role1", "role2"));
		for (boolean f : flags) {
			Assert.assertTrue(f);
		}
		
		Subject subject2 = login("classpath:shiro/shiro-role.ini", "wang", "123");
		boolean flag3 = subject2.hasRole("role2");
		Assert.assertTrue(flag3);
	}
	
	public Subject login(String filePath, String username, String password) {
		Subject subject = null;
		try {
			/***factory构造方法参数是文件路径**/
			Factory<SecurityManager> factory = new IniSecurityManagerFactory(filePath);
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			// 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证） 自动绑定到当前线程
			subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (Exception e) {
			log.error("执行错误" + e.getLocalizedMessage());
		}
		return subject;
	}
	
}
