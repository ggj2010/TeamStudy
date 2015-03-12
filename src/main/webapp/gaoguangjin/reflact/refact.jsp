<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
request.setAttribute("path",path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
        <base href="<%=basePath%>"><title>My JSP 'robot.jsp' starting page</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.10.2.js">
        </script>
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js">
        </script>
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js">
        </script>
  </head>
  
  <body>
	
	<div style="margin-top:50px;border:1 solid #000;">
	<blockquote>这是测试IOC</blockquote>
	<div style="margin-top:20px">
  	<form class="form-horizontal" role="form" action="<%=path%>/refactTest" method="post">
			<div class="form-group has-success has-feedback">
				<label class="col-xs-1 control-label">姓名</label>
				<div class="col-xs-2">
					<input type="text" name="studentName" class="form-control">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				<label class="col-xs-1 control-label">年龄</label>
				<div class="col-xs-2">
					<input type="text" name="studentAge" class="form-control">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				<label class="col-xs-1 control-label">教师姓名</label>
				<div class="col-xs-2">
					<input type="text" name="teacherName" class="form-control">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
				<label class="col-xs-1 control-label">教师专业${path}</label>
				<div class="col-xs-2">
					<input type="text" name="teacherMajor" class="form-control">
					<span class="glyphicon glyphicon-ok form-control-feedback"></span>
				</div>
			</div>
			<button type="submit" class="btn btn-danger btn-block">Submit</button>
		</form>
		</div>
	</div>	
  	
  </body>
</html>
