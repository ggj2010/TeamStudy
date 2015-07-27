package com.team.gaoguangjin.shiro.realproject.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName:UserRealm.java
 * @Description:   如果Realm是AuthenticatingRealm子类，则提供给AuthenticatingRealm内部使用的CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）； 
 * @author gaoguangjin
 * @Date 2015-7-27 下午2:23:11
 */
public class UserRealm extends AuthorizingRealm {
	
	// [2]授权(Authorization)
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 认证成功之后就进行授权
		String username = (String) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		
		/********常规的这一步是从根据用户名 从数据库查询出来对应的角色和权限 然后放到simpleAuthorizeation里面*******/
		// 自定义两个角色student teacher
		Set<String> roleSet = new HashSet<String>();
		roleSet.add("student");
		roleSet.add("teacher");
		sai.setRoles(roleSet);
		
		Set<String> permissionsSet = new HashSet<String>();
		permissionsSet.add("student:find");
		permissionsSet.add("student:update");
		permissionsSet.add("student:delete");
		//
		permissionsSet.add("teacher:insert");
		permissionsSet.add("teacher:update");
		permissionsSet.add("teacher:delete");
		
		sai.setStringPermissions(permissionsSet);
		
		return sai;
	}
	
	// [1]认证(authentication) 我们认证时候获取的密码应该是加密之后的密码。
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 用户名
		String username = (String) token.getPrincipal();
		// 我们传过来的密码是明文，然后 利用SimpleAuthenticationInfo自带的Credentials，将数据库里面存放的加密后的密码，转成明文比较
		// String password = new String((char[]) token.getCredentials());
		
		// 盐等于用户名+随机数
		// String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		String salt = "001a99ded337f6d23c43f16fdf0c13c2";
		
		// 加密之后的密码
		// String enyCrptPassword = new SimpleHash("md5", "zjy", ByteSource.Util.bytes(salt), 2).toHex();
		String enyCrptPassword = "96d2a72821d53837921140ef704a805c";
		// 有时候传入的是加密的密码，需要解密，然后解密可能需要盐（用户名+随机数）
		SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(username, enyCrptPassword, ByteSource.Util.bytes(salt), getName());
		return sa;
	}
	
}
