package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @ClassName:RoleServlet.java
 * @Description:    
 * @author gaoguangjin
 * @Date 2015-7-28 下午12:22:42
 */
@WebServlet(name = "roleServlet", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.checkRole("admin");
		req.getRequestDispatcher("gaoguangjin/shiro/display.jsp").forward(req, resp);
	}
}
