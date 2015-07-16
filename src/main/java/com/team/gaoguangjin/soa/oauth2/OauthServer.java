package com.team.gaoguangjin.soa.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

/**
 * @ClassName:Server.java
 * @Description:   oauth2服务端 
 * @author gaoguangjin
 * @Date 2015-7-14 下午5:59:55
 * @url:https://cwiki.apache.org/confluence/display/OLTU/OAuth+2.0+Authorization+Server
 * @url:http://jinnianshilongnian.iteye.com/blog/2038646
 */
@Slf4j
public class OauthServer extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			
			// 检查clientid
			String clientid = oauthRequest.getClientId();
			
			// 检查客户端安全KEY是否正确
			String secret = oauthRequest.getClientSecret();
			
			log.info(clientid + ":" + secret);
			
			// 检查用户是否登陆，没登录直接跳转到登陆页面
			
			// 我们假设用户已经登录过
			
			MD5Generator md5 = new MD5Generator();
			String name = "gaoguangjin";
			
			// 生成授权码
			String authorizationCode = md5.generateValue(name);
			// 进行OAuth响应构建
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
			// 设置授权码
			builder.setCode(authorizationCode);
			// 得到到客户端重定向地址
			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
			
			// 构建响应
			OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
			// 根据OAuthResponse返回ResponseEntity响应
			resp.sendRedirect(response.getLocationUri());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 第一步客户端调用URL 带参数clientid和Secret。
	// 第二步服务器端收到参数之后 先验证clientid和Secret合法性，然后进行登陆操作。登陆成功之后，利用MD5生成授权码code返回给客户端
	// 第三步客户端可以将code保存下来，如果需要获取详细的数据的话可以 再以ulr带参数code 去访问服务器端
	// 第四部 服务器端验证code的准确性然后生成access token,同时返回给客户端。
	// 第五步 如果客户端想要调用服务器端什么资源 访问的url 带上token参数就可以了ttp://localhost:8080/test?
	// access_token=828beda907066d058584f37bcfd597b6
}
