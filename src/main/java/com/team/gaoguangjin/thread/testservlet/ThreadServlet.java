package com.team.gaoguangjin.thread.testservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThreadServlet extends HttpServlet {
	// 使用成员变量
	private volatile String username;
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, RuntimeException {
		System.out.println("=================【BingFa doGet】===============");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		username = request.getParameter("name");
		System.out.println(username);
		PrintWriter out = response.getWriter();
		try {
			// 得到当前线程的名字
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			Thread.sleep(10000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write("这是返回的值：【" + username + "】");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, RuntimeException {
		doGet(request, response);
	}
	
}
