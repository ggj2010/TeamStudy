package com.team.gaoguangjin.thread.testservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:ThreadServlet.java
 * @Description:servlet是否线程安全是由它的实现来决定的，如果它内部的属性或方法会被多个线程改变，它就是线程不安全的，反之，就是线程安全的
 * @see:servlet是多线程执行的
 * @author gaoguangjin
 * @Date 2015-4-16 上午11:53:37
 */
public class ThreadServlet extends HttpServlet {
	// 使用成员变量
	private volatile String username;
	
	private int count = 0;
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
		// thread(response);
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		sleep(username);//
		out.write("这是返回的值：【" + username + "】");
		out.flush();
		out.close();
	}
	
	private void thread(HttpServletResponse response) throws IOException {
		response.getWriter().println("<HTML><BODY>");
		response.getWriter().println(this + " ==> ");
		response.getWriter().println(Thread.currentThread() + ": <br>");
		for (int i = 0; i < 5; i++) {
			response.getWriter().println("count = " + count + "<BR>");
			try {
				Thread.sleep(1000);
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.getWriter().println("</BODY></HTML>");
		
	}
	
	// private synchronized void sleep(String username) {//加了synchronized就是单线程了，但是全局变量还是共享的
	private void sleep(String username) {
		long time = 0;
		
		if ("a".equals(username)) {
			time = 1000;
		} else {
			time = 15000;
		}
		try {
			// 得到当前线程的名字
			System.out.println(username + "】Thread Name: " + Thread.currentThread().getName() + ":开始");
			Thread.sleep(time);
			System.out.println(username + "】Thread Name: " + Thread.currentThread().getName() + ":结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
		doGet(request, response);
	}
	
}
