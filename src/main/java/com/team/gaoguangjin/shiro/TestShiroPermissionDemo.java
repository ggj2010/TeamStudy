package com.team.gaoguangjin.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName:ShiroPermissionDemo.java
 * @Description: 基于资源的访问控制（显示角色）   
 * @author gaoguangjin
 * @Date 2015-7-17 下午6:24:08
 */
public class TestShiroPermissionDemo extends BaseTest {
	
	/**
	 * @Description:  
	 * [users]
		gao=123,role1,role2
		wang=123,role1
	#对资源user拥有create、update权限
		role1=user:create,user:update
	#对资源user拥有create、delete权限
		role2=user:create,user:delete
	 */
	@Test
	public void test1() {
		login("classpath:shiro/shiro-permission.ini", "gao", "123");
		// 角色=权限1，权限2
		Assert.assertTrue(subject().isPermitted("user:create"));
		Assert.assertTrue(subject().isPermittedAll("user:create", "user:delete"));
		// user:find没有这个资源权限
		// Assert.assertTrue(subject().isPermitted("user:view"));
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testCheckPermission() {
		login("classpath:shiro/shiro-permission.ini", "gao", "123");
		// 断言拥有权限：user:create
		subject().checkPermission("user:create");
		// 断言拥有权限：user:delete and user:update
		subject().checkPermissions("user:delete", "user:update");
		// 断言拥有权限：user:view 失败抛出异常
		subject().checkPermissions("user:");
	}
	
	/**
	到此基于资源的访问控制（显示角色）就完成了，也可以叫基于权限的访问控制，这种方式的一般规则是“资源标识符：操作”，
	即是资源级别的粒度；这种方式的好处就是如果要修改基本都是一个资源级别的修改，不会对其他模块代码产生影响，粒度小。
	但是实现起来可能稍微复杂点，需要维护“用户——角色，角色——权限（资源：操作）”之间的关系。  
	**/
	
	/**
	 * @Description: 字符串通配符权限
	 * @return:void
	 */
	@Test
	public void test2() {
		login("classpath:shiro/shiro-permission.ini", "wang", "123");
		// 角色=权限1，权限2
		Assert.assertTrue(subject().isPermitted("system:user:update"));
		Assert.assertTrue(subject().isPermittedAll("user:create", "user:delete"));
	}
	
	/**
	 * @Description:  表示资源/操作/实例的分割；“,”表示操作的分割；“*”表示任意资源/操作/实例
	 * @see:资源标识符：操作：对象实例ID 对哪个资源的哪个实例可以进行什么操作
	 * @return:void
	 */
	@Test
	public void test3() {
		login("classpath:shiro/shiro-permission.ini", "li", "123");
		// role42="system:user:update,delete"
		Assert.assertTrue(subject().isPermittedAll("system:user:update,delete"));
		
		/*****************单个资源全部权限*************************/
		// 用户拥有资源“system:user”的“create”、“update”、“delete”和“view”所有权限。如上可以简写成：role52=system:user:*
		Assert.assertTrue(subject().isPermittedAll("system:user:*"));
		
		/*****************所有资源全部权限*************************/
		// 用户拥有所有资源的“view”所有权限。假设判断的权限是“"system:user:view”，那么需要“role5=*:*:view”
		Assert.assertTrue(subject().isPermittedAll("user:view"));
		
		/******************单个实例单个权限***********************************/
		Assert.assertTrue(subject().isPermittedAll("user:view:1"));
		// 单个实例多个权限 两种写法一样
		Assert.assertTrue(subject().isPermittedAll("user:delete,update:1"));
		Assert.assertTrue(subject().isPermittedAll("user:delete:1", "user:update:1"));
		
		/*************所有实例单个权限*********************/
		// role74=user:auth:*
		Assert.assertTrue(subject().isPermittedAll("user:auth:1", "user:auth:2"));
		
		/*************所有实例所有权限*********************/
		Assert.assertTrue(subject().isPermittedAll("user:view:1", "user:auth:2", "user:auth:1", "user:delete:1", "user:update:1"));
		Assert.assertTrue(subject().isPermittedAll("user:*:*"));
	}
}
