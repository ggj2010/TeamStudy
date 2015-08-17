<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ggj" tagdir="/WEB-INF/tags/"%>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<html>
<head>
<title>tag标签学习</title>
</head>

<script type="text/javascript">
	function callback(){
		alert("这是tag标签里面回调的方法");
	}
</script>
<body>
	<ggj:test name="gao" content="利用tag引入通用的内容" id="targtest" functions="callback()"></ggj:test>
</body>
</html>