<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/function.tld"%>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<html>
    <head>
        <title>functiontld标签学习</title>
    </head>
    <body>
    <pre>
     这是自定义tld标签，调用后台java的static方法：
   返回值为:${fns:getLoginUser()}
    </pre>
  
    </body>
</html>