<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 如果$el表达式不管用加这个 %@ page isELIgnored="false" % -->
<% 
	request.setAttribute("path",request.getContextPath());
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 国产浏览器告诉模式 -->
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css"href="${path}/youdao/themes/bootstrap.css">
<script type="text/javascript" src="${path}/youdao/js/require.js"></script>
<script type="text/javascript" src="${path}/youdao/js/main.js" defer async="true"></script>

<!-- js让requirejs去管理 -->

<!-- 新 Bootstrap 核心 CSS 文件 
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
-->
<!-- 可选的Bootstrap主题文件（一般不用引入）
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
 -->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
-->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
-->