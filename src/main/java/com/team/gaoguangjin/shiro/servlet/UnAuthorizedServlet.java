package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:UnAuthorizedServlet.java
 * @Description: 没有授权   
 * @author gaoguangjin
 * @Date 2015-7-28 上午11:44:05
 */
@WebServlet(name = "unauthorizedServlet", urlPatterns = "/unauthorized")
public class UnAuthorizedServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("gaoguangjin/shiro/unauthorized.jsp").forward(req, resp);
	}
	
}
