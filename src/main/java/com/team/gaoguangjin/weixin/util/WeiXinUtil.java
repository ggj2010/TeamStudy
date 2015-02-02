package com.team.gaoguangjin.weixin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.team.gaoguangjin.weixin.bean.InputMessage;

public class WeiXinUtil {
	public static String getCheckServerParam(String timestamp, String nonce, String Token) throws Exception {
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
		return params.get(0) + params.get(1) + params.get(2);
	}
	
	/**
	 * 转换接受和发送对象
	 * @param inputMessage
	 */
	public static void changeInputMessage(InputMessage inputMessage) throws Exception {
		String fromUserName = inputMessage.getFromUserName();
		String toUserName = inputMessage.getToUserName();
		inputMessage.setFromUserName(toUserName);
		inputMessage.setToUserName(fromUserName);
		
	}
}
