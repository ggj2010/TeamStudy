<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			if("${message}"!=null&&"${message}"!=""){
				alert("${message}");
			}
			//登陆框的按钮点击之后
			 $("a[name='buyButton']").on('click', function () {
				    var $btn = $(this).button('loading');
				setTimeout(function() {
					$btn.button('reset');
				}, 1000);
			})
			
				 $('[data-toggle="tooltip"]').tooltip();//提示框启用
			
		})
	});
	
	function buy(id){
		window.location="${path}/ggj/concurrent?method=buy&id="+id;
	}
	function concurrentBy(id){
		window.location="${path}/ggj/concurrent?method=buy&isConCurrent=y&id="+id;
	}
</script>
<style>
.tops {
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
</style>

<body>




	<div class="tops">
		<div class="container-fluid">
			<div class="pull-left">
				<h3>图书</h3>
			</div>
			<div class="pull-right visible-lg">
				<h3>学习并发锁</h3>
			</div>
		</div>
	</div>



	<div class="page-header"></div>
	<!-- 大容器 -->
	<div class="container">
		<div class="table-responsive">
			<table class="table table-hover table-bordered">
				<tr class="info">
					<th>序号</th>
					<th>书名</th>
					<th>书描述</th>
					<th>剩余数量</th>
					<th><span class="glyphicon glyphicon-shopping-cart">购买</th>
				</tr>
				<c:forEach items="${bookList}" var="book">
					<tr>
						<td>${book.id}</td>
						<td>${book.bookName}</td>
						<td>
							<span data-toggle="tooltip" data-placement="top"title="${book.bookDetail}"> 
								${book.bookDetail} 
							</span>
						</td>
						<td>${book.bookRemainNumber}</td>
						<td>
							<a type="button" class="btn btn-info" name="buyButton"data-loading-text="bug.." href="javascript:buy(${book.id})">
									<span class="glyphicon glyphicon-shopping-cart"aria-hidden="true"></span>
							</a>
							<a type="button" class="btn btn-info" name="buyButton"data-loading-text="bug.." href="javascript:concurrentBy(${book.id})">
									<span class="glyphicon glyphicon-shopping-cart"aria-hidden="true">并发</span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
