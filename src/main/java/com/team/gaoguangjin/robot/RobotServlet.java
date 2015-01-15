package com.team.gaoguangjin.robot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Slf4j
public class RobotServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String content = request.getParameter("content");
			
			String APIKEY = "4b441cb500f431adc6cc0cb650b4a5d0";
			String INFO = URLEncoder.encode(content, "utf-8");
			String requesturl = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
			
			HttpGet req = new HttpGet(requesturl);
			HttpResponse res = HttpClients.createDefault().execute(req);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				String resultContent = EntityUtils.toString(res.getEntity());
				writer.write(resultContent);
			}
		}
		catch (Exception e) {
			log.error("获取机器人信息失败：" + e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
}
