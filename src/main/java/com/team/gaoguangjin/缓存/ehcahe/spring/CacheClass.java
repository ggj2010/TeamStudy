package com.team.gaoguangjin.缓存.ehcahe.spring;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheClass {
	
	@Cacheable(value = "springCache", key = "#id")
	public CacheBean cache(int id) {
		System.out.println("进入方法");
		return new CacheBean(1);
	}
	
}
