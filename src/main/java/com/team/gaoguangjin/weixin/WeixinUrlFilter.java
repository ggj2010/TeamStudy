package com.team.gaoguangjin.weixin;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.gaoguangjin.weixin.bean.InputMessage;
import com.team.gaoguangjin.weixin.bean.MsgType;
import com.team.gaoguangjin.weixin.bean.OutputMessage;
import com.team.gaoguangjin.weixin.bean.SHA1;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class WeixinUrlFilter implements Filter {
	
	// 这个Token是给微信开发者接入时填的
	// 可以是任意英文字母或数字，长度为3-32字符
	private static String Token = "ggj20101234567890";
	
	public void init(FilterConfig config) throws ServletException {
		System.out.println("WeixinUrlFilter启动成功!");
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 微信服务器将发送GET请求到填写的URL上,这里需要判定是否为GET请求
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		System.out.println("获得微信请求:" + request.getMethod() + " 方式");
		if (isGet) {
			// 验证URL真实性
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			List<String> params = new ArrayList<String>();
			params.add(Token);
			params.add(timestamp);
			params.add(nonce);
			// 1. 将token、timestamp、nonce三个参数进行字典序排序
			Collections.sort(params, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
			String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
			if (temp.equals(signature)) {
				response.getWriter().write(echostr);
			}
		} else {
			// 处理接收消息
			ServletInputStream in = request.getInputStream();
			// 将POST流转换为XStream对象
			XStream xs = new XStream(new DomDriver());
			// 将指定节点下的xml节点数据映射为对象
			xs.alias("xml", InputMessage.class);
			// 将流转换为字符串
			StringBuilder xmlMsg = new StringBuilder();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				xmlMsg.append(new String(b, 0, n, "UTF-8"));
			}
			// 将xml内容转换为InputMessage对象
			InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());
			// 取得消息类型
			String msgType = inputMsg.getMsgType();
			// 根据消息类型获取对应的消息内容
			if (msgType.equals(MsgType.Text.toString())) {
				try {
					// 文本消息
					System.out.println("开发者微信号：" + inputMsg.getToUserName());
					System.out.println("发送方帐号：" + inputMsg.getFromUserName());
					System.out.println("消息创建时间：" + inputMsg.getCreateTime());
					System.out.println("消息内容：" + inputMsg.getContent());
					System.out.println("消息Id：" + inputMsg.getMsgId());
					// 发送文本消息 start
					// XStream xstream = new XStream(new XppDriver() {
					// public HierarchicalStreamWriter createWriter(Writer out) {
					// return new PrettyPrintWriter(out) {
					// protected void writeText(QuickWriter writer, String text) {
					// if (!text.startsWith("<![CDATA[")) {
					// text = "<![CDATA[" + text + "]]>";
					// }
					// writer.write(text);
					// }
					// };
					// }
					// });
					// // 创建文本发送消息对象
					// TextOutputMessage outputMsg = new TextOutputMessage();
					// outputMsg.setContent("你的消息已经收到，谢谢！");
					// setOutputMsgInfo(outputMsg, inputMsg);
					// // 设置对象转换的XML根节点为xml
					// xs.alias("xml", outputMsg.getClass());
					// // 将对象转换为XML字符串
					// String xml = xstream.toXML(outputMsg);
					// // 将内容发送给微信服务器，发送到用户手机
					// response.getWriter().write(xml);
					
					StringBuffer sb = new StringBuffer();
					sb.append(" <xml> ");
					sb.append(" <ToUserName>" + inputMsg.getFromUserName() + "</ToUserName> ");
					sb.append(" <FromUserName>" + inputMsg.getToUserName() + "</FromUserName> ");
					sb.append(" <CreateTime>" + new Date() + "</CreateTime> ");
					sb.append(" <MsgType>text</MsgType> ");
					sb.append(" <Content>测试回复的内容，目前比较简单，只是回复时间：" + new Date() + "</Content> ");
					sb.append(" </xml> ");
					
					OutputStream os = response.getOutputStream();
					os.write(sb.toString().getBytes("UTF-8"));
					os.flush();
					os.close();
				}
				catch (Exception ex) {
					System.out.println("消息接受和发送出现异常!");
					ex.printStackTrace();
				}
			}
		}
	}
	
	// 设置详细信息
	private static void setOutputMsgInfo(OutputMessage oms, InputMessage msg) throws Exception {
		// 设置发送信息
		Class<?> outMsg = oms.getClass().getSuperclass();
		Field CreateTime = outMsg.getDeclaredField("CreateTime");
		Field ToUserName = outMsg.getDeclaredField("ToUserName");
		Field FromUserName = outMsg.getDeclaredField("FromUserName");
		
		ToUserName.setAccessible(true);
		CreateTime.setAccessible(true);
		FromUserName.setAccessible(true);
		
		CreateTime.set(oms, new Date().getTime());
		ToUserName.set(oms, msg.getFromUserName());
		FromUserName.set(oms, msg.getToUserName());
	}
	
	public void destroy() {
	}
}
