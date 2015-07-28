package com.team.gaoguangjin.shiro;

import java.io.Serializable;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.session.Session;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName:SessionTest.java
 * @Description:Shiro提供的会话可以用于JavaSE/JavaEE环境，不依赖于任何底层容器，可以独立使用，是完整的会话模块  
 * @author gaoguangjin
 * @Date 2015-7-28 下午4:16:55
 */
@Slf4j
public class SessionTest extends BaseTest {
	@Test
	public void sessionTest() {
		login("classpath:shiro/shiro-permission.ini", "gao", "123");
		Session session = subject().getSession();
		
		String host = session.getHost();
		Serializable id = session.getId();
		// 设置当前Session的过期时间
		session.setTimeout(1000);
		
		// 获取会话的启动时间及最后访问时间
		Date startTime = session.getStartTimestamp();
		Date accessTime = session.getLastAccessTime();
		
		// 设置/获取/删除会话属性；在整个会话范围内都可以对这些属性进行操作
		session.setAttribute("key", "123");
		Assert.assertEquals("123", session.getAttribute("key"));
		session.removeAttribute("key");
		
		// 更新会话最后访问时间及销毁会话；当Subject.logout()时会自动调用stop方法来销毁会话
		session.touch();
		session.stop();
		
		log.info("Shiro的session :host=" + host + "||" + "id:" + id);
		log.info("Shiro的session :startTime=" + DateFormatUtils.format(startTime, "yyyy-MM-dd hh:mm:ss") + "||" + "accessTime:" + accessTime);
		
	}
	
}
