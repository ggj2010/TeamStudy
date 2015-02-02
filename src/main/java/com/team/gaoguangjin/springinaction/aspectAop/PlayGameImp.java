package com.team.gaoguangjin.springinaction.aspectAop;

import lombok.extern.slf4j.Slf4j;

import com.team.gaoguangjin.springinaction.annoation.JdkAnnoation;

@Slf4j
public class PlayGameImp implements PlayGame {
	public void playLOL() {
		log.info("【2】玩lol");
	}
	
	public void playCs() {
		log.info("【2】玩cs");
	}
	
	@JdkAnnoation(url = "www.baidu.com", value = true)
	public void playAnnation() {
		log.info("【2】玩注解的类啊啊啊");
	}
	
}
