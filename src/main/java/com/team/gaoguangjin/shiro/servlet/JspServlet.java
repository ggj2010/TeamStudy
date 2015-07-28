package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:JspServlet.java
 * @Description:  学习shiro标签  
 * @author gaoguangjin
 * @Date 2015-7-28 下午3:15:44
 */
@Slf4j
@WebServlet(name = "jspServlet", urlPatterns = "/jsp")
public class JspServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/gaoguangjin/shiro/shirojsp.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
