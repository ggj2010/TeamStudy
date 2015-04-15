package com.team.hadoop.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.team.hadoop.util.GetClassContent;

/**
 * @ClassName:HadoopServlet.java
 * @Description: hadoop所有操作就只用这一个servlet去控制
 * @author gaoguangjin
 * @Date 2015-3-13 上午10:16:22
 */
@Slf4j
public class HadoopServlet extends HttpServlet {
	/**
	 * doget方法
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(GetClassContent.getClassContent(req.getParameter("className")));
		} catch (Exception e) {
			log.error(req.getParameter("className") + "hadoop类加载失败" + e.getLocalizedMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		log.info("【2】service方法调用，判断是get方法还是post方法");
		super.service(req, res);
	}
	
	@Override
	public void init() throws ServletException {
		// listener filter 获取初始化参数 和servlet获取 init-param参数不一样
		String hadoop = this.getInitParameter("hadoop");
		log.info("【3web.xml启动】servlet的获取init-param的值：hadoop=," + hadoop);
		log.info("【4web.xml启动】servlet的init方法调用,");
		super.init();
	}
	
}
