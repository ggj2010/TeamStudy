package com.team.youdao.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.util.HttpURLConnectionUtil;
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
		String message = "登陆失败";
		
		if (sessionUtil.isLogin(request)) {
			mv.setViewName("/index");
			message = "已经登陆过，session没过期无序登陆！";
		} else {
			if (checkLoginInfo(human, request)) {
				Object object = sessionUtil.getSeesionByName(request, Constant.LOGIN_TO_URL);
				if (object != null) {
					sessionUtil.removeAttribute(request, Constant.LOGIN_TO_URL);
					try {
						request.getRequestDispatcher("/" + object.toString()).forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					message = "跳转URL=" + object.toString();
				}
				mv.setViewName("/index");
			}
		}
		
		log.info(message);
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
	
	@RequestMapping(value = "/youdao/ajaxUpload", method = { RequestMethod.POST })
	@ResponseBody
	public String ajaxUpload(HttpServletResponse response, HttpServletRequest request) throws IOException {
		userSpringMVCAnother(request);
		// normalUpload(request);
		return "abc";
	}
	
	/**
	 * @Description: 用CommonsMultipartResolver来上传文件
	 */
	private void userSpringMVCAnother(HttpServletRequest request) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 由CommonsMultipartFile继承而来,拥有上面的方法.
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					String clientPath = request.getSession().getServletContext().getRealPath("/") + File.separator + fileName;
					File localFile = new File(clientPath);
					file.transferTo(localFile);
					System.out.println(fileName + clientPath);
				}
			}
		}
	}
	
	/**
	 * @Description: 如果用原生态的request去上传文件，那就去掉@RequestParam MultipartFile[] file参数
	 */
	@RequestMapping(value = "/youdao/upload", method = { RequestMethod.POST })
	public void upload(HttpServletResponse response, HttpServletRequest request, @RequestParam MultipartFile[] file) throws IOException {
		userSpringMvc(request, file);// 带参数的这种方式
		// normalUpload(request);// 不用springmvc自带的MultipartFile
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("上传成功");
	}
	
	/**
	 * @Description: 利用springmvc的上传
	 * @param request
	 * @param file
	 * @param myfiles
	 * @return:void
	 * @throws IOException
	 */
	private void userSpringMvc(HttpServletRequest request, MultipartFile[] myfiles) throws IOException {
		for (MultipartFile multipartFile : myfiles) {
			if (!multipartFile.isEmpty()) {
				String fileName = multipartFile.getOriginalFilename();
				System.out.println(fileName + multipartFile.getSize() + "=" + multipartFile.getContentType() + "=" + multipartFile.getBytes());
			}
		}
	}
	
	/**
	 * @Description:
	 * 不用sprigmvc的上传，用纯原生态的输入流request.getInputStream()；得到的头四行文件是可以判断文件的类型名称，如果用上springmvc的multipaartfile话,就不用截取头文件了
	 * @param request 前台jsp页面form表单必须要加 enctype="multipart/form-data"，不然是不会识别的。
	 * @throws IOException
	 * @return:void
	 */
	private void normalUpload(HttpServletRequest request) throws IOException {
		InputStream inputstrem = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputstrem));
		System.out.println("头行文件内容：" + br.readLine() + "==" + br.readLine() + "===" + br.readLine() + "=" + br.readLine());
		String str = null;
		StringBuilder sb = new StringBuilder();
		while ((str = br.readLine()) != null) {// 丢掉换行
			sb.append(str + "\n");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("e:aa.txt"))));
		bw.flush();
		bw.write(sb.toString());
		bw.close();
		
	}
	
	@RequestMapping(value = "/youdao/translation")
	public ModelAndView translation() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/homepage/translation");
		return mav;
	}
	
	@RequestMapping(value = "/youdao/gettranslation", method = { RequestMethod.GET })
	@ResponseBody
	public String getTranslation(String from, String message) {
		try {
			String to = "en";
			if (to.equals(from))
				to = "zh";
			// 字符串转换成unicode
			message = new String(message.getBytes("utf-8"));// 需要将字符集转换成utf-8.!!!!!!!!!!!
			String str = "from=" + from + "&query=" + message + "&simple_means_flag=3&to=" + to + "&transtype=trans";
			String url = "http://fanyi.baidu.com/v2transapi";
			
			// get方式
			String callBack = HttpURLConnectionUtil.getURLConnectionMessage(url, true, str);
			JSONObject transResultObject = (JSONObject) JSONObject.parseObject(callBack).get("trans_result");
			JSONObject dataObject = (JSONObject) ((JSONArray) transResultObject.get("data")).get(0);
			
			return dataObject.toJSONString();
			
		} catch (Exception e) {
			log.error("翻译失败！！" + e.getMessage());
		}
		return null;
	}
}
