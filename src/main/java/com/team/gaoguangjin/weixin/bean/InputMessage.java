package com.team.gaoguangjin.weixin.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InputMessage {
	// 用户关注时候事件
	public static final String EVENT_SUBSCRIBE = "subscribe";
	// 用户取消关注时候事件
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String MsgType = "text";
	private Long MsgId;
	// 文本消息
	private String Content;
	// 图片消息
	private String PicUrl;
	// 位置消息
	private String Location_X;
	private String Location_Y;
	private Long Scale;
	private String Label;
	// 链接消息
	private String Title;
	private String Description;
	private String Url;
	// 语音信息
	private String MediaId;
	private String Format;
	private String Recognition;
	// 事件
	private String Event;
	private String EventKey;
	private String Ticket;
	
	// 回复图文
	public String ArticleCount;
	public List<Item> Articles;
	
	// 回复歌曲
	public Music Music;
	
}
