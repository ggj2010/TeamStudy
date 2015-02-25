package com.team.gaoguangjin.test.mock;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	private AccountDAO accountDAO;
	
	public Account getAccount(String name, String pwd) {
		log.info("------------------easyMock模拟创建了一个AccountDAO accountDAO-------");
		return this.accountDAO.getByNameAndPwd(name, pwd);
	}
	
}