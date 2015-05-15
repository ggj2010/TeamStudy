package com.team.gaoguangjin.springinaction.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName:TestProperties.java
 * @Description: springweb项目中 需要配置spring.xml 和spring-dispatcher.xml 因为会加载两次。如果只有一个 那么@value在controller就会没有值
 * @author gaoguangjin
 * @Date 2015-5-14 下午2:34:42
 */
@Service(value = "tt")
public class TestProperties {
	@Value("${jdbc.url}")
	public String jdbcUrl;
	
	public void test() {
		System.out.println(jdbcUrl + "========================");
		
	}
}
