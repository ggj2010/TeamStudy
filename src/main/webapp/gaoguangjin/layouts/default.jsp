<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title/>这是被装饰的头部</title><!--  - Powered By JeeSite -->
	<sitemesh:head/>
</head>
<body>
<ul >
	<li>1</li>
	<li>2</li>
	<li>3</li>
	<li>4</li>
</ul>
<h2>这是被装饰的body部分</h2>
	<sitemesh:body/>
	
	
</body>
</html>