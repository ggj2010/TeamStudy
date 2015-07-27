package com.team.gaoguangjin.javaweb.cookie;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write("get方法验证cookie login成功");
	}
	
	@Override
	public void destroy() {
		log.info("servlet destroy");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	}
	
}
