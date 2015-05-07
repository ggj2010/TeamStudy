package com.team.gaoguangjin.soa.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserInterface {
	String getUserByName(String userName);
}
