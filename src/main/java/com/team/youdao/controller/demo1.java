package com.team.youdao.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class demo1 {
	@RequestMapping(value = "/demo1", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		System.out.println("123");
		log.error("123");
		log.debug("345");
		return mv;
	}
}
