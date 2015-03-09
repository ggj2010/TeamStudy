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
.fj1 {
position:absolute;
	width: 150px;
	height: 150px;
	border: 1px solid #000;
	background: #999;
}

.zj1 {
position:relative;
	width: 100px;
	height: 100px;
	border: 1px solid yellow;
	background: #FFF;
	z-index: 1;  
}

.reddiv {
position:relative;
	width: 130px;
	height: 70px;
	border: 1px solid #F00;
	background: red;
	z-index: 2;  
}

.div1 {
position:absolute;
	width: 150px;
	height: 150px;
	background: #999;
}

.div2 {
position:absolute;
	width: 100px;
	height: 100px;
	background: blue;
}

.div3 {
position:absolute;
	width: 50px;
	height: 50px;
	background: red;
}


.div11 {
position:absolute;
	width: 150px;
	height: 150px;
	background: #999;
	z-index:1;
}

.div22 {
position:absolute;
	width: 100px;
	height: 100px;
	background: blue;
	z-index:3;
}

.div33 {
position:absolute;
	width: 50px;
	height: 150px;
	background: red;
	z-index:2;
}

</style>
<body>
	<div>
		<div class="fj1">我在下面 上的发生大幅 上的发生大幅随碟附送的</div>
		<div class="zj1">我浮动在上面</div>
		<div class="reddiv">我是red</div>
		我们使用z-index重叠顺序样式，在实际DIV+CSS布局时候我们需要绝对定位样式，并且可以使
用left、right进行定位，通过不同z-index值实现层重叠顺序排列。 z-index只能在position属性值为relative或absolute或fixed的元素上有效。
	</div>

	<pre>没用z-index的div</pre>
	<div class="div1">1</div>
	<div class="div2">2</div>
	<div class="div3">3</div>
	
	<div style="margin:200px"></div>
	<pre>用了z-index的div,z-index 值越大的越靠前.默认的 z-index 是 0。如果我们让多个div 在同一个位置，就要设置z-index，那个div在那个div之上</pre>
	
	<div class="div11">【级别1】1</div>
	<div class="div22">【级别是3】width: 100px;
	height: 100px;</div>
	<div class="div33">【级别是2】width: 50px;
	height: 150px;</div>
</body>
</html>