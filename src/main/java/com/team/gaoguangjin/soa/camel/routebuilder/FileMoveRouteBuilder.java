package com.team.gaoguangjin.soa.camel.routebuilder;

import org.apache.camel.builder.RouteBuilder;

import com.team.gaoguangjin.soa.camel.process.FileMoveProcess;

/**
 * @ClassName:FileMoveRouteBuilder.java
 * @Description: 文件移动路由类。路由就好比是Camel中怎样将消息从一端传递到另一端的一个指令定义
 * @author gaoguangjin
 * @Date 2015-5-26 上午10:34:02
 */
public class FileMoveRouteBuilder extends RouteBuilder {
	
	/**
	 * 路由是用来配置规则的from=>to process 传输之前对数据的加工
	 */
	public void configure() throws Exception {
		from("file:D:/ftp?delay=3000").process(new FileMoveProcess()).to("file:E:/xfmovie");
	}
	
}
