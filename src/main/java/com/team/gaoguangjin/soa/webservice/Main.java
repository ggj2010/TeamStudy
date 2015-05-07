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
		Endpoint.publish("http://192.168.199.115:8080/webservice", new UserServiceImp());
		
		// 每一个接口，发布一次。多个就发布多次
		// Endpoint.publish("http://192.168.199.115:8080/a", new WebServiceImp());
		// Endpoint.publish("http://192.168.199.115:8080/b", new WebServiceImp());
		// Endpoint.publish("http://192.168.199.115:8080/c", new WebServiceImp());
		
		// 利用jdkbin 里面的wsimport 去生成对应的源码文件,生成的文件就在bin目录下面，然后拷贝到调用的项目里面
		// C:\Program Files\Java\jdk1.6.0_43\bin>wsimport -keep http://localhost:8080/webservice?wsdl
	}
}
