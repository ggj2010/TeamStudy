package com.team.gaoguangjin.soa.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@SOAPBinding(style = SOAPBinding.Style.RPC)
// 利用soap simple object access 进行交互
@WebService(name = "UserServiceInterface", targetNamespace = "www.ggjlovezjy.com", serviceName = "webservice")
public class UserServiceImp implements UserInterface {
	
	@Override
	public String getUserByName(String userName) {
		return "查询到的是：" + userName;
	}
	
}
