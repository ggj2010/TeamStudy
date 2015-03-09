<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
body {
	margin: 0;
	padding: 0;
}

.float-left {
	float: left;
}

.float-right {
	float: right
}

.divleft1 {
	width: 50px;
	height: 50px;
	background-color: grey;
}

.divleft2 {
	width: 50px;
	height: 50px;
	background-color: red;
	margin-left: 20px;
	padding: 20px;
}

.div3 {
	width: 50px;
	height: 50px;
	background-color: pink;
}

.clear{
clear: both;
}


</style>
<body>
	<div class="divleft1 float-left">1</div>
	<div class="divleft2 float-left">2</div>
	<div class="div3 float-right">3</div>
	<!-- 
	<div class="clear"></div> -->
	<span>55555</span>
	<div style="background-color: yellow">没有加float的就不会浮动，会按照浏览器一开始位置，而且z-indx是相对于position才有作用</div>
</body>
</html>