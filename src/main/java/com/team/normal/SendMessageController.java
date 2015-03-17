package com.team.normal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.youdao.bean.LeaveMessageMessage;

/**
 * @ClassName:SendMessageController.java
 * @Description: 因为服务器上面没有安装数据库，所以占时留言放到日志里面
 * @author gaoguangjin
 * @Date 2015-3-14 上午12:32:14
 */
@Controller
@Slf4j
public class SendMessageController {
	
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessage(LeaveMessageMessage leaveMessageMessage) {
		log.error("【留言】：" + leaveMessageMessage.getName() + "|" + leaveMessageMessage.getEmail() + "|" + leaveMessageMessage.getSubject() + "|"
				+ leaveMessageMessage.getMessage());
		return null;
	}
}
