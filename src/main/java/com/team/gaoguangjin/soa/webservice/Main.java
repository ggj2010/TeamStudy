package com.team.gaoguangjin.soa.webservice;

import javax.xml.ws.Endpoint;

/**
 * @ClassName:Main.java
 * @Description: 调用WebService
 * @author gaoguangjin
 * @Date 2015-4-24 下午5:37:24
 */
public class Main {
	public static void main(String[] args) {
		// 发布WebService 服务
		Endpoint.publish("http://localhost:8080/webservice", new WebServiceImp());
		// 利用jdkbin 里面的wsimport 去生成对应的源码文件,生成的文件就在bin目录下面，然后拷贝到调用的项目里面
		// C:\Program Files\Java\jdk1.6.0_43\bin>wsimport -keep http://localhost:8080/webservice?wsdl
	}
}
