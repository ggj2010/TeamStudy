package com.team.gaoguangjin.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * @ClassName:SecondRealmDemo.java
 * @Description:自定义Realm实现,Shiro从从Realm获取安全数据（如用户、角色、权限）,可以把Realm看成DataSource，即安全数据源
 * @author gaoguangjin
 * @Date 2015-7-16 下午6:53:37
 */
public class SecondRealmDemo implements Realm {
	
	@Override
	public String getName() {
		return "myrealm1";
	}
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 用户名
		String username = (String) token.getPrincipal();
		// 密码
		String password = new String((char[]) token.getCredentials());
		
		if (!"ggj".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"qazqaz".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}
	
}
