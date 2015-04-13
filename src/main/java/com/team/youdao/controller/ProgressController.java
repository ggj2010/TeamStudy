package com.team.youdao.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.team.util.Progress;

@Controller
// @SessionAttributes("status")
public class ProgressController {
	
	@RequestMapping(value = "/youdao/progress", method = RequestMethod.POST)
	@ResponseBody
	public String initCreateInfo(Map<String, Object> model, HttpServletRequest request) {
		Progress status = (Progress) request.getSession().getAttribute("status");
		
		// System.out.println(request.getSession());
		if (status == null)
			return null;
		
		// 读的和总大小一样，那就代表上传完了
		if (status.getPContentLength() == status.getPBytesRead() && status.getPContentLength() != 0) {
			status.setIsover(true);
		}
		
		System.out.println(JSONObject.toJSONString(status));
		return JSONObject.toJSONString(status);
		
	}
}
