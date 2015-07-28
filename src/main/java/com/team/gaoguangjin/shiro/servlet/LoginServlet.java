package com.team.gaoguangjin.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @ClassName:LoginServlet.java
 * @Description:   测试shiro，jsp标签 。用 zjy和ggj分别登陆就可以看到效果
 * @author gaoguangjin
 * @Date 2015-7-17 下午4:44:04
 */
@Slf4j
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/gaoguangjin/shiro/shiroLogin.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jsp = "gaoguangjin/shiro/display.jsp";
		try {
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			subject.login(token);
			
			// 没有认证成功
			if (!subject.isAuthenticated())
				jsp = "gaoguangjin/shiro/shiroLogin.jsp";
		} catch (Exception e) {
			log.error("验证失败!" + e.getLocalizedMessage());
			jsp = "gaoguangjin/shiro/shiroLogin.jsp";
			req.setAttribute("message", e.getLocalizedMessage());
		}
		req.getRequestDispatcher(jsp).forward(req, resp);
	}
	
}
