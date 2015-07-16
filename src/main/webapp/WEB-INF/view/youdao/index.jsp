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
	require([ 'jquery', 'bootstrap' ], function($, b) {
		$(function() {
			$(".daohang > ul > li > a").on("click", function() {
				$(".daohang .active").removeClass("active");
				$(this).parent("li").prop("class", "active");
			})

			for ( var i = 0; i < 100; i++) {
				//$("#test").append("d\n");
			}
			$("[data-toggle='popover']").popover();//提示框启用
			$('[data-toggle="tooltip"]').tooltip();//提示框启用

			//登陆框的按钮点击之后
			$('#myButtons').on('click', function() {
				var $btn = $(this).button('loading');
				setTimeout(function() {
					$btn.button('reset');
				}, 1000);
			})

		})
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

/*fixed标签会在网站变小时候消失掉*/
.daohang {
	margin-top: 10px;
	font-size: 12px;
	font-weight: 400;
	width: 263px;
	position: fixed;
	padding-top: 10px;
	padding-bottom: 10px;
	text-shadow: 0 1px 0 #fff;
	background-color: #f7f5fa;
	border-radius: 5px;
}

/**
a:link {color: #FF0000}		 未访问的链接 
a:visited {color: blue}	 已访问的链接 
a:hover {color: red}	鼠标移动到链接上 
a:active {color: #0000FF}
a:focus{color:pink}
**/
.daohang .nav {
	display: none;
	padding-bottom: 10px;
}

.daohang .nav {
	display: none;
	padding-bottom: 10px;
}

.daohang .active .nav {
	display: block;
}

/*输入框的背景*/
.bs-example {
	margin-right: 0;
	margin-left: 0;
	background-color: #fff;
	border-color: #ddd;
	border-width: 1px;
	border-radius: 4px 4px 0 0;
	-webkit-box-shadow: none;
	box-shadow: none;
}

/**输入框聚焦时候的映射*/
#focusedInput {
	border-color: rgba(82, 168, 236, .8);
	outline: 0;
	-webkit-box-shadow: 0 0 8px rgba(82, 168, 236, .6);
	box-shadow: 0 0 8px rgba(82, 168, 236, .6);
}

.scrollspy-example {
	position: relative;
	height: 200px;
	margin-top: 10px;
	overflow: auto;
}
</style>

<body>
	<div class="top">
		<div class="container-fluid">
			<div>
				<h3>bootstrap学习</h3>
			</div>
		</div>
	</div>


	<div class="container-fluid">
		<pre>
					container-fluid 类用于 100% 宽度，占据全部视口（viewport）的容器。流式布局容器
					</pre>
	</div>
	<div class="container" id="header-1">
		<!-- page-header是用来控制下划线的 -->
		<h4 class="page-header">标题</h4>
		<pre>
						container类用于固定宽度并支持响应式布局的容器
					</pre>
	</div>




	<!-- 大容器 -->
	<div class="container">
		<!-- row必须要在container里面才能看到效果 -->
		<div class="row">
			<!-- 一行总共有12个单位，比如9+3 -->
			<div class="col-md-9">
				<!-- 栅格总共有12个单位 -->
				<div class="container">
					<div class="row" id="col-66">
						<div class="col-md-6">
							<pre>col-md-6</pre>
						</div>
						<div class="col-md-6">
							<pre>col-md-6</pre>
						</div>
					</div>
					<div id="col-237" class="row">
						<div class="col-md-2">
							<pre>col-md-2</pre>
						</div>
						<div class="col-md-3">
							<pre>col-md-3</pre>
						</div>
						<div class="col-md-7">
							<pre>col-md-7</pre>
						</div>
					</div>
				</div>

				<div id="ppp">
					<p>这是文本噢p标签Bootstrap 将全局 font-size 设置为 14px，line-height 设置为
						1.428。这些属性直接赋予 body> 元素和所有段落元素。另外，p> （段落）元素还被设置了等于 1/2 行高（即
						10px）的底部外边距（margin）。</p>
					<p>这是文本噢p标签Bootstrap 将全局 font-size 设置为 14px，line-height 设置为
						1.428。这些属性直接赋予 body> 元素和所有段落元素。另外，p> （段落）元素还被设置了等于 1/2 行高（即
						10px）的底部外边距（margin）。</p>
					<p>这是文本噢p标签Bootstrap 将全局 font-size 设置为 14px，line-height 设置为
						1.428。这些属性直接赋予 body> 元素和所有段落元素。另外，p> （段落）元素还被设置了等于 1/2 行高（即
						10px）的底部外边距（margin）。</p>

				</div>

				<div>
					<pre>这是<mark>【高亮】《mark》</mark>标签</pre>
				</div>
				<div>
					<pre>这是<del>【删除】《del》</del>标签</pre>
				</div>
				<div>
					<pre>
						<s>这是【无用的文本】 用《s》标签</s>
					</pre>
				</div>

				<div>
					<pre>
						<u>这是带【下划线】用《u》标签</u>
					</pre>
				</div>
				<div>
					<pre>
						<small>这是带【小号文本】用《small》标签</small>
					</pre>
				</div>
				<div>
					<pre>
						<strong>这是带【着重】用《strong》标签</strong>
					</pre>
				</div>
				<div>
					<pre>
						<em>这是带【斜体】用《em》标签</em>
					</pre>
				</div>

				<div>
					<pre>改变大小写
					<p class="text-lowercase" title="原来值Lowercased text.">Lowercased text.《text-lowercase》</p>
					<p class="text-uppercase" title="原来值Uppercased text.">Uppercased text.《text-uppercase》</p>
					<p class="text-capitalize" title="原来值Capitalized text">Capitalized text.《text-capitalize》</p>
					</pre>
				</div>
				<div id="address">
					<address>
						<strong>Twitter, Inc.</strong><br> 795 Folsom Ave, Suite 600<br>
						San Francisco, CA 94107<br> <abbr title="Phone">P:</abbr>
						(123) 456-7890
					</address>

					<address>
						<strong>Full Name</strong><br> <a href="mailto:#">first.last@example.com</a>
					</address>
				</div>

				<div id="duiqi">
					<pre>
					<p class="text-left">《text-left》 p标签对其方式</p>
					<p class="text-right">《text-right》 p标签对其方式</p>
					<p class="text-center">《text-center》 p标签对其方式</p>
					<p class="text-justfy">《text-justfy》 p标签对其方式</p>
					</pre>
				</div>

				<div id="blockquote">
					<!-- 右对齐方式blockquote-reverse -->
					<blockquote class="blockquote-reverse">
						<p>《blockquote》引用标签</p>
						<!-- footer是脚标签 -->
						<footer>高广金</footer>
					</blockquote>
				</div>

				<div id="liebiao">
					<!-- <ul class="list-inline">无序ul列表list-inline会让无序列表变成一行内联 -->
					<ul>
						无序ul列表list-inline会让无序列表变成一行内联
						<li>字段1</li>
						<li>字段2</li>
						<li>字段3</li>
					</ul>
					<div class="page-header"></div>
					<ol>
						有序ol列表
						<li>字段1</li>
						<li>字段2</li>
						<li>字段3</li>
					</ol>

					<div class="page-header"></div>
					<dl>
						<dt>【1】小标题《dt》</dt>
						<dd>内容《dd》 带描述的短语列表</dd>
						<dt>【2】小标题《dt》</dt>
						<dd>内容《dd》</dd>
					</dl>
					<div class="page-header"></div>
					<dl class="dl-horizontal">
						<dt>【1】小标题《dt》</dt>
						<dd>内容《dd》 带描述的短语列表 class="dl-horizontal" 水平排列</dd>
						<dt>【2】小标题《dt》</dt>
						<dd>内容《dd》dddddddddddddddddddddddddddddddddddddddddddddddd</dd>
					</dl>
				</div>

				<div class="page-header"></div>
				<div id="code">
					<p>
						比如这是代码《code》：
						<code>这是&lt;span&gt;dddd&lt;span&gt;</code>
					</p>
				</div>

				<div class="page-header"></div>
				<div>
					<p>
						用户输入《kbd》
						<kbd>crtl+</kbd>
					</p>
				</div>

				<div id="table">
					<table class="table" title="class=table">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
					</table>

					<span style="color: red"> ------------------条纹状表格 table
						table-striped每一行增加斑马条纹样式。---------------- </span>
					<table class="table table-striped" title="class=table-striped">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
					</table>

					<span style="color: red"> ------------------带边框的表格
						.table-bordered单元格增加边框。---------------- </span>
					<table class="table table-bordered" title="class=table-bordered">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
					</table>
					<span style="color: red"> ------------------鼠标悬停的表格
						.table-hover---------------- </span>
					<table class="table table-hover" title="class=table-hover">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
					</table>
					<dl class="dl-horizontal">
						<dt>Class</dt>
						<dd>描述</dd>
						<dt>.active</dt>
						<dd>鼠标悬停在行或单元格上时所设置的颜色</dd>
						<dt>.success</dt>
						<dd>标识成功或积极的动作</dd>
						<dt>.info</dt>
						<dd>标识普通的提示信息或动作</dd>
						<dt>.warning</dt>
						<dd>标识警告或需要用户注意</dd>
						<dt>.danger</dt>
						<dd>标识危险或潜在的带来负面影响的动作</dd>
					</dl>


					<span style="color: red">
						------------------表格列的颜色---------------- </span>
					<table class="table table-hover">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr class="active">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr class="success">
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
						<tr class="warning">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr class="danger">
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
						<tr class="info">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
					</table>
				</div>
				<span style="color: red"> ------------------在table前面增加一个div
					class=table-responsive,这样当其会在小屏幕设备上（小于768px）水平滚动。当屏幕大于 768px
					宽度时，水平滚动条消失。-------------- </span>
				<div class="table-responsive" title="class=table-responsive">
					<table class="table" title="class=table-responsive">
						<tr>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
						</tr>
						<tr class="active">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr class="success">
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
						<tr class="warning">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
						<tr class="danger">
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
							<td>2</td>
							<td>张静月</td>
							<td>女</td>
							<td>20</td>
						</tr>
						<tr class="info">
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
							<td>1</td>
							<td>高广金</td>
							<td>男</td>
							<td>23</td>
						</tr>
					</table>
				</div>

				<div class="page-header"></div>

				<div id="form" class="bs-example">
					<!-- margin-bottom: 15px; -->
					<div class="form-group">
						<p>
							<code>所有设置了 .form-control
								类的&lt;input&gt;&lt;textarea&gt;&lt;select&gt;</code>
							默认设置宽度是
							<code>&lt;width:100%&gt;</code>
						</p>
					</div>
					<div class="form-group">
						<input class="form-control" type="text" placeholder="输入框">
					</div>
					<div class="checkbox">
						<input type="radio"><label>单选按钮</label>
					</div>
					<div class="checkbox">
						<input type="checkbox"><label>复选框</label>
					</div>
				</div>

				<div class="page-header"></div>

				<div class="form-inline">
					<div class="form-group">
						<label>form-inline内联输入框</label> <input class="form-control"
							type="text" placeholder="内联输入框form-inline">
					</div>
					<div class="form-group">
						<label>form-inline内联输入框</label> <input class="form-control"
							type="text" placeholder="输入框form-inline">
					</div>
				</div>

				<!--下拉框btn-group是必须要的  -->
				<div class="btn-group" id="drop">
					<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"
						aria-expanded="false">
						下拉框 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">按钮1</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<!--分割线 -->
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
					将下拉菜单触发器和下拉菜单都包裹在 .dropdown 里，或者另一个声明了 position: relative;
					的元素。然后加入组成菜单的 HTML 代码。为下拉菜单的父元素添加 .dropup 类还可以让菜单向上弹出（默认是向下弹出）。
				</div>
				<div class="page-header"></div>

				<div class="row">
					<div class="col-md-2">
						<input type="text" class="form-control" placeholder="栅栏式">
					</div>
					<div class="col-md-10">
						<input type="text" class="form-control" placeholder="输入框">
					</div>

					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control" placeholder="输入框">
					</div>

					<div>
						<input type="text" class="form-control" id="focusedInput"
							placeholder="聚焦之后的效果">
					</div>
				</div>

				<div class="page-header"></div>
				<div id="button">
					<div class="form-group">
						<button class="btn btn-primary active">active按钮</button>
						<button class="btn btn-primary">正常按钮</button>
						<button class="btn btn-primary" disabled>disable按钮</button>
					</div>
				</div>
				<div class="page-header"></div>
				<div>
					<select class="form-control">
						<option>条件1</option>
						<option>条件2</option>
						<option>条件3</option>
						<option>条件4</option>
					</select>
					<div class="page-header"></div>
					<select multiple class="form-control">
						<option>条件1 multiple 显示多行</option>
						<option>条件2 multiple</option>
						<option>条件3</option>
						<option>条件4</option>
					</select>
				</div>
				<div class="page-header"></div>

				<div id="nav">
					<!--navbar 和颜色   navbar-fixed-top在顶部-->
					<div class="navbar navbar-inverse ">
						<div class="container">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed"
									data-toggle="collapse" data-target="#title">
									<span class="sr-only">屏幕缩小的按钮</span> <span class="icon-bar"></span>
									<span class="icon-bar"></span> <span class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="#">本地</a>
							</div>
							<div id="title" class="collapse navbar-collapse">
								<ul class="nav navbar-nav">
									<li class="active"><a href="#">Home</a></li>
									<li><a href="#about">About</a></li>
									<li><a href="#contact">Contact</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="page-header"></div>

				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">Profile</a></li>
					<li role="presentation"><a href="#">Messages</a></li>
				</ul>


				<div id="message">
					<a href="message">个人消息 <span class="badge">42</span></a>
					<button class="btn btn-danger" type="button">
						离线<span class="badge">4</span>
					</button>
				</div>
				<div class="page-header"></div>


				<div class="row" id="image">
					<div class="col-sm-6 col-md-4">
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
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img class="img-circle"
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
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img class="img-thumbnail"
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
				</div>


				<div class="page-header"></div>
				<div id="close">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>提醒!</strong>这个页面可以关闭的！
					</div>
				</div>


				<div class="alert alert-success" role="alert">123</div>

				<div class="page-header"></div>

				<div class="container">
					<button type="button" class="btn btn-default" title="Popover title"
						data-container="body" data-toggle="popover" data-placement="left"
						data-content="左侧的 Popover 中的一些内容">左侧的 Popover</button>
					<button type="button" class="btn btn-primary" title="Popover title"
						data-container="body" data-toggle="popover" data-placement="top"
						data-content="顶部的 Popover 中的一些内容">顶部的 Popover</button>
					<button type="button" class="btn btn-success" title="Popover title"
						data-container="body" data-toggle="popover"
						data-placement="bottom" data-content="底部的 Popover 中的一些内容">
						底部的 Popover</button>
					<button type="button" class="btn btn-warning" title="Popover title"
						data-container="body" data-toggle="popover" data-placement="right"
						data-content="右侧的 Popover 中的一些内容">右侧的 Popover</button>
				</div>

				<div class="page-header"></div>
				<div id="process" class="progress">
					<div class="progress-bar" role="progressbar" aria-valuenow="60"
						aria-valuemin="0" ari-valuemax="100" style="width: 60%;">
						60%</div>
				</div>
				<div class="form-group">
					<label>请选择文件：</label> <input type="file" class="form-control"
						id="uploadFile">
				</div>

				<div id="listgroup">
					<ul class="list-group">
						<li class="list-group-item">listgroup列表组</li>
						<li class="list-group-item">《list-group-item》</li>
						<li class="list-group-item">未读消息<span class="badge">3333</span></li>
					</ul>
				</div>

				<div id="list-group">
					<a class="list-group-item active" href="#">这是连接的列表组</a> <a
						class="list-group-item" href="#">这是连接的列表组</a> <a
						class="list-group-item" href="#">这是连接的列表组</a>
				</div>

				<div class="page-header"></div>


				<div class="list-group">
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">List group 表头</h4>
						<p class="list-group-item-text">内容内容</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">不是激活状态</h4>
						<p class="list-group-item-text">内容内容</p>
					</a>
				</div>

				<div class="page-header"></div>

				<div class="panel panel-danger" id="panel">
					<div class="panel-heading">《panel-heading》</div>
					<div class="panel-body">这是文字《panel-body》</div>
				</div>

				<div class="panel panel-danger" id="panelfull">
					<div class="panel-heading">好好学习</div>
					<!--如果去除panel-body  panel-heading就和table在一起了  	-->
					<div class="panel-body">
						<p>好好学习天天向上好好学习天天向上好好学习天天向上,好好学习天天向上好好学习天天向上好好学习天天向上,好好学习天天向上好好学习天天向上好好学习,如果去除panel-body
							panel-heading就和table在一起了 。</p>
					</div>

					<table class="table table-hover">
						<tr class="info">
							<th>标题1</th>
							<th>标题2</th>
							<th>标题2</th>
						</tr>
						<tr>
							<td>1</td>
							<td>2</td>
							<td>3</td>
						</tr>
					</table>
				</div>


				<div class="page-header"></div>

				<!-- 4:3 aspect ratio -->
				<div class="embed-responsive embed-responsive-4by3" id="iframes">
					<iframe class="embed-responsive-item" src="http://www.baidu.com/"></iframe>
				</div>
				<div class="page-header"></div>

				<div id="dialog">
					<button type="button" class="btn btn-primary btn-lg"
						data-toggle="modal" data-target="#myModal">启动对话框</button>
					<div id="myModal" class="modal fade in" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="false">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										标题<a class="anchorjs-link" href="#myModalLabel"><span
											class="anchorjs-icon"></span></a>
									</h4>
								</div>
								<div class="modal-body">
									<h4 id="text-in-a-modal">
										Text in a modal<a class="anchorjs-link"
											href="#text-in-a-modal"><span class="anchorjs-icon"></span></a>
									</h4>
									<p>Duis mollis, est non commodo luctus, nisi erat porttitor
										ligula.</p>

									<h4 id="popover-in-a-modal">
										Popover in a modal<a class="anchorjs-link"
											href="#popover-in-a-modal"><span class="anchorjs-icon"></span></a>
									</h4>
									<p>
										This <a href="#" role="button"
											class="btn btn-default popover-test" title=""
											data-content="And here's some amazing content. It's very engaging. right?"
											data-original-title="A Title">button</a> should trigger a
										popover on click.
									</p>

									<h4 id="tooltips-in-a-modal">
										Tooltips in a modal<a class="anchorjs-link"
											href="#tooltips-in-a-modal"><span class="anchorjs-icon"></span></a>
									</h4>
									<p>
										<a href="#" class="tooltip-test" title=""
											data-original-title="Tooltip">This link</a> and <a href="#"
											class="tooltip-test" title="" data-original-title="Tooltip">that
											link</a> should have tooltips on hover.
									</p>

									<hr>

									<h4 id="overflowing-text-to-show-scroll-behavior">
										Overflowing text to show scroll behavior<a
											class="anchorjs-link"
											href="#overflowing-text-to-show-scroll-behavior"><span
											class="anchorjs-icon"></span></a>
									</h4>
									<p>Cras mattis consectetur purus sit amet fermentum. Cras
										justo odio, dapibus ac facilisis in, egestas eget quam. Morbi
										leo risus, porta ac consectetur ac, vestibulum at eros.</p>
									<p>Praesent commodo cursus magna, vel scelerisque nisl
										consectetur et. Vivamus sagittis lacus vel augue laoreet
										rutrum faucibus dolor auctor.</p>
									<p>Aenean lacinia bibendum nulla sed consectetur. Praesent
										commodo cursus magna, vel scelerisque nisl consectetur et.
										Donec sed odio dui. Donec ullamcorper nulla non metus auctor
										fringilla.</p>
									<p>Cras mattis consectetur purus sit amet fermentum. Cras
										justo odio, dapibus ac facilisis in, egestas eget quam. Morbi
										leo risus, porta ac consectetur ac, vestibulum at eros.</p>
									<p>Praesent commodo cursus magna, vel scelerisque nisl
										consectetur et. Vivamus sagittis lacus vel augue laoreet
										rutrum faucibus dolor auctor.</p>
									<p>Aenean lacinia bibendum nulla sed consectetur. Praesent
										commodo cursus magna, vel scelerisque nisl consectetur et.
										Donec sed odio dui. Donec ullamcorper nulla non metus auctor
										fringilla.</p>
									<p>Cras mattis consectetur purus sit amet fermentum. Cras
										justo odio, dapibus ac facilisis in, egestas eget quam. Morbi
										leo risus, porta ac consectetur ac, vestibulum at eros.</p>
									<p>Praesent commodo cursus magna, vel scelerisque nisl
										consectetur et. Vivamus sagittis lacus vel augue laoreet
										rutrum faucibus dolor auctor.</p>
									<p>Aenean lacinia bibendum nulla sed consectetur. Praesent
										commodo cursus magna, vel scelerisque nisl consectetur et.
										Donec sed odio dui. Donec ullamcorper nulla non metus auctor
										fringilla.</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
				</div>

				<div class="page-header"></div>

				<nav id="navbar-example" class="navbar navbar-default navbar-static">
					<div class="container-fluid">
						<div class="navbar-header">
							<!-- 这个是空值浏览器宽度变小的是当行条的变化 -->
							<button class="navbar-toggle collapsed" type="button"
								data-toggle="collapse"
								data-target=".bs-example-js-navbar-collapse">
								<span class="sr-only">dddddd</span> <span class="icon-bar"></span>
								<span class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
								<!-- 有多少个自导航就写多少个横线的span -->
							</button>
							<a class="navbar-brand" href="#">主导航栏目</a>
						</div>
						<div
							class="collapse navbar-collapse bs-example-js-navbar-collapse">
							<ul class="nav navbar-nav">
								<li class="dropdown"><a id="drop1" href="#"
									class="dropdown-toggle" data-toggle="dropdown"
									aria-haspopup="true" role="button" aria-expanded="false">
										导航栏1 <span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Another action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Something else here</a></li>
										<li role="presentation" class="divider"></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Separated link</a></li>
									</ul></li>
								<li class="dropdown"><a id="drop2" href="#"
									class="dropdown-toggle" data-toggle="dropdown"
									aria-haspopup="true" role="button" aria-expanded="false">
										导航栏2 <span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="drop2">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Another action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Something else here</a></li>
										<li role="presentation" class="divider"></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Separated link</a></li>
									</ul></li>
							</ul>
							<ul class="nav navbar-nav navbar-right">
								<li id="fat-menu" class="dropdown"><a id="drop3" href="#"
									class="dropdown-toggle" data-toggle="dropdown"
									aria-haspopup="true" role="button" aria-expanded="false">
										导航栏3 <span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Another action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Something else here</a></li>
										<li role="presentation" class="divider"></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="https://twitter.com/fat">Separated link</a></li>
									</ul></li>
							</ul>
						</div>
						<!-- /.nav-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>

				<div class="page-header"></div>

				<!-- 滚动监听 -->
				<div class="bs-example" data-example-id="embedded-scrollspy">
					<nav id="navbar-example2"
						class="navbar navbar-default navbar-static">
						<div class="container-fluid">
							<div class="navbar-header">
								<button class="navbar-toggle collapsed" type="button"
									data-toggle="collapse"
									data-target=".bs-example-js-navbar-scrollspy">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="#">Project Name</a>
							</div>
							<div
								class="collapse navbar-collapse bs-example-js-navbar-scrollspy">
								<ul class="nav navbar-nav">
									<li class="active"><a href="#fat">@fat</a></li>
									<li class=""><a href="#mdo">@mdo</a></li>
									<li class="dropdown"><a href="#" id="navbarDrop1"
										class="dropdown-toggle" data-toggle="dropdown" role="button"
										aria-expanded="false">Dropdown <span class="caret"></span></a>
										<ul class="dropdown-menu" role="menu"
											aria-labelledby="navbarDrop1">
											<li class=""><a href="#one" tabindex="-1">one</a></li>
											<li class=""><a href="#two" tabindex="-1">two</a></li>
											<li class="divider"></li>
											<li class=""><a href="#three" tabindex="-1">three</a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</nav>
					<div data-spy="scroll" data-target="#navbar-example2"
						data-offset="0" class="scrollspy-example">
						<h4 id="fat">
							@fat<a class="anchorjs-link" href="#fat"><span
								class="anchorjs-icon"></span></a>
						</h4>
						<p>Ad leggings keytar, brunch id art party dolor labore.
							Pitchfork yr enim lo-fi before they sold out qui. Tumblr
							farm-to-table bicycle rights whatever. Anim keffiyeh carles
							cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure.
							Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you
							probably haven't heard of them et cardigan trust fund culpa
							biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred
							irony biodiesel keffiyeh artisan ullamco consequat.</p>
						<h4 id="mdo">
							@mdo<a class="anchorjs-link" href="#mdo"><span
								class="anchorjs-icon"></span></a>
						</h4>
						<p>Veniam marfa mustache skateboard, adipisicing fugiat velit
							pitchfork beard. Freegan beard aliqua cupidatat mcsweeney's vero.
							Cupidatat four loko nisi, ea helvetica nulla carles. Tattooed
							cosby sweater food truck, mcsweeney's quis non freegan vinyl.
							Lo-fi wes anderson +1 sartorial. Carles non aesthetic
							exercitation quis gentrify. Brooklyn adipisicing craft beer vice
							keytar deserunt.</p>
						<h4 id="one">
							one<a class="anchorjs-link" href="#one"><span
								class="anchorjs-icon"></span></a>
						</h4>
						<p>Occaecat commodo aliqua delectus. Fap craft beer deserunt
							skateboard ea. Lomo bicycle rights adipisicing banh mi, velit ea
							sunt next level locavore single-origin coffee in magna veniam.
							High life id vinyl, echo park consequat quis aliquip banh mi
							pitchfork. Vero VHS est adipisicing. Consectetur nisi DIY minim
							messenger bag. Cred ex in, sustainable delectus consectetur fanny
							pack iphone.</p>
						<h4 id="two">
							two<a class="anchorjs-link" href="#two"><span
								class="anchorjs-icon"></span></a>
						</h4>
						<p>In incididunt echo park, officia deserunt mcsweeney's
							proident master cleanse thundercats sapiente veniam. Excepteur
							VHS elit, proident shoreditch +1 biodiesel laborum craft beer.
							Single-origin coffee wayfarers irure four loko, cupidatat terry
							richardson master cleanse. Assumenda you probably haven't heard
							of them art party fanny pack, tattooed nulla cardigan tempor ad.
							Proident wolf nesciunt sartorial keffiyeh eu banh mi sustainable.
							Elit wolf voluptate, lo-fi ea portland before they sold out four
							loko. Locavore enim nostrud mlkshk brooklyn nesciunt.</p>
						<h4 id="three">
							three<a class="anchorjs-link" href="#three"><span
								class="anchorjs-icon"></span></a>
						</h4>
						<p>Ad leggings keytar, brunch id art party dolor labore.
							Pitchfork yr enim lo-fi before they sold out qui. Tumblr
							farm-to-table bicycle rights whatever. Anim keffiyeh carles
							cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure.
							Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you
							probably haven't heard of them et cardigan trust fund culpa
							biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred
							irony biodiesel keffiyeh artisan ullamco consequat.</p>
						<p>Keytar twee blog, culpa messenger bag marfa whatever
							delectus food truck. Sapiente synth id assumenda. Locavore sed
							helvetica cliche irony, thundercats you probably haven't heard of
							them consequat hoodie gluten-free lo-fi fap aliquip. Labore elit
							placeat before they sold out, terry richardson proident brunch
							nesciunt quis cosby sweater pariatur keffiyeh ut helvetica
							artisan. Cardigan craft beer seitan readymade velit. VHS chambray
							laboris tempor veniam. Anim mollit minim commodo ullamco
							thundercats.</p>
					</div>
				</div>



				<div class="bs-example bs-example-tabs" role="tabpanel"
					data-example-id="togglable-tabs">
					<ul id="myTab" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#home"
							id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
							aria-expanded="true">Home</a></li>
						<li role="presentation" class=""><a href="#profile"
							role="tab" id="profile-tab" data-toggle="tab"
							aria-controls="profile" aria-expanded="false">Profile</a></li>
						<li role="presentation" class="dropdown"><a href="#"
							id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown"
							aria-controls="myTabDrop1-contents" aria-expanded="false">Dropdown
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="myTabDrop1" id="myTabDrop1-contents">
								<li class=""><a href="#dropdown1" tabindex="-1" role="tab"
									id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1"
									aria-expanded="false">@fat</a></li>
								<li><a href="#dropdown2" tabindex="-1" role="tab"
									id="dropdown2-tab" data-toggle="tab" aria-controls="dropdown2"
									aria-expanded="false">@mdo</a></li>
							</ul></li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div role="tabpanel" class="tab-pane fade active in" id="home"
							aria-labelledby="home-tab">
							<p>Raw denim you probably haven't heard of them jean shorts
								Austin. Nesciunt tofu stumptown aliqua, retro synth master
								cleanse. Mustache cliche tempor, williamsburg carles vegan
								helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher
								synth. Cosby sweater eu banh mi, qui irure terry richardson ex
								squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis
								cardigan american apparel, butcher voluptate nisi qui.</p>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="profile"
							aria-labelledby="profile-tab">
							<p>Food truck fixie locavore, accusamus mcsweeney's marfa
								nulla single-origin coffee squid. Exercitation +1 labore velit,
								blog sartorial PBR leggings next level wes anderson artisan four
								loko farm-to-table craft beer twee. Qui photo booth letterpress,
								commodo enim craft beer mlkshk aliquip jean shorts ullamco ad
								vinyl cillum PBR. Homo nostrud organic, assumenda labore
								aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr,
								vero magna velit sapiente labore stumptown. Vegan fanny pack
								odio cillum wes anderson 8-bit, sustainable jean shorts beard ut
								DIY ethical culpa terry richardson biodiesel. Art party
								scenester stumptown, tumblr butcher vero sint qui sapiente
								accusamus tattooed echo park.</p>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="dropdown1"
							aria-labelledby="dropdown1-tab">
							<p>Etsy mixtape wayfarers, ethical wes anderson tofu before
								they sold out mcsweeney's organic lomo retro fanny pack lo-fi
								farm-to-table readymade. Messenger bag gentrify pitchfork
								tattooed craft beer, iphone skateboard locavore carles etsy
								salvia banksy hoodie helvetica. DIY synth PBR banksy irony.
								Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh
								mi whatever gluten-free, carles pitchfork biodiesel fixie etsy
								retro mlkshk vice blog. Scenester cred you probably haven't
								heard of them, vinyl craft beer blog stumptown. Pitchfork
								sustainable tofu synth chambray yr.</p>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="dropdown2"
							aria-labelledby="dropdown2-tab">
							<p>Trust fund seitan letterpress, keytar raw denim keffiyeh
								etsy art party before they sold out master cleanse gluten-free
								squid scenester freegan cosby sweater. Fanny pack portland
								seitan DIY, art party locavore wolf cliche high life echo park
								Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they
								sold out farm-to-table VHS viral locavore cosby sweater. Lomo
								wolf viral, mustache readymade thundercats keffiyeh craft beer
								marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park
								vegan.</p>
						</div>
					</div>
				</div>
				<div class="page-header"></div>
				<div>
					<button type="button" class="btn btn-default" data-toggle="tooltip"
						data-placement="left" title="Tooltip on left">Tooltip on
						left</button>

					<button type="button" class="btn btn-default" data-toggle="tooltip"
						data-placement="top" title="Tooltip on top">Tooltip on
						top</button>

					<button type="button" class="btn btn-default" data-toggle="tooltip"
						data-placement="bottom" title="Tooltip on bottom">Tooltip
						on bottom</button>

					<button type="button" class="btn btn-default" data-toggle="tooltip"
						data-placement="right" title="Tooltip on right">Tooltip
						on right</button>
				</div>

				<div class="page-header"></div>
				<a tabindex="0" class="btn btn-lg btn-danger" role="button"
					data-toggle="popover" data-trigger="focus"
					title="Dismissible popover" data-content="你是傻蛋么？">可消失的弹出框</a>

				<div class="page-header"></div>

				<div id="alertjs">
					<div class="alert alert-warning alert-dismissible fade in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<strong>这是错误的提示i!!!</strong>
					</div>
				</div>

				<div class="page-header"></div>
				<button type="button" id="myButtons" data-loading-text="登陆中。。。。。"
					class="btn btn-primary" autocomplete="off">登陆</button>

				<div class="page-header"></div>

				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<!-- aria-controls控制访问的id -->
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne"> Collapsible Group Item #1 </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">111</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwo">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo">
									Collapsible Group Item #2 </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">222</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree"
									aria-expanded="false" aria-controls="collapseThree">
									Collapsible Group Item #3 </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">333</div>
						</div>
					</div>
				</div>

				<div class="page-header"></div>

				<div>
					<a class="btn btn-primary" data-toggle="collapse"
						href="#collapseExample" aria-expanded="false"
						aria-controls="collapseExample"> Link with href </a>
					<button class="btn btn-primary" type="button"
						data-toggle="collapse" data-target="#collapseExample"
						aria-expanded="false" aria-controls="collapseExample">
						点击出来下面隐藏框内容噢</button>
					<div class="collapse" id="collapseExample">
						<div class="well">这是字体的噢</div>
					</div>
				</div>

				<div class="page-header"></div>

				<div class="bs-example" data-example-id="carousel-with-captions">
					<div id="carousel-example-captions" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-captions" data-slide-to="0"
								class=""></li>
							<li data-target="#carousel-example-captions" data-slide-to="1"
								class=""></li>
							<li data-target="#carousel-example-captions" data-slide-to="2"
								class="active"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div class="item">
								<img data-src="holder.js/900x500/auto/#777:#777" alt="900x500"
									src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgdmlld0JveD0iMCAwIDkwMCA1MDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzPjwvZGVmcz48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzc3NyI+PC9yZWN0PjxnPjx0ZXh0IHg9IjM0MS41IiB5PSIyNTAiIHN0eWxlPSJmaWxsOiM3Nzc7Zm9udC13ZWlnaHQ6Ym9sZDtmb250LWZhbWlseTpBcmlhbCwgSGVsdmV0aWNhLCBPcGVuIFNhbnMsIHNhbnMtc2VyaWYsIG1vbm9zcGFjZTtmb250LXNpemU6NDJwdDtkb21pbmFudC1iYXNlbGluZTpjZW50cmFsIj45MDB4NTAwPC90ZXh0PjwvZz48L3N2Zz4="
									data-holder-rendered="true">
								<div class="carousel-caption">
									<h3 id="first-slide-label">
										First slide label<a class="anchorjs-link"
											href="#first-slide-label"><span class="anchorjs-icon"></span></a>
									</h3>
									<p>Nulla vitae elit libero, a pharetra augue mollis
										interdum.</p>
								</div>
							</div>
							<div class="item active left">
								<img data-src="holder.js/900x500/auto/#666:#666" alt="900x500"
									src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgdmlld0JveD0iMCAwIDkwMCA1MDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzPjwvZGVmcz48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzY2NiI+PC9yZWN0PjxnPjx0ZXh0IHg9IjM0MS41IiB5PSIyNTAiIHN0eWxlPSJmaWxsOiM2NjY7Zm9udC13ZWlnaHQ6Ym9sZDtmb250LWZhbWlseTpBcmlhbCwgSGVsdmV0aWNhLCBPcGVuIFNhbnMsIHNhbnMtc2VyaWYsIG1vbm9zcGFjZTtmb250LXNpemU6NDJwdDtkb21pbmFudC1iYXNlbGluZTpjZW50cmFsIj45MDB4NTAwPC90ZXh0PjwvZz48L3N2Zz4="
									data-holder-rendered="true">
								<div class="carousel-caption">
									<h3 id="second-slide-label">
										Second slide label<a class="anchorjs-link"
											href="#second-slide-label"><span class="anchorjs-icon"></span></a>
									</h3>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
								</div>
							</div>
							<div class="item next left">
								<img data-src="holder.js/900x500/auto/#555:#5555" alt="900x500"
									src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgdmlld0JveD0iMCAwIDkwMCA1MDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzPjwvZGVmcz48cmVjdCB3aWR0aD0iOTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iIzU1NSI+PC9yZWN0PjxnPjx0ZXh0IHg9IjM0MS41IiB5PSIyNTAiIHN0eWxlPSJmaWxsOiM1NTU1O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjQycHQ7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+OTAweDUwMDwvdGV4dD48L2c+PC9zdmc+"
									data-holder-rendered="true">
								<div class="carousel-caption">
									<h3 id="third-slide-label">
										Third slide label<a class="anchorjs-link"
											href="#third-slide-label"><span class="anchorjs-icon"></span></a>
									</h3>
									<p>Praesent commodo cursus magna, vel scelerisque nisl
										consectetur.</p>
								</div>
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-captions"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-captions" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>



			</div>
			<div class="col-md-3">
				<div class="daohang">
					<ul>
						<li><a href="#">【1】第一部分</a>
							<ul class="nav">
								<li><a href="#header-1">1.111</a></li>
								<li><a href="#col-66">栅格分66比例两个</a></li>
								<li><a href="#col-237">栅格237比例三个</a></li>
								<li><a href="#ppp">p标签</a></li>
								<li><a href="#duiqi">p对其标签</a></li>
								<li><a href="#address">地址address标签</a></li>
								<li><a href="#blockquote">引用blockquote标签</a></li>
								<li><a href="#liebiao">列表ul li ol标签</a></li>
							</ul></li>
						<li><a href="#">【2】第一部分代码</a>
							<ul class="nav">
								<li><a href="#code">code</a></li>
								<li><a href="#table">table</a></li>
							</ul></li>
						<li><a href="#">【3】文本框表单代码</a>
							<ul class="nav">
								<li><a href="#">表单1.form-control form-group</a></li>
								<li><a href="#">水平排列的表单</a></li>
								<li><a href="#button">按钮</a></li>
							</ul></li>
						<li><a href="#">【4】组件</a>
							<ul class="nav">
								<li><a href="#drop">drop下拉</a></li>
								<li><a href="#nav">标签式的导航菜单</a></li>
								<li><a href="#message">消息的提示</a></li>
							</ul></li>
						<li><a href="#">【5】组件</a>
							<ul class="nav">
								<li><a href="#bigscreen">超大屏幕</a></li>
								<li><a href="#image">缩略图</a></li>
								<li><a href="#close">关闭的对话框</a></li>
								<li><a href="#process">进度条</a></li>
							</ul></li>
						<li><a href="#">【6】列表组</a>
							<ul class="nav">
								<li><a href="#listgroup">列表组list-group</a></li>
							</ul></li>
						<li><a href="#">【7】面版</a>
							<ul class="nav">
								<li><a href="#panel">面板</a></li>
								<li><a href="#panelfull">带表格的面版</a></li>
								<li><a href="#iframes">iframes嵌套网页</a></li>
								<li><a href="#dialog">dialog弹出对话框</a></li>
								<li><a href="#alertjs">alert.js警告消息</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<div class="jumbotron" id="bigscreen">
		<div class="container">
			<h1>大屏幕jumbotron!</h1>
			<p>这是内容内容。。。。。。。。</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">了解更多</a>
			</p>
		</div>
	</div>



</body>
</html>
