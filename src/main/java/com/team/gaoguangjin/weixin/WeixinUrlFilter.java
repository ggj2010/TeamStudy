package com.team.gaoguangjin.weixin;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.team.gaoguangjin.weixin.bean.InputMessage;
import com.team.gaoguangjin.weixin.bean.Item;
import com.team.gaoguangjin.weixin.bean.MsgType;
import com.team.gaoguangjin.weixin.bean.Music;
import com.team.gaoguangjin.weixin.bean.OutputMessage;
import com.team.gaoguangjin.weixin.bean.SHA1;
import com.team.gaoguangjin.weixin.util.WeiXinUtil;
import com.team.gaoguangjin.weixin.util.XmlExchangeUtil;

@Slf4j
public class WeixinUrlFilter implements Filter {
	
	// 这个Token是给微信开发者接入时填的
	// 可以是任意英文字母或数字，长度为3-32字符
	private static final String Token = "ggj20101234567890";
	
	public void init(FilterConfig config) throws ServletException {
		log.info("WeixinUrlFilterfilter启动成功!");
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 微信服务器将发送GET请求到填写的URL上,这里需要判定是否为GET请求
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		log.info("获得微信请求:" + request.getMethod() + " 方式");
		
		try {
			if (isGet) {
				checkServer(req, res);
			} else {
				InputMessage inputMsg = XmlExchangeUtil.getObjectByXml(request);
				;
				
				String content = getCallBackContent(inputMsg);
				System.out.println(content);
				OutputStream os = response.getOutputStream();
				os.write(content.toString().getBytes("UTF-8"));
				os.flush();
				os.close();
				
				// String msgType = null;
				// // 根据消息类型获取对应的消息内容
				// if (msgType.equals(MsgType.Text.toString())) {
				// try {
				// // 文本消息
				// log.info("开发者微信号：" + inputMsg.getToUserName());
				// log.info("发送方帐号：" + inputMsg.getFromUserName());
				// log.info("消息创建时间：" + inputMsg.getCreateTime());
				// log.info("消息内容：" + inputMsg.getContent());
				// log.info("消息Id：" + inputMsg.getMsgId());
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
				
				// StringBuffer sb = new StringBuffer();
				// sb.append(" <xml> ");
				// sb.append(" <ToUserName>" + inputMsg.getFromUserName() + "</ToUserName> ");
				// sb.append(" <FromUserName>" + inputMsg.getToUserName() + "</FromUserName> ");
				// sb.append(" <CreateTime>" + new Date() + "</CreateTime> ");
				// sb.append(" <MsgType>text</MsgType> ");
				// sb.append(" <Content>测试回复的内容，目前比较简单，只是回复时间：" + new Date() + "</Content> ");
				// sb.append(" </xml> ");
				//
				// OutputStream os = response.getOutputStream();
				// os.write(sb.toString().getBytes("UTF-8"));
				// os.flush();
				// os.close();
			}
		} catch (Exception ex) {
			log.info("消息接受和发送出现异常!");
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 根据传来的类型，返回对应的数据
	 * @param inputMsg Text("text"), Image("image"), Music("music"), Video("video"), Voice("voice"),
	 * Location("location"), Link("link"), Event( "event"); 传过来的有以上内容。
	 * @return
	 * @throws Exception
	 */
	private String getCallBackContent(InputMessage inputMessage) throws Exception {
		WeiXinUtil.changeInputMessage(inputMessage);
		// 文本特殊处理
		String msgType = inputMessage.getMsgType();
		if (msgType.equals(MsgType.Text.toString()))
			getTextContent(inputMessage);
		if (msgType.equals(MsgType.Image.toString()))
			getImageContent(inputMessage);
		if (msgType.equals(MsgType.Music.toString()))
			getMusicContent(inputMessage);
		if (msgType.equals(MsgType.Video.toString()))
			getVideoContent(inputMessage);
		if (msgType.equals(MsgType.Voice.toString()))
			getVoiceContent(inputMessage);
		if (msgType.equals(MsgType.Location.toString()))
			getLocationContent(inputMessage);
		if (msgType.equals(MsgType.Link.toString()))
			getLinkContent(inputMessage);
		if (msgType.equals(MsgType.Event.toString()))
			getEventContent(inputMessage);
		return XmlExchangeUtil.getXmlByObject(inputMessage);
	}
	
	/**
	 * 我们自微信平台填写服务器配置保存时候，微信平台会发送一个get方式的验证。 验证服务器地址的有效性， 加密/校验流程如下： 1.将token、timestamp、nonce三个参数进行字典序排序
	 * 2.将三个参数字符串拼接成一个字符串进行sha1加密 3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信 开发者通过检验signature对请求进行校验（下面有校验方式）。
	 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	private void checkServer(ServletRequest request, ServletResponse response) throws Exception {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		
		String temp = SHA1.encode(WeiXinUtil.getCheckServerParam(timestamp, nonce, Token));
		if (temp.equals(signature)) {
			response.getWriter().write(echostr);
		}
	}
	
	/**
	 * http://mp.weixin.qq.com/wiki/2/5baf56ce4947d35003b86a9805634b1e.html
	 * @param inputMsg
	 * @return
	 */
	
	private void getEventContent(InputMessage inputMessage) {
		// 返回的类型是text
		inputMessage.setMsgType("text");
		// 关注
		if (inputMessage.EVENT_SUBSCRIBE.equals(inputMessage.getEvent())) {
			inputMessage.setContent("谢谢关注【猫与狗的世界 】,此账号正在开发学习阶段");
		} else {
			inputMessage.setContent("谢谢关注【猫与狗的世界 】,此账号正在开发学习阶段");
		}
		
	}
	
	private void getLinkContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getLocationContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getVoiceContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getVideoContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getMusicContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getImageContent(InputMessage inputMsg) {
		// TODO Auto-generated method stub
	}
	
	private void getTextContent(InputMessage inputMessage) throws ParseException, IOException {
		// 根据传过来的值来决定返回的内容
		String inputContent = inputMessage.getContent();
		
		// 目前用if判断来决定，到时候遇到好方法迁移
		// 1.回复图文信息
		if (inputContent.trim().equals("狗")) {
			inputMessage.setMsgType("news");
			inputMessage.setArticleCount("1");
			List<Item> list = new ArrayList<Item>();
			Item item = new Item();
			item.setDescription("这是一个神奇的神烦狗啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
			item.setPicUrl("http://b.hiphotos.baidu.com/zhidao/pic/item/37d12f2eb9389b5016f8fd738635e5dde7116e8f.jpg");
			item.setTitle("傻逼狗狗啊");
			item.setUrl("http://image.baidu.com/i?tn=baiduimage&ipn=r&ct=201326592&cl=2&fm=detail&lm=-1&st=-1&sf=2&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E7%A5%9E%E7%83%A6%E7%8B%97");
			list.add(item);
			
			Item item2 = new Item();
			item2.setDescription("性感女神图片啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
			item2.setPicUrl("http://img.zjol.com.cn/pic/0/05/70/94/5709408_999439.jpg");
			item2.setTitle("性感女神");
			item2.setUrl("http://image.baidu.com/i?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E6%80%A7%E6%84%9F%E5%A5%B3%E7%A5%9E&step_word=&pn=79&spn=0&di=101481357780&pi=&rn=1&is=&istype=&ie=utf-8&oe=utf-8&in=30804&cl=2&lm=-1&st=&cs=1347010172%2C3476933164&os=2850520212%2C1329856165&adpicid=0&ln=1000&fr=ala&fmq=1422090462295_R&ic=&s=&se=&sme=0&tab=&width=&height=&face=&ist=&jit=&cg=&objurl=http%3A%2F%2Fimg.zjol.com.cn%2Fpic%2F0%2F05%2F70%2F94%2F5709408_999439.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fgjof_z%26e3Bz3_z%26e3Begjp_z%26e3BvgAzdH3Fda8na0dc8l9ldnnb_d_z%26e3Bip4s");
			list.add(item2);
			
			inputMessage.setArticles(list);
			
		} else if (inputContent.trim().contains("音乐")) {
			inputMessage.setMsgType("music");
			
			Music music = new Music();
			// 歌曲没弄查询接口，直接随机弄几个
			music.setDescription("做给我老婆听的--张静月");
			
			if (inputContent.contains("你是我的眼")) {
				music.setHQMusicUrl("http://bcs.duapp.com/testggj/nishiwodeyan.mp3");
				music.setMusicUrl("http://bcs.duapp.com/testggj/nishiwodeyan.mp3");
				music.setTitle("你是我的眼");
			} else if (inputContent.contains("平凡之路")) {
				music.setHQMusicUrl("http://bcs.duapp.com/testggj/pingfanzhilu.mp3.mp3");
				music.setMusicUrl("http://bcs.duapp.com/testggj/pingfanzhilu.mp3.mp3");
				music.setTitle("平凡之路");
			} else if (inputContent.contains("情歌王")) {
				music.setHQMusicUrl("http://bcs.duapp.com/testggj/qinggewang.mp3");
				music.setMusicUrl("http://bcs.duapp.com/testggj/qinggewang.mp3");
				music.setTitle("情歌王");
			} else if (inputContent.contains("李白")) {
				music.setHQMusicUrl("http://bcs.duapp.com/testggj/libai.mp3");
				music.setMusicUrl("http://bcs.duapp.com/testggj/libai.mp3");
				music.setTitle("李白");
			} else {
				music.setHQMusicUrl("http://bcs.duapp.com/testggj/qinggewang.mp3");
				music.setMusicUrl("http://bcs.duapp.com/testggj/qinggewang.mp3");
				music.setTitle("情歌王");
			}
			
			inputMessage.setMusic(music);
			//
		} else if (inputContent.contains("听歌")) {
			inputMessage.setMsgType("text");
			inputMessage.setContent("目前功能做的简单,只有以下歌曲回复格式如下:\r" + "【1】音乐你是我的眼\r" + "【2】音乐平凡之路\r" + "【3】音乐情歌王\r" + "【4】音乐李白\r");
			
		} else {
			getRobotContent(inputMessage);
		}
		
	}
	
	/**
	 * 获取图灵机器人的对话值
	 * @param inputMessage
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private void getRobotContent(InputMessage inputMessage) throws ClientProtocolException, IOException {
		// 返回的类型是text
		inputMessage.setMsgType("text");
		String APIKEY = "4b441cb500f431adc6cc0cb650b4a5d0";
		String INFO = URLEncoder.encode(inputMessage.getContent(), "utf-8");
		String requesturl = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		
		HttpGet req = new HttpGet(requesturl);
		HttpResponse res = HttpClients.createDefault().execute(req);
		String resultContent = "";
		if (res.getStatusLine().getStatusCode() == 200) {
			resultContent = EntityUtils.toString(res.getEntity());
		}
		JSONObject ro = (JSONObject) JSONObject.parse(resultContent);
		inputMessage.setContent(ro.getString("text"));
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
