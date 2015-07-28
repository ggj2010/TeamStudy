package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:AuthenticatedServlet.java
 * @Description:  用来区别用户是否登陆，登陆后就能直接访问，没有登陆那就直接跳转到登陆页面，只需要在配置文件里面配置下 
 * @author gaoguangjin
 * @Date 2015-7-28 上午11:21:19
 */
@WebServlet(name = "authenticatedServlet", urlPatterns = "/authenticated")
public class AuthenticatedServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.getWriter().write("登陆验证成功才会显示这些文字！");
	}
}
