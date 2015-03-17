<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %><!-- 如果$el表达式不管用加这个 -->
<% 
	request.setAttribute("path",request.getContextPath());
%>
<!--  
<link rel="stylesheet" type="text/css" href="${path}/themes/css/demo.css">
-->
<script type="text/javascript" src="${path}/youdao/js/require.js" ></script>
<script type="text/javascript" src="${path}/youdao/js/main.js" defer async="true"></script>
