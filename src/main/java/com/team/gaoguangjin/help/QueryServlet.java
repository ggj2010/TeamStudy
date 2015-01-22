package com.team.gaoguangjin.help;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;

@Slf4j
public class QueryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			// 通过反射进行set值
			ResultSet rs = null;
			LuHuiEnty luhui = ReflectUtil.getParams(request, rs);
			List<LuHuiEnty> list = new LuHuiDAO().selectByParams(luhui);
			// 查询回来的数据转换成 jason
			writer.write(JSONObject.toJSON(list).toString());
			
		}
		catch (Exception e) {
			log.error("信息失败：" + e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
}
