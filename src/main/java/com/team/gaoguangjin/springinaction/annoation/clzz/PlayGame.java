package com.team.gaoguangjin.springinaction.annoation.clzz;

import lombok.extern.slf4j.Slf4j;

import com.team.gaoguangjin.springinaction.annoation.method.MethodAnnation;

@Slf4j
public class PlayGame {
	@MethodAnnation(isTest = true)
	public void play() {
		log.info("玩游戏！");
	}
	
	public void test() {
		log.info("玩游戏之前，需要测试年龄！");
	}
}
