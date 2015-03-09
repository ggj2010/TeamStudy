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
/**去除浏览器的默认初始值**/
body,pre {
	margin: 0px;
	padding: 0px;
	font-size: 18px;
}

.div1 {
	background-color: #FF0000;
	width: 100px;
	height: 100px;
}

.div2 {
	background-color: #33FF66;
	width: 100px;
	height: 100px;
	position: absolute;
}

.div11 {
	background-color: #FF0000;
	width: 2000px;
	height: 2000px;
}

.div22 {
	background-color: pink;
	width: 100px;
	height: 100px;
	position: fixed;
	left: 50px;
	top: 50px;
}
</style>
<body>
	<div class="div1">层1</div>
<div class="div2">absolute是相对于浏览器的位置</div>

	<div class="div11"></div>
<div class="div22">fixed是始终子浏览器某个位置的</div>





</body>
</html>