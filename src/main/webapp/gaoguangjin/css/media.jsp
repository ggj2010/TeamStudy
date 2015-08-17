<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>使用@media 属性</title>
<style type="text/css">

span {
	color: red;
}

@media screen and (max-width:768px)  {
	span {
		color: grey;
	}
}



</style>
</head>
<body>

	<div>
		<span>使用@media 属性</span>
	</div>
</body>
</html>