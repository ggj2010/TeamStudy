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
body ,pre{
	margin: 0px;
	padding: 0px;
	font-size: 18px;
}

.absolute1 {
	position: absolute;
	width: 200px;
	height: 200px;
	background-color: blue;
	top: 20px;
	left: 10px;
}

.absolute2 {
	position: absolute;
	width: 200px;
	height: 200px;
	background-color: blue;
	top: 400px;
	left: 1100px;
}

.relative1{
position: relative;
	width: 300px;
	height: 100px;
	background-color: red;
margin-left: 20px;
margin-top: 30px;
}


</style>
<body>
<div>
	<pre>position:absolute 是相对浏览器绝对定位的，位置将依据浏览器左上角的0点开始计算，根据设置的 top和 left 决定div的位置</pre>
</div>
	<div class="absolute1"
		title="position: absolute;width: 200px;height: 200px;background-color: blue;top: 10px;left: 10px;">position:
		absolute使用left，right，top，bottom等属性进行绝对定位</div>
			<div class="absolute2"
		title="position: absolute;width: 200px;height: 200px;background-color: blue;top: 400px;left: 1100px;">position:
		absolute使用left，right，top，bottom等属性进行绝对定位</div>
		
		<!--  -->
		<div style="margin-top:250px;height:150px ;border: 1px solid yellow; " title=>
		这是一个普通div margin-top:250px;height:150px ;border: 1px solid yellow;
		</div>
		
		<div class="relative1" title="margin-left: 20px;margin-top: 30px;">
			position：relative是相对定位,是相对于前面的容器定位的,或者说是它的【原始起点】进行移动这个时候不能用top left在定位.应该用margin.
		</div>
		
		
		
</body>
</html>