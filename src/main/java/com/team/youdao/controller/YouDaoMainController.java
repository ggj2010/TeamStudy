package com.team.youdao.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:YouDaoMainController.java
 * @Description: 有道模仿主页面controller层
 * @author gaoguangjin
 * @Date 2015-2-28 下午2:17:00
 */
@Slf4j
@Controller
@RequestMapping("/youdao/main")
public class YouDaoMainController {
	@RequestMapping(value = "/demo1", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index1");
		return mv;
	}
	
	@RequestMapping(value = "/demo2", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index2");
		return mv;
	}
}
