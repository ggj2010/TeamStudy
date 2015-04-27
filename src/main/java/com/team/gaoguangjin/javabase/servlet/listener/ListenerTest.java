package com.team.gaoguangjin.javabase.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ListenerTest.java
 * @Description: listener
 * @author gaoguangjin
 * @Date 2015-4-13 下午4:53:27
 */
@Slf4j
public class ListenerTest implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取context-param的值需要先获取到其getServletContext，然后再获取getInitParameter
		String robotServlet = (String) sce.getServletContext().getInitParameter("webAppRootKey");
		log.info("【1web.xml启动】这是context-param的初始化:RobotServlet==" + robotServlet);
		log.info("【2web.xml启动】这是listener的初始化");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
