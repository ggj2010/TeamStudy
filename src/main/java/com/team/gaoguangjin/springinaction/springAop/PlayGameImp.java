package com.team.gaoguangjin.springinaction.springAop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayGameImp implements PlayGame {
	public void playLOL() throws Exception {
		log.info("【2】玩lol");
	}
	
	public void playCs() {
		log.info("【2】玩cs");
	}
	
}
