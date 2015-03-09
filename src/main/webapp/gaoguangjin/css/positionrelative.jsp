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

.left {
	width: 200px;
	height: 200px;
	background-color: blue;
	float: left;
}

.center {
	width: 200px;
	height: 200px;
	background-color: yellow;
	float: left;
}

.right {
	width: 200px;
	height: 200px;
	background-color: pink;
	float: left;
	z-index:2;
	position:relative;
}


.relative {
	position:relative;
	width: 200px;
	height: 200px;
	background-color: yellow;
	float: left;
	margin-left: 20px;
	z-index:1;
}
</style>
<body>
	<div class="left"></div>

	<div class="center"></div>

	<div class="right"></div>

	<div style="clear:both;margin-top: 10px;height: 30px;margin-bottom: 20px">
	position:relative;margin-left: 20px;黄色的div相对于原来位置移动了20px
	
	</div>

	<div class="left"></div>

	<div class="relative" title="position:relative;margin-left: 20px;">
	
		<div style="position: absolute; left:10px;width: 150px;height: 150px;background-color: grey" title="position: absolute; left:10px;width: 150px;height: 150px;background-color: grey">
			如果父级别的div 设置了position:relative  那么子级别的就可以用position: absolute进行绝对定位，相对与父级别
		</div>
	
	</div>

	<div class="right"></div>


</body>
</html>