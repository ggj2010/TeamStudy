<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>有道云笔记</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<script type="text/javascript">
	
	//其实可以把中文翻译成英文  或者英文翻译成中文放到一个输入框里面，我这边是偷懒，不想写一个切换按钮
	require([ 'jquery', 'bootstrap' ], function($, b) {
		$(function() {
			//登陆框的按钮点击之后
			 $('a').on('click', function () {
				//var transType=$(this).attr("id");
				var transType=$("#type").val();
				var input=$("#inputs").val().trim();
				translation(transType,input);
			})
			
			
			$("#inputs").on("focus",function(){
				$("#output").val("");
			})
			$("button").on("click",function(){
				if($("#type").val()=="zh"){
					$("#type").val("en");
					$("#inputTitle").text("【英文】翻译中文");
					$("#outputTitle").text("中文");
				}else{
					$("#type").val("zh");
					$("#inputTitle").text("中文翻译【英文】");
					$("#outputTitle").text("英文");
				}
			})
		})
		
		
		function translation(transType,input){
			$.ajax({
				type : "get",//get提交中文乱码需要修改tomact里面的 uRIEncoding="utf-8"
				////url : "${path}/youdao/progress.do",
				//dataType : "text",
				//url : "http://fanyi.baidu.com/v2transapi?from=en&query=the&simple_means_flag=3&to=zh&transtype=realtime",
				//dataType : "jsonp",//因为返回类型和jsonp不匹配，所以调用不了success,jsonp是用来解决跨域的
				//url : "https://www.baidu.com/",//jsop跨域调用返回的是 一段可执行的js代码
				//dataType : "jsonp",
				url : "${path}/youdao/gettranslation.do",
				dataType :"json",
				cache:false,//不适用缓存
				data:{from:transType,message:input},
				success: function(data) {  
					$("#output").val(data.dst);
					//这个方法不会被调用，因为如果声明的雷士jsonp而返回的类型不是jsonp，所以调用不了的
		            },
			complete: function(data){
			}
			})
			
			
		}
		
		
		
		
	
		
		
	});
</script>
<style>
.top {
	background: rgba(0, 0, 0, .3);
	border: none;
	top: 0;
	border-width: 0 0 1px;
	position: fixed;
	right: 0;
	left: 0;
	z-index: 1030;
	height: 50px;
}


.btn {
	-moz-user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	cursor: pointer;
	display: inline-block;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.42857;
	margin-bottom: 0;
	padding: 4px 12px;
	text-align: center;
	vertical-align: middle;
	white-space: nowrap;
}
</style>

<body>



	<input type="hidden" id="type" value="zh">

	<div class="top ">
		<div class="container-fluid">
			<div class="pull-left">
				<h3>翻译</h3>
			</div>
			<div class="pull-right visible-lg">
				<h3>这是翻译噢翻译</h3>
			</div>
		</div>
	</div>



	<div class="page-header"></div>
	<!-- 大容器 -->
	<div class="container">
		<!-- row必须要在container里面才能看到效果 -->
		<div class="row">
			<!-- 一行总共有12个单位，比如9+3 -->
			<div class="col-md-6">
				<div class="panel panel-danger">
					<div class="panel-heading ">
							<!-- 使用图标需要将font放在和css同一层目录 -->
						<span id="inputTitle">中文翻译【英文】</span>
						<button  class="btn btn-danger">
							<span class="glyphicon glyphicon-resize-horizontal">转换</span>
						</button>
						
					 
						<a  class="btn btn-info pull-right" id="ch">开始翻译</a>
					</div>
					<div class="panel-body">
						<textarea rows="10" cols="10" class="form-control" id="inputs">
						</textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-danger">
					<div class="panel-heading" >
					<span id="outputTitle"> 英文</span>
					</div>
					<div class="panel-body">
						<textarea rows="10" cols="10" class="form-control" id="output">
						</textarea>
					</div>
				</div>
			</div>
			
			<!--  
			<div class="col-md-7">
				<div class="panel panel-danger">
					<div class="panel-heading ">
						英文翻译成中文 <a  class="btn btn-info pull-right" id="en">开始翻译</a>
					</div>
					<div class="panel-body">
						<textarea rows="10" cols="10" class="form-control" id="enInput">
						</textarea>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="panel panel-danger">
					<div class="panel-heading">中文</div>
					<div class="panel-body">
						<textarea rows="10" cols="10" class="form-control" id="enOutput">
						</textarea>
					</div>
				</div>
			</div>
			-->
		</div>
	</div>
</body>
</html>
