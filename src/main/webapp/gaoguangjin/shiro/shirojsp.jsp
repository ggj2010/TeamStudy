<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<!--shiros安全框架标签  -->
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆保存cookie</title>
</head>
<body>
	<div class="container basetype-container">
		<shiro:authenticated>
			<div class="alert alert-success" role="alert">验证成功！</div>
		</shiro:authenticated>

		<shiro:hasPermission name="user:create">
			<pre> 拥有passion user:user:create</pre>
		</shiro:hasPermission>
		
		<shiro:hasAnyPermissions name="user:create,user:update">
		<pre> 拥有passionuser:create,user:update  hasAnyPermissions需要自己去定义 </pre>
		</shiro:hasAnyPermissions>
		
		

		<shiro:user>  
				<pre>欢迎[<shiro:principal />]登录! &lt;shiro:user&gt;游客标签&lt;/shiro:user&gt;</pre>
		</shiro:user>

		<shiro:guest>
			<pre> &lt;shiro:guest&gt;游客标签&lt;/shiro:guest&gt;</pre>
		</shiro:guest>
		
		<shiro:hasRole name="admin">
		<pre> &lt;shiro:hasRole name="admin"&gt;拥有权限标签admin&lt;/shiro:hasRole&gt;</pre>
		</shiro:hasRole>
		
		<shiro:lacksRole name="test">
		<pre> &lt;shiro:shiro:lacksRole name="test"&gt; lackrole标示不包含某个role</pre>
		</shiro:lacksRole>
		
		
		<div class="list-group">
  	<a href="#" class="list-group-item list-group-item-success">shiro:authenticated/shiro:notAuthenticated 身份验证通过，不是记住我登录的。</a>
  <a href="#" class="list-group-item list-group-item-info">shiro:guest 游客模式</a>
  <a href="#" class="list-group-item list-group-item-warning">shiro:user 用户已经身份验证/记住我登录后显示相应的信息</a>
  <a href="#" class="list-group-item list-group-item-danger">shiro: principal  显示用户身份信息，默认调用Subject.getPrincipal()获取</a>
  	
  	<a href="#" class="list-group-item list-group-item-success">shiro:hasRole name="admin/shiro:hasAnyRoles name="admin,user"   </a>
  <a href="#" class="list-group-item list-group-item-info">shiro:lacksRole name="abc"/shiro:lacksPermission 没有某个角色/权限</a>
  <a href="#" class="list-group-item list-group-item-warning">shiro:user 用户已经身份验证/记住我登录后显示相应的信息</a>
  <a href="#" class="list-group-item list-group-item-danger">shiro: principal  显示用户身份信息，默认调用Subject.getPrincipal()获取</a>

</div>
	</div>
</body>
</html>