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
body,border {
	margin: 0;
	padding: 0;
}

/*嵌套样式*/  
.contain { width:700px; height:560px; margin:20px; padding:10px 20px 10px 20px; border:1px solid #FF6600; text-align:center}   
.inner_contain { width:550px; height:100px; border:1px solid #009966;margin-top: 10px;}

</style>
<body>
<!-- --> 
<div class="contain">  
    <div class="inner_contain">  
    padding 是相对于原来的位置内部增加 margin是相对于原来的位置外部增加。增加的部分不能使用，会占用空间的
    </div>  
    <div class="inner_contain">  
    padding 是相对于原来的位置内部增加 margin是相对于原来的位置外部增加。增加的部分不能使用，会占用空间的
    </div>  
</div> 

<pre>--1--</pre>

<div style="width:250px; height:200px; background-color: blue; ">
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" title="border:1px solid white ;">
	没加的话 margin-top: 20px父div一起移动
	</div>
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" ></div>
</div>
<pre>--2--</pre>
<div style="width:250px; height:200px; background-color: blue;border:1px solid white ;">
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" >
	父辈div加 border:1px solid white;就可以解决margin-top: 20px;启作用
	</div>
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" ></div>
</div>
<pre>--3--</pre>
<div style="width:250px; height:200px; background-color: blue;overflow: hidden">
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" >
	父div加 overflow: hidden就可以解决
	</div>
	<div style="width:250px; height:50px; background-color: yellow;margin-top: 20px;" ></div>
</div>


嵌套div中margin-top转移问题的解决办法

在这两个浏览器中，有两个嵌套关系的div，如果外层div的父元素padding值为0，那么内层div的margin-top或者margin-bottom的值会“转移”给外层div。




<div style="background-color:#FF0000;width:300px; height:100px">上部层</div>

<div style="background-color:#009900; width:300px; height:300px;overflow:hidden "> <!--父层-->
     <div style="margin:50px; background-color:#000000;width:200px; height:200px"">子层</div>
</div>



原因：盒子没有获得 haslayout  造成 margin-top无效
 
解决办法：
1、在父层div加上：overflow:hidden；
2、把margin-top外边距改成padding-top内边距 ；
3、父元素产生边距重叠的边有不为 0 的 padding 或宽度不为 0 且 style 不为 none 的 border。
    父层div加： padding-top: 1px;
4、让父元素生成一个 block formating context，以下属性可以实现
    * float: left/right
    * position: absolute
    * display: inline-block/table-cell(或其他 table 类型)
    * overflow: hidden/auto
   父层div加：position: absolute;


</body>
</html>