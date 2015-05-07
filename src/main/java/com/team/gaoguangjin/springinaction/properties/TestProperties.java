package com.team.gaoguangjin.springinaction.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "tt")
public class TestProperties {
	@Value("${jdbc.url}")
	public String jdbcUrl;
	
	public void test() {
		System.out.println(jdbcUrl + "========================");
		
	}
}
