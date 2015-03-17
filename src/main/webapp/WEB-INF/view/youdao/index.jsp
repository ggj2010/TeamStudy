<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp" %>
<%@ include file="/youdao/common/taglibs.jsp" %>
<html>
  <head>
    <title>有道云笔记</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <script type="text/javascript">
	
  require(['jquery'], function($) {
  	   $(function() {
  		   $("#init").text("欢迎登陆到主界面！！！！！！！！！！！！！"); 
  	   })
  	   alert($().jquery);
   
  });
  </script>
  
  <body>
  	欢迎登陆到主界面！
  	<span id="init"></span>
  </body>
</html>
