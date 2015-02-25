package com.team.gaoguangjin.test.mock;

import static org.junit.Assert.assertNotNull;
import lombok.extern.slf4j.Slf4j;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName:AccountServiceTest.java
 * @Description: 这里我没有实现 AccountDAO 接口，对于 Mock 测试来说，这也是不需要的。下面就是 AccountServiceImpl 的测试类
 * 
 * 1.Mock 方法是单元测试中常见的一种技术，它的主要作用是模拟一些在应用中不容易构造或者比较复杂的对象，从而把测试与测试边界以外的对象隔离开。
 * 
 * 同时也可以当调用别人的模块，而该模块又没有实现时（只提供接口），我们可以在独立的环境中测试自己的模块逻辑。
 * @author gaoguangjin
 * @Date 2015-2-12 上午10:08:24
 */
@Slf4j
public class AccountServiceTest {
	private AccountDAO accountDAOMock;
	private AccountServiceImpl accountService;
	
	@Before
	public void before() throws Exception {
		// 创建模仿对象 accountDAOMock
		accountDAOMock = EasyMock.createMock(AccountDAO.class);
		
		accountService = new AccountServiceImpl();
		// 将模仿对象set进去
		accountService.setAccountDAO(accountDAOMock);
	}
	
	@Test
	public void testGetAccount() {
		String name = "gaoguangjin";
		String pwd = "123";
		
		Account accountExpected = new Account();
		accountExpected.setName(name);
		accountExpected.setPwd(pwd);
		accountExpected.setId(new Long(10));
		// 将 Mock 对象复位，也就是重新设置 Mock 对象的状态和行为
		// EasyMock.reset(accountDAOMock);
		// 记录mock对象期望的行为
		EasyMock.expect(accountDAOMock.getByNameAndPwd(name, pwd)).andReturn(accountExpected);
		// 进入replay阶段
		EasyMock.replay(accountDAOMock);
		
		Account account = accountService.getAccount(name, pwd);
		
		log.info("account的返回值：" + account.getName());
		
		assertNotNull(account);
		
		// 对mock对象执行验证
		EasyMock.verify(accountDAOMock);
	}
}
