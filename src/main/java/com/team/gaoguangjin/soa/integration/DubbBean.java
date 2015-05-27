package com.team.gaoguangjin.soa.integration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubbBean {
	public String testDubbo(String message) {
		log.info("【3】dubbo 调用具体业务返回数据：" + message);
		return "【3】dubbo返回==》" + message;
	}
}
