package com.team.gaoguangjin.javabase.exception.trywithresource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pool implements AutoCloseable {
	public void close() {
		log.info("pool回收");
	}
	
	public Object get() {
		log.info("得到对象");
		return null;
	}
}
