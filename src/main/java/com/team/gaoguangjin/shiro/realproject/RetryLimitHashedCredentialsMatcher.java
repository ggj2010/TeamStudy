package com.team.gaoguangjin.shiro.realproject;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName:RetryLimitHashedCredentialsMatcher.java
 * @Description:    调用HashedCredentialsMatcher去验证密码，同时增加密码验证次数限制
 * @author gaoguangjin
 * @Date 2015-7-27 下午5:28:29
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	Ehcache passwordRetryCache;
	
	public RetryLimitHashedCredentialsMatcher() throws CacheException, IOException {
		CacheManager cacheManager = CacheManager.newInstance(new ClassPathResource("shiro/ehcache.xml").getInputStream());
		System.out.println(new ClassPathResource("shiro/ehcache.xml").getFile().exists());
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		System.out.println(passwordRetryCache);
	}
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		Element element = passwordRetryCache.get(username);
		if (element == null) {
			element = new Element(username, new AtomicInteger(0));
			passwordRetryCache.put(element);
		}
		AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}
		// 调用 HashedCredentialsMatcher的doCredentialsMatch方法
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}
	
}
