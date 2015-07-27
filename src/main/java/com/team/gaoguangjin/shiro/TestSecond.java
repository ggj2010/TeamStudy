package com.team.gaoguangjin.shiro;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @ClassName:TestSecond.java
 * @Description:    数据源是SecondRealmDemo ,调用测试之后，会调用getAuthenticationInfo()方法
 * @author gaoguangjin
 * @Date 2015-7-16 下午11:32:51
 */
@Slf4j
public class TestSecond {
	@Test
	public void test() {
		Subject subject = null;
		try {
			Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm.ini");
			
			SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken("ggj", "qazqaz");
			subject.login(token);
			
			log.info("登陆成功：" + subject.isAuthenticated());
		} catch (Exception e) {
			log.error("认证失败：" + e.getMessage());
		}
		finally {
			subject.logout();
		}
	}
}
