package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

/**
 * @ClassName:LogoutServlet.java
 * @Description:  退出  
 * @author gaoguangjin
 * @Date 2015-7-28 下午2:52:26
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SecurityUtils.getSubject().logout();
		req.getRequestDispatcher("gaoguangjin/shiro/logoutSuccess.jsp").forward(req, resp);
	}
	
}
