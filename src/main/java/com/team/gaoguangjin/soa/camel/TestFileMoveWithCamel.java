package com.team.gaoguangjin.soa.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import com.team.gaoguangjin.soa.camel.process.FileMoveProcess;
import com.team.gaoguangjin.soa.camel.routebuilder.FileMoveRouteBuilder;

/**
 * @ClassName:FileMoveWithCamel.java
 * @Description: 每3秒扫描一次D:/ftp目录下，把这个目录下的文件移动到 E:/xfmovie
 * @author gaoguangjin
 * @Date 2015-5-22 下午5:04:56
 */
public class TestFileMoveWithCamel {
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		// 两种方式 实现路由类，过程类。内部类和外部定义
		
		// addRouteTypeOne(context);
		addRouteTypeTwo(context);
		
		context.start();
		System.in.read();
		context.stop();
	}
	
	private static void addRouteTypeTwo(CamelContext context) throws Exception {
		context.addRoutes(new FileMoveRouteBuilder());
	}
	
	private static void addRouteTypeOne(CamelContext context) throws Exception {
		context.addRoutes(new RouteBuilder() {// 这是一个路由类
			public void configure() {
				// 1、不要过程
				// from("file:D:/ftp?delay=3000").to("file:E:/xfmovie")
				
				// 2、添加过程
				from("file:D:/ftp?delay=3000").process(new FileMoveProcess()).to("file:E:/xfmovie");
				
				// 3、添加内部类过程
				// from("file:D:/ftp?delay=3000").process(new Processor() {
				// public void process(Exchange exchange) throws Exception {
				// log.info("这是内部类的过程");
				// }
				// }).to("file:E:/xfmovie");
			}
		});
		
	}
}
