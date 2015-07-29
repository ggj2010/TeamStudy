package com.team.gaoguangjin.shiro.spring;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

public class AnnationClass {
	@RequiresRoles("student")
	public String getName() {
		return "这是role主键";
	}
	
	@RequiresPermissions("student:find")
	public String find() {
		return "这是权限主键";
	}
	
	@RequiresRoles("students")
	public String notExistsRole() {
		return "这是权限notExistsRole主键";
	}
}
