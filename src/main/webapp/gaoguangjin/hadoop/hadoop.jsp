<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'robot.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

<!-- Bootstrap core CSS -->
<link href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Optional Bootstrap Theme -->
  <link href="data:text/css;charset=utf-8," data-href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap-theme.min.css" rel="stylesheet" id="bs-theme-stylesheet">



<link href="http://v3.bootcss.com/assets/css/patch.css" rel="stylesheet">

<!-- Documentation extras -->
<link href="http://v3.bootcss.com/assets/css/docs.min.css" rel="stylesheet">

<!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
  <script src="http://v3.bootcss.com/assets/js/ie-emulation-modes-warning.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
  <script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

  <script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>

<style>
.rightpage {
	float: left;
	height: 100%;
	width: 20%;
	border: 1px solid pink;
}

.leftpage {
	margin-left: 2%;
	float: left;
	height: 100%;
	width: 78%;
	overflow-x: hidden;
}

.classContentStyle {
	border: 0px solid grey;
	height: 100%;
	width: 100%;
}

#content {
	color: black;
}
</style>

<script type="text/javascript">
	$(function() {
		$("li > a").on("click",function() {
					var packageName = $(this).parent("li").parent("ul").prop("id");
					var className = $(this).text();
					$(".classContentStyle").attr("src","${path}/ggj/hadoop?className=" + packageName+"/"+className);
				})
			//给ifram赋值
		$(".btn").on('click', function() {
			//$(this).attr("aa","bb");可以复制
			//$(this).prop("cc","dd");不能赋值
			//alert($(this).attr("value"));
			//alert($(this).prop('value'));只获取值
			$("#ifrmepage").attr("src", $(this).prop("value"));
		})
		
		//初始化弹出框 popover.js
		$('[data-toggle="popover"]').popover();

		function getData() {
			var className = $("a").text();
			$.ajax({
				type : "get",//使用get方法访问后台
				dataType : "text",////jquery调用servlert ajax返回json格式的数据.
				url : "${path}/ggj/hadoop",//要访问的后台地址
				data : "className=" + className,//要发送的数据
				success : function(data) {//msg为返回的数据，在这里做数据绑定
				},
				complete : function() {//AJAX请求完成时隐藏loading提示
				}
			})
		}
	})
</script>
</head>
<body>
	<span id="text"></span>
	<div>
		<div class="rightpage">
			<h4>WordCount的代码</h4>
			<ul id="worldcount">
				<li><a>WorldCountNew.java</a></li>
				<li><a>WordCountOld.java<a></li>
				<li>Duplicate.java</li>
			</ul>
			<h4>WordCount的代码</h4>
			<ul id="worldcount">
				<li><a>WorldCountNew.java</a></li>
				<li>wordwcount</li>
				<li>wordwcount1</li>
			</ul>
			<h4>WordCount的代码</h4>
			<ul>
				<li><a>WorldCountNew.java</a></li>
				<li>wordwcount</li>
				<li>wordwcount1</li>
			</ul>
		</div>
		<!-- button -->
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal"
			value="http://123.56.118.135:50070/dfshealth.jsp">DFS主页面</button>
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal"
			value="http://123.56.118.135:50070/nn_browsedfscontent.jsp">HDFS
			文件仓库</button>
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal" value="http://123.56.118.135:50030/jobtracker.jsp">JobTracker</button>
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal" value="http://123.56.118.135:50075/browseDirectory.jsp?dir=%2Fhbase&namenodeInfoPort=50070">Habase</button>

		<a tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="focus" title="masterip绑定" data-content="C:\Windows\System32\drivers\etc  目录下面修改hosts文件 添加 123.56.118.135  master">配置maste</a>

		<!-- iframe -->
		<div class="leftpage">
			<iframe class="classContentStyle" src=""> </iframe>
		</div>
		
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">hadoop监控</h4>
				</div>
				<div class="modal-body">
					<iframe id="ifrmepage" width="100%" height="100%" frameborder="0"
						src=""></iframe>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
