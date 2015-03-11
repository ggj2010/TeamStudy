package com.team.youdao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team.youdao.base.Constant;
import com.team.youdao.base.SessionUtil;
import com.team.youdao.bean.Human;
import com.team.youdao.service.ExceptionService;
import com.team.youdao.service.HumanService;

/**
 * @ClassName:LoginController.java
 * @Description: 登陆初始化一些东西
 * @author gaoguangjin
 * @Date 2015-2-28 下午2:16:38
 */
@Slf4j
@Controller
public class LoginController {
	@Autowired
	HumanService humanService;
	@Autowired
	ExceptionService exceptionService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value = "/youdao/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(HttpServletResponse response, Human human, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login");
		if (checkLoginInfo(human, request)) {
			Object object = sessionUtil.getSeesionByName(request, Constant.LOGIN_TO_URL);
			if (object != null) {
				sessionUtil.removeAttribute(request, Constant.LOGIN_TO_URL);
				try {
					request.getRequestDispatcher("/" + object.toString()).forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("跳转URL=" + object.toString());
			}
			mv.setViewName("/index");
		}
		log.info("登陆失败！");
		return mv;
	}
	
	@RequestMapping(value = "/youdao/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView logout(HttpServletResponse response, Human human, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login");
		sessionUtil.removeAttribute(request, Constant.FILTERED_REQUEST);
		sessionUtil.removeAttribute(request, Constant.SESSION_HUMAN);
		log.info("退出成功");
		return mv;
	}
	
	private boolean checkLoginInfo(Human human, HttpServletRequest request) {
		if (!StringUtils.isEmpty(human.getHumanName())) {
			log.info(human.getHumanName());
			sessionUtil.setAttribute(request, Constant.FILTERED_REQUEST, Constant.FILTERED_REQUEST);
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(value = "/yd", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/homepage/home");
		return mv;
	}
	
	@RequestMapping(value = "/youdao/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/register");
		return mv;
	}
	
	/**
	 * @Description: 注册保存
	 * @param human
	 * @return
	 * @throws Exception
	 * @return:ModelAndView
	 */
	@RequestMapping(value = "/youdao/register/save", method = { RequestMethod.POST })
	public ModelAndView registerSave(Human human) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			humanService.saveRegisterHuman(human);
			mv.setViewName("/login");
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
		return mv;
	}
	
	/**
	 * @Description: 异常捕获测试
	 * @return spring事物对运行时异常 不会捕获回滚 。对非运行时候异常会捕获进行回滚操作
	 * @see:spring一般给service配置事物，然后一个service调用不同的dao， 但是不允许dao层调用别的到层，防止跨事物的不回滚
	 * @return:ModelAndView
	 */
	@RequestMapping(value = "/youdao/exception")
	public ModelAndView testException() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("/exception/exception");
			
			// 方法命名是get*开头，事物是只读状态，所以无法执行更新和插入操作
			// exceptionService.getData();
			
			// 正常保存
			// exceptionService.saveData();
			
			// 以save命名的 事物不是readonly状态,但是有一个不能为空字段为空，看看事物是否回滚
			// exceptionService.saveDataWithNull();//测试回滚
			
			// 保存数据，带的是运行时异常，不需要捕获的，看看事物是否回滚
			// exceptionService.saveDataWihtUnCheckedException();// 运行时异常，会回滚
			
			// 保存数据，带非运行时异常，看看数据库事物能否回滚;
			// exceptionService.saveDataWithcheckedExcepton();// 因为是运行时异常，事物不会回滚
		} catch (Exception e) {
			log.error("这是异常啊" + e.getLocalizedMessage());
		}
		return mv;
	}
	
}
