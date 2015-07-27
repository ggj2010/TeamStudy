package com.team.gaoguangjin.shiro;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName:FirstLoginDemo.java
 * @Description:    
 * @author gaoguangjin
 * @Date 2015-7-16 下午5:46:00
 */
@Slf4j
public class FirstLoginDemo {
	
	/**
	 * @Description:  
	 * @see:new IniSecurityManagerFactory()构造器有两种参数 一个是文件的路径，一个Ini对象\
	 * @see:路径其实也是解析成Ini形式.
	 * @see:在指定路径下面创建这个文件classpath:shiro/shiro.ini
	 * @return:void
	 */
	@Test
	public void login() {
		Subject subject = null;
		try {
			// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
			/***factory构造方法参数是Ini对象**/
			Ini ini = new Ini();
			ini.addSection("users");
			ini.getSection("users").put("ggj", "qazqaz");
			Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
			
			/***factory构造方法参数是文件路径**/
			// Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
			
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			// 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证） 自动绑定到当前线程
			subject = SecurityUtils.getSubject();
			
			// 中文验证不成功,,,,,
			// UsernamePasswordToken token = new UsernamePasswordToken("高广金", "qazqaz");
			UsernamePasswordToken token = new UsernamePasswordToken("ggj", "qazqaz");
			subject.login(token);
			
			// 断言用户已经登录
			Assert.assertEquals(true, subject.isAuthenticated());
			log.info("认证成功！");
		} catch (Exception e) {
			log.error("认证错误！" + e.getLocalizedMessage());
		}
		finally {
			// 6、退出
			subject.logout();
		}
	}
}
