<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'robot.jsp' starting page</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



<script src="${path}/youdao/js/lib/jquery.js">
</script>
<script src="${path}/youdao/js/lib/ajaxfileupload.js">
</script>
<script src="${path}/gaoguangjin/js/jqueryui/ui/jquery.ui.core.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
	<script src="${path}/gaoguangjin/js/jqueryui/ui/jquery.ui.datepicker.js"></script>

<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">

<style>
.title {
	height: 30px;
	width: 100%;
	background
}
</style>
<script type="text/javascript">
	//这里面写jquery事件
	$(function() {
		var processMethod;
	//setInterval(process,500);
		
		$('#ajaxUpload').on('click', function() {
			$("#progressid").css("width","0%");
			$("#process").text("0%");
			$("#progressdiv").css("display","block");
			processMethod=setInterval(process,100);
			$.ajaxFileUpload({
				url : '${path}/youdao/ajaxUpload.do',//用于文件上传的服务器端请求地址
				secureuri : false,//一般设置为false
				fileElementId : 'filess',//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType : 'String',//ajaxFileUpload支持String类型
				async:true,//异步 
				success : function(data) { //服务器成功响应处理函数
				},
				error : function(data, status, e) {//服务器响应失败处理函数
					alert(e);
				}
			})
		});
		
		function process(){
			//$("#process").text(new Date());
			$.ajax({
				type : "post",
				url : "${path}/youdao/progress.do",
				dataType : "json",//不支持String类型可以用text,如果返回值的类型和声明不一致，是不会调用success参数的
				success : function(data) {
					$("#process").text(data.percent);
					$("#progressid").css("width",data.percent);
					if(data.isover==true){
						stopCount();
						 setTimeout(function () {//解决alert占用导致上传成功进度条css还没到100%
							 alert("上传成功"+data.percent); 
							$("#progressdiv").css("display","none");
						    }, 500);
					}
					
				},
				complete: function(data){//AJAX请求完成时隐藏loading提示
				}
			})
		}
		
		//关闭定时任务
	function stopCount() {
			clearInterval(processMethod);
			//clearTimeout()//setTimeout("function",time) 
		}
	})
</script>
</head>
<body>
	<div class="container">
		<!--如果要上传文件必须要加multipart/form-data  -->
		<form action="${path}/youdao/upload.do" enctype="multipart/form-data"
			method="post" class="form-horizontal">
			<div class="row">
				<div class="form-group">
					<label class="col-md-2 control-label">普通form提交</label>
					<div class="col-md-10">
						<input type="file" class="form-control" name="file">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-sm btn-info" type="submit">表单提交</button>
					</div>
				</div>
				
			
				
			</div>
		</form>
		
		<div class="page-header"></div>
		<pre><h>利用ajxa上传，然后看进度条,但是这样写需要一直访问服务器，非常占用资源。或者先计算网速是多少，然后根据文件大小去前台页面让进度条循环</h></pre>
		
		<div class="row form-horizontal">
				<div class="form-group">
					<label class="col-md-2 control-label">ajax上传</label>
					<div class="col-md-10">
						<input type="file" class="form-control" id="filess" name="files">
					</div>
				</div>

			<div class="form-group" id="progressdiv" style="display: none">
				<label class="col-md-2 control-label">上传进度条</label>
				<div class="col-md-8">
				<div  class="progress">
					<!--active是带动画的效果  progress-bar-warning 是颜色-->
					<div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" style="width: 0%;" id="progressid">
					<span id="process">0%</span>
					</div>
				</div>
				</div>
			</div>
			<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button class="btn btn-sm btn-info" id="ajaxUpload">ajax提交</button>
					</div>
			</div>

			<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<img class="img-rounded"
						src="http://m1.sinaimg.cn/maxwidth.2880/m1.sinaimg.cn/e79e5e1a817c4819cbc3f34eaecca7dc_301_294.jpg"
						alt="图片内容缩略图">
					<div class="caption">
						<h3>这是一个傻狗</h3>
						<p>这是傻狗照片</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">查看</a> <a
								href="#" class="btn btn-default" role="button">删除</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<img class="img-rounded"
						src="http://img4.duitang.com/uploads/item/201308/09/20130809115652_GhiQE.thumb.700_0.jpeg"
						alt="图片内容缩略图">
					<div class="caption">
						<h3>这是一个傻狗</h3>
						<p>这是傻狗照片</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">查看</a> <a
								href="#" class="btn btn-default" role="button">删除</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img class="img-rounded"
						src="http://cdn.duitang.com/uploads/item/201410/19/20141019074135_uXnk5.thumb.700_0.jpeg"
						alt="图片内容缩略图">
					<div class="caption">
						<h3>这是一个傻狗</h3>
						<p>这是傻狗照片</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">查看</a> <a
								href="#" class="btn btn-default" role="button">删除</a>
						</p>
					</div>
				</div>
			</div>


		</div>
	</div>

</body>
</html>
