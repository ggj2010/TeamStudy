package com.team.gaoguangjin.weixin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.team.gaoguangjin.weixin.bean.InputMessage;
import com.team.gaoguangjin.weixin.bean.Item;
import com.team.gaoguangjin.weixin.bean.Music;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlExchangeUtil {
	/**
	 * 获取post传过来的参数，将xml内容转换为InputMessage对象
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static InputMessage getObjectByXml(HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {
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
		return (InputMessage) xs.fromXML(xmlMsg.toString());
		
	}
	
	public static String getXmlByObject(InputMessage inputMessage) {
		XStream xStream = new XStream(new XppDriver());
		xStream.alias("xml", inputMessage.getClass());
		// 转换item标签
		if (null != inputMessage.getArticles() && inputMessage.getArticles().size() > 0)
			xStream.alias("item", inputMessage.getArticles().get(0).getClass());
		return xStream.toXML(inputMessage);
	}
	
	public static void main(String[] args) {
		InputMessage inputMessage = new InputMessage();
		
		inputMessage.setContent("测试");
		inputMessage.setDescription("描述");
		
		// 组装图文
		List<Item> list = new ArrayList<Item>();
		Item item = new Item();
		item.setDescription("神烦狗");
		item.setPicUrl("图片地址");
		item.setTitle("标题");
		item.setUrl("setUrl");
		list.add(item);
		
		Item item2 = new Item();
		item2.setDescription("神烦狗2");
		item2.setPicUrl("图片地址2");
		item2.setTitle("标题2");
		item2.setUrl("setUrl2");
		list.add(item2);
		
		inputMessage.setArticles(list);
		
		// 组装歌曲
		Music music = new Music();
		music.setDescription("歌曲Description");
		music.setHQMusicUrl("歌曲HQMusicUrl");
		music.setThumbMediaId("歌曲ThumbMediaId");
		music.setTitle("歌曲title");
		inputMessage.setMusic(music);
		
		XStream xStream = new XStream(new XppDriver());
		xStream.alias("xml", inputMessage.getClass());
		xStream.alias("Item", item.getClass());
		
		String xml = xStream.toXML(inputMessage);
		System.out.println(xml);
		
		// if ("event".equals(MsgType.Event.toString())) {
		// System.out.println("===");
		// }
		// System.out.println(MsgType.Event + "==");
		
		// String resultContent = "{\"code\":100000,\"text\":\"你也好 嘻嘻\"}";
		// JSONObject ro = (JSONObject) JSONObject.parse(resultContent);
		//
		// System.out.println(ro.get("text"));
		
	}
}
