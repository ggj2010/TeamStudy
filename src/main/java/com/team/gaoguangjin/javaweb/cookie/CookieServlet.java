package com.team.gaoguangjin.javaweb.cookie;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String passwod = req.getParameter("password");
		req.getSession().setAttribute(LoginFilter.SUCCESS_LOGIN, true);
		
		Cookie cookie1 = new Cookie("name", name);
		Cookie cookie2 = new Cookie("password", passwod);
		
		// 一小时 cookie失效时间，如果不设置，那么关闭浏览器就没有了
		cookie1.setMaxAge(60 * 60);
		cookie2.setMaxAge(60 * 60);
		
		// 在webapp文件夹下的所有应用共享cookie,例如现在访问地址http://localhost:8088/TeamStudy/cookie
		// 那么默认的就是在这个目录下TeamStudy/cookie。其他目录就访问不了
		cookie1.setPath("/");
		cookie2.setPath("/");
		
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		resp.setContentType("text/html;charset=UTF-8");
		// resp.getWriter().write("登陆成功");
	}
	
	@Override
	public void destroy() {
		log.info("servlet destroy");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	}
	
}
