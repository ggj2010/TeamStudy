package com.team.gaoguangjin.javaweb.cookie;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:LoginFilter.java
 * @Description: 学习cookie与session相关 需要把浏览器设置为退出不清楚cookie
 * @author gaoguangjin
 * @Date 2015-7-17 上午10:46:15
 */
@Slf4j
public class LoginFilter implements Filter {
	public static final String SUCCESS_LOGIN = "loginSuccess";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter开启");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// log.info("访问地址getRequestURI：" + req.getRequestURI());
		// log.info("项目运行地址getRealPath：" + req.getRealPath("/"));
		HttpSession session = req.getSession();
		String sessionId = req.getSession().getId();
		
		// log.info("session=" + session);
		// log.info("sessionId=" + sessionId);
		
		// 检查放行
		if (req.getRequestURI().contains(".jsp") || req.getRequestURI().contains(".css") || req.getRequestURI().contains("login.do")
				|| checkIslogin(req)) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("gaoguangjin/cookie/cookieLogin.jsp");
		}
	}
	
	/**
	 * @Description:  每一个浏览器cleint访问，服务器端都会生成一个独立的HttpSession
	 * @param req
	 * @return:boolean
	 */
	private boolean checkIslogin(HttpServletRequest req) {
		String name = null;
		String password = null;
		
		// 如果session里面存在信息，证明用户已经登陆过了，不需要在登陆了。
		if (null != req.getSession().getAttribute(SUCCESS_LOGIN))
			return true;
		
		// 从cookie里面获取客户端保存的用户名和密码，然后和数据库里面的数据比对
		Cookie[] cook = req.getCookies();
		if (null != cook) {
			for (Cookie cookie : cook) {
				if ("name".equals(cookie.getName()))
					name = cookie.getValue();
				if ("password".equals(cookie.getName()))
					password = cookie.getValue();
			}
		}
		
		if (name != null) {
			log.info("cookiel里面的用户名：" + name + "密码：" + password);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void destroy() {
		log.info("filter销毁");
	}
	
}
