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
	margin-top: 20px;
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
					<p>比如这是代码《code》：<code>这是&lt;span&gt;dddd&lt;span&gt;</code></p>
				</div>
				
				<div class="page-header"></div>
				<div>
					<p>用户输入《kbd》<kbd>crtl+</kbd></p>
				</div>

				<div>
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
	<span style="color: red">
						------------------在table前面增加一个div class=table-responsive,这样当其会在小屏幕设备上（小于768px）水平滚动。当屏幕大于 768px 宽度时，水平滚动条消失。-------------- </span>
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



				<div>
					<pre id="test">
					行（row）”必须包含在 .container，这样才能看到栅格效果
						</pre>
				</div>
			</div>
			<div class="col-md-3">
				<div class="daohang">
					<ul>
						<li><a>【1】第一部分</a>
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
						<li><a href="#header-1">【2】第一部分代码</a>
							<ul class="nav">
								<li><a href="#code">code</a></li>
								<li><a href="#header-1">2.222</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>



</body>
</html>
