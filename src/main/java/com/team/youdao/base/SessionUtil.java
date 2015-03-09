package com.team.youdao.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
	public Object getSeesionByName(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}
	
	public void setAttribute(HttpServletRequest request, String sessionHuman, String value) {
		request.getSession().setAttribute(sessionHuman, value);
	}
	
	public void removeAttribute(HttpServletRequest request, String loginToUrl) {
		request.getSession().removeAttribute(loginToUrl);
		
	}
}
