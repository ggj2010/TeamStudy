<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>websocket学习</title>
<script src="http://code.jquery.com/jquery-1.10.2.js">
	
</script>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js">
	
</script>
</head>
<body>
	<script type="text/javascript">
		var ws;
		var wsUri = "ws://localhost:8080/TeamStudy/integration";
		$(function() {
			//$("[data-toggle='popover']").popover();//提示框启用
			//点击一次就等于建立一次连接。 等于后台重新new 了一个WebSocketTest对象。
			$("#toggleConnection").on('click',function() {
				ws = new WebSocket(wsUri);//连接服务器
				ws.onopen = function(event) {
					alert("已经与服务器建立了连接rn当前连接状态：" + this.readyState);
				};
				ws.onmessage = function(event) {
					$("#message").text($("#message").text() + "===" + event.data);
					//alert("服务器回复:\r\n" + event.data);
				};
				ws.onclose = function(event) {
					//alert("已经与服务器断开连接rn当前连接状态："+ this.readyState);
				};
				ws.onerror = function(event) {
					//如果关掉服务器，先报异常，再报服务器断开
					//alert("WebSocket异常！");
				};
			})

			$("#sendData").on('click', function() {
				try {
					ws.send("高广金大帅哥");
				} catch (ex) {
					alert(ex.message);
				}
			})

			$("#showState").on('click', function() {
				try {
					var state = ws.readyState;
					$("#state").text(state);
				} catch (ex) {
					alert(ex.message);
				}
			})

		})
	</script>
</head>
<body>
	<button id='toggleConnection' type="button" class="btn btn-info">连接服务器</button>
	<br />
	<br />
	<button type="button" id='sendData' class="btn btn-info">发送我的名字：高广金</button>

	<div class="panel panel-danger">
		<div class="panel-heading">留言板</div>
		<div class="panel-body" id="message"></div>
	</div>

	<button type="button" id='showState' class="btn btn-info">查看状态</button>
	
	<div class="panel panel-danger">
		<div class="panel-heading">状态</div>
		<div class="panel-body" id="state"></div>
	</div>

</body>
</body>

</html>