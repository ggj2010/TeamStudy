package com.team.gaoguangjin.springinaction.study.controller;

import java.beans.PropertyEditorSupport;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.gaoguangjin.springinaction.study.entity.JavaType;

public class MainController {
	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public String get() {
		System.out.println("loginget");
		return "/login";
	}
	
	/*
	 * 这个方法只有在登陆失败时候才会调用
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public String fail(Model model) {
		System.out.println("验证失败loginPOST");
		model.addAttribute("message", "密码或者用户名不对");
		return "/login";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "success", method = { RequestMethod.GET })
	public String success() {
		try {
			System.out.println("success");
			SecurityUtils.getSubject().checkPermission("user");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return "/index";
	}
	
	@RequiresPermissions(value = "bb:test")
	@RequestMapping(value = "test", method = { RequestMethod.GET })
	public String test() {
		SecurityUtils.getSubject().checkPermission("user");
		System.out.println("test");
		return "/test";
	}
	
	/* 没有权利跳转到的页面 */
	@RequestMapping(value = "unauthorizedUrl", method = { RequestMethod.GET })
	public String unauthorizedUrl() {
		System.out.println("unauthorizedUrl");
		return "/unauthorizedException";
	}
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	public String authenticationException() {
		System.out.println("走注解式异常authenticationException");
		return "/unauthorizedException";
	}
	
	@ExceptionHandler({ NumberFormatException.class })
	public String numberFormatException() {
		System.out.println("走注解式异常");
		return "/exception";
	}
	
	@RequestMapping(value = "exception", method = { RequestMethod.GET })
	public String exception() {
		System.out.println("exception");
		Integer.parseInt("a");
		return "/test";
	}
	
	@RequestMapping(value = "initbinder", method = { RequestMethod.GET })
	public String initBinderparam(String name) {
		System.out.println(name);
		return "/test";
	}
	
	@ResponseBody
	@RequestMapping(value = "json", method = { RequestMethod.GET })
	public Object json(JavaType type) {
		System.out.println(type.getName() + ":" + type.getValue());
		type.setName("返回json数据");
		return type;
	}
	
	/**
	 * 传入restful风格的参数
	 * http://localhost:8088/generateCode-platform/msok/web/restful  
	 * 然后再header 里面加上参数 {"name":"返回json数据","value":"1这是initbinder"}
	 */
	@ResponseBody
	@RequestMapping(value = "restful", method = { RequestMethod.POST })
	public Object restful(@RequestBody JavaType type) {
		System.out.println(type.getName() + ":" + type.getValue());
		type.setName("restful返回");
		return type;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : text.trim() + "这是initbinder");
			}
			
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
	}
}
