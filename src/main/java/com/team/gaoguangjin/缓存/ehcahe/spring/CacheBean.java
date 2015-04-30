package com.team.gaoguangjin.缓存.ehcahe.spring;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class CacheBean implements Serializable {
	
	private String name;
	private String id;
	
	public CacheBean(int id) {
		this.id = id + "";
	}
	
}
