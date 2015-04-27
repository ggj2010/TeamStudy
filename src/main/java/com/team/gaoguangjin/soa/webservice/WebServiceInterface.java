package com.team.gaoguangjin.soa.webservice;

import javax.xml.ws.Holder;

public interface WebServiceInterface {
	/**
	 * @Description:最简单的传参，返回参数
	 * @param message
	 * @return:String
	 */
	public String find(String message);
	
	/**
	 * @Description:
	 * @param message
	 * @param statInfo 传过去一个对象，这个对象也会返回来的
	 * @return:String
	 */
	public String getHolder(String message, Holder<String> statInfo);
}
