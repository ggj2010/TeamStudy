<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<html>
<head>
<title>有道云笔记页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<style>
/**为了练习css，所以把css写到文件里面。  **/
/**去除不同浏览器的初始大小**/
body,ul,li,a {
	margin: 0;
	padding: 0;
}

.header {
	width: 980px;
	margin: 0 auto;
	height: 55px;
	padding-top: 10px;
}

.logo {
	background: url("${path}/youdao/image/header.png") no-repeat;
	background-position: 0 -10px;
	height: 37px;
	width: 152px;
	float: left;
}

.header-middle {
	margin-left: 10px;
	background-color: #C9C9C9;
	height: 37px;
	width: 1px;
	float: left;
}

.header-text {
	text-align: center;
	font-size: 20px;
	padding-top: 5px; height : 37px;
	width: 100px;
	float: left;
	height: 37px;
}

.horizontal {
	background: url("${path}/youdao/image/horizontal.png") repeat;
	width: 1180px;
	height: 20px;
	margin: 0 auto;
}

.content {
	margin: 30px auto;
	width: 980px;
	height: 300px;
	border: 1px solid #bbc2c6;
	border-radius: 8px; /**椭圆形边缘**/
}

.formgroup-span {
	float: left;
	margin: 30px 0 0 30px;
	height: 45px;
	width: 80px;
	text-align: right;
	padding-top: 10px;
}

.formgroup {
	float: left;
	margin: 30px 0 0 30px;
	height: 35px;
	border: 1px solid #bbc2c6;
	border-radius: 8px; /**椭圆形边缘**/
}

.formgroup input {
	margin-top: 10px;
	border: 0px solid #ddd;
	outline: medium none;
}

.submitButton {
	width: 350px;
	height: 50px;
	background-color: pink;
	margin-top: 10px;
	border: 1px solid #ddd;
	border-radius: 8px; /**椭圆形边缘**/
}

.clear {
	clear: both;
}
</style>
<script type="text/javascript">
	/** **/
	require([ 'jquery' ], function($) {
		$(function() {
		})
	});
</script>
<body>
	<div class="header">
		<div class="logo"></div>
		<div class="header-middle"></div>
		<div class="header-text">注册账号</div>
	</div>

	<div class="horizontal"></div>
	<div class="content">
		<div class="form">
			<form action="${path}/youdao/register/save.do" method="post">
				<div class="formgroup-span">
					<span>手机/邮箱</span>
				</div>
				<div class="formgroup">
					<input type="text" name="humanName">
				</div>
		</div>
		<div class="clear"></div>
		<div class="form">
			<div class="formgroup-span">
				<span>密码</span>
			</div>
			<div class="formgroup">
				<input type="text" name="humanPassWord">
			</div>
		</div>
		<div class="clear"></div>
		<div class="form">
			<div class="formgroup-span"></div>
			<div>
				<input type="submit" class="submitButton" value="注册" />
			</div>
		</div>
		</form>
	</div>
</body>
</html>
