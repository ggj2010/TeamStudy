<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>bootstrp栅栏</title>
<style>
.row div {
	border-color: grey;
	border: 1px solid pink;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<ol>
					<li>col-xs</li>
					<li>col-xs</li>
				</ol>
			</div>
			<div class="col-xs-4">
				<ol>
					<li>col-xs</li>
					<li>col-xs</li>
				</ol>
			</div>
			<div class="col-xs-4">
				<ol>
					<li>col-xs</li>
					<li>col-xs</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">col-sm-4</div>
			<div class="col-sm-4">col-sm-4</div>
			<div class="col-sm-4">col-sm-4</div>
		</div>
		<div class="row">
			<div class="col-md-4">col-md-4</div>
			<div class="col-md-4">col-md-4</div>
			<div class="col-md-4">col-md-4</div>
		</div>
	</div>
</body>
</html>