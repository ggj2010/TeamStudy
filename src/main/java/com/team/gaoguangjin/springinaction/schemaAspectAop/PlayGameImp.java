package com.team.gaoguangjin.springinaction.schemaAspectAop;

import lombok.extern.slf4j.Slf4j;

import com.team.gaoguangjin.springinaction.test.Monitorable;

@Slf4j
@Monitorable
public class PlayGameImp implements PlayGame {
	public String playLOL(String param) {
		log.info("【2】玩lol");
		return param;
	}
	
	public String playCs(String param) {
		log.info("【2】玩cs");
		return param;
	}
	
	public String playCar(String param) {
		log.info("【2】玩车啊");
		return param;
	}
	
	public String playGame(String gameName) {
		log.info("【2】玩" + gameName + "啊!!");
		return gameName;
	}
	
	public String playAfter(String param) {
		log.info("【2】玩【之后】啊");
		return param;
	}
	
	public String playBefore(String param) {
		log.info("【2】玩【之前】啊");
		return param;
	}
	
	public String playMiddle(String param) {
		log.info("【2】玩【中间】啊");
		return param;
	}
}
