package com.team.gaoguangjin.soa.webservice;

import java.util.Date;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;

@SOAPBinding(style = SOAPBinding.Style.RPC)
// 利用soap simple object access 进行交互
@WebService(name = "WebServiceInterface", targetNamespace = "www.ggjlovezjy.com", serviceName = "webservice")
// name = "WebServiceInterface" 生成对应wsdl的方面名称
public class WebServiceImp implements WebServiceInterface {
	public int m = 0;
	
	public String find(String message) {
		try {
			System.out.println(m + "值被调用,时间是" + new Date());
			Thread.sleep(4000);// 测试webservce并发调用
			System.out.println(m++ + "休眠之后,时间是" + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "find返回穿过来的值：" + message;
	}
	
	public String getHolder(String message, Holder<String> statInfo) {
		
		statInfo.value = "Holder<String> statInfo的值传过来，也可以返回回去的！很好用";
		return "getHolder返回穿过来的值：" + message;
	}
}
