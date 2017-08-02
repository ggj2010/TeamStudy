package com.team.gaoguangjin.javabase.exception.trywithresource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoolTwo implements AutoCloseable {
	public void close() {
		log.info("pool2回收");
	}
	
	public Object get() {
		log.info("得到2对象");
		return null;
	}
}
