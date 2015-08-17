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

.divabsolute {
	background-color: #33FF66;
	width: 100px;
	height: 100px;
	position: absolute;
	left: 50px;
	top: 50px;
}

.divnormal {
	background-color: black;
	width: 100px;
	height: 100px;
	left: 50px;
	top: 50px;
}

.divbig {
	background-color: #FF0000;
	width: 2000px;
	height: 2000px;
}

.divrelative {
	background-color: yellow;
	width: 300px;
	height: 300px;
	position: relative;
}

.divnorelative {
	background-color: yellow;
	width: 300px;
	height: 300px;
}

.divfixed {
	background-color: pink;
	width: 200px;
	height: 50px;
	position: fixed;
	left: 100px;
	top: 100px;
}
</style>
<body>
	<div class="div1">层1</div>

	<!--  
	
	<div class="divrelative">
		<!-- 
		<div class="divabsolute">absolute是相对于固定的位置</div>
		<div class="divfixed">divfixed是相对于固定的位置</div>
		
		relative
	</div>
	-->

	<!--有效果
	<div class="divrelative">
		<apan>父级别是relative</span>
		<div>
			<div class="divabsolute">父级别div没有position属性修饰</div>
		</div>
	</div>
-->

	<div class="divnorelative">
		<sapan>父级别是divnorelative</span>
		<div>
			<div class="divabsolute">父级别div没有position属性修饰</div>
		</div>
	</div>

	<!-- 
	<div class="divabsolute">absolute是相对于固定的位置</div>
	<div class="divfixed">divfixed是相对于浏览器的位置</div>
	<div class="divnormal">divnormal</div>
	<div class="divbig">divbig</div>
 -->






</body>
</html>