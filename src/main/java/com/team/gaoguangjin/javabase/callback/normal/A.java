package com.team.gaoguangjin.javabase.callback.normal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A implements ACallBack {
	
	@Override
	public void doCRUD() {
		log.info("执行AdoCRUD");
	}
	
}
