package com.team.youdao.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.team.youdao.base.Constant;
import com.team.youdao.base.SessionUtil;

/**
 * @ClassName:LoginInterceptor.java
 * @Description: 对指定连接进行拦截操作，一般这个类是进行记录日志操作的
 * @author gaoguangjin
 * @Date 2015-2-28 上午10:49:38
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	SessionUtil sessionUtil;
	private final NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	
	// 拦截之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			long beginTime = System.currentTimeMillis();
			startTimeThreadLocal.set(beginTime);
			// session里面有记录就直接放行
			if (sessionUtil.getSeesionByName(request, Constant.FILTERED_REQUEST) != null)
				return true;
			return changToLogin(request, response);
		} catch (Exception e) {
		}
		return true;
	}
	
	// 处理拦截
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	// 拦截之后
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		log.info("【本次访问消耗时间】：" + consumeTime);
	}
	
	/**
	 * @Description:获取用户的一些基本信息
	 * @param request
	 * @return:boolean
	 */
	private boolean changToLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String toUrl = request.getServletPath().toString();
			// 比如输入的地址是（/main/aa.do?a=b）
			// "a=b"==request.getQueryString()获取提交的内容
			if (!StringUtils.isEmpty(request.getQueryString())) {
				toUrl += "?" + request.getQueryString();
			}
			sessionUtil.setAttribute(request, Constant.LOGIN_TO_URL, toUrl);
			request.getRequestDispatcher("/WEB-INF/view/youdao/login.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
		return false;
	}
	
}
