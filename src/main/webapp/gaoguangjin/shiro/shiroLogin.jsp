<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>测试shiro</title>
</head>
<body>
	<div class="container basetype-container">
		<form action="${path}/login.do" method="post" class="form-horizontal">
			<div class="row">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-8">
						<input type="text" name="name" class="form-control"
							maxlength="64" placeholder="用户名">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-8">
						<input type="password" name="password" class="form-control"
							maxlength="64" placeholder="密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-info" type="submit">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>