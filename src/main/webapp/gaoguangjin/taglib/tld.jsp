<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="ggj" tagdir="/WEB-INF/tags/"%>

<%@ taglib prefix="mytld" uri="/WEB-INF/tlds/test.tld"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<html>
    <head>
        <title>tld标签学习</title>
    </head>
    <body>
    <mytld:isDisplay name="true">
    	显示,不显示的在下面
    </mytld:isDisplay>
    
    <mytld:isDisplay name="flase">
    	不显示
    </mytld:isDisplay>
    </body>
</html>