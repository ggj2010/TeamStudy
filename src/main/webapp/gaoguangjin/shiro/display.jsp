<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<!--shiros安全框架标签  -->
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登陆保存cookie</title>
</head>
<body>
	<div class="container basetype-container">
		<shiro:authenticated></shiro:authenticated>
	</div>
</body>
</html>