package com.team.gaoguangjin.javabase.callback.otherpeople;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerroristAttack {
	public void attack(BoomWTC bmw) {
		// 这相当于【背景3】
		if (bmw.benLaDengDecide()) {// class B在方法中回调class A的方法，相当于【i call you back】
			// let's go.........
			log.info("恐怖分子进行轰炸");
		}
	}
}
