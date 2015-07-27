package com.team.gaoguangjin.shiro;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class PasswordTest extends BaseTest {
	@Test
	public void testGeneratePassword() {
		String algorithmName = "md5";
		String username = "liu";
		String password = "123";
		String salt1 = username;
		// 随机数
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		System.out.println("加密盐：" + salt2);
		int hashIterations = 2;
		
		// 生成密码散列值 盐（用户名+随机数）
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println("加密盐：" + salt2);
		System.out.println("加密之后密码:" + encodedPassword);
	}
	
	// 期望抛出的异常类型。
	@Test(expected = ExcessiveAttemptsException.class)
	public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:shiro/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
			} catch (Exception e) {/* ignore */
			}
		}
		login("classpath:shiro/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
	}
	
}
