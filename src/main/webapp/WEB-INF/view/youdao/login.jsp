<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>

<html>
<head>
<title>有道云笔记</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="expires" content="0">
</head>

<style>
body,ul,li,a,h2 {
	margin: 0;
	padding: 0;
	font-family: 微软雅黑;
	color:white;
}

/**导航栏背景图**/
.header {
	background: url("${path}/youdao/image/headerbackground.png") repeat;
	height: 58px;
	color: #707070;
	font-size: 18px;
}

/**logo向左浮动**/
.header-left {
	float: left;
	margin-left: 200px;
	height: 37px;
	width: 152px;
}

/**logo**/
.header-left .headerLogo {
	background: url("${path}/youdao/image/header.png") no-repeat;
	background-position: 0 -10px;
	height: 37px;
	margin: 14px 73px 25px 1px;
	width: 152px;
}

/**导航栏右边**/
.header-right {
	width: 150px;
	margin-right: 200px;
	float: right;
	height: 37px;
}

/**导航栏右边字体居中**/
.header-word {
	font-size: 15px;
	color: grey;
	margin: auto;
	text-align: center;
	margin-top: 20px;
}

/**中间部分**/
.middle {
	height: 470px;
	background-color: #7BC5FF;
	overflow: hidden;
	/**比较特殊的作用， 要想解决这个问题,在父容器中除定义宽和高的值以外,还必须写overflow:hidden,这样就能把子容器的其它内容隐藏**/
}

.bd-login-main {
	background-color: #ffffff;
	border: 1px solid #578bb4;
	border-radius: 5px;
	height: 340px;
	margin: 50px auto;
	width: 789px;
}

/**登陆框左边**/
.login-form-left {
	widows: 350px;
	height: 300px;
	float: left;
	padding: 50px 50px;
}

.login-form-right {
	widows: 350px;
	height: 250px;
	float: left;
	padding: 50px 50px;
}

h2 {
	font-size: 16px;
	font-weight: 500;
	margin-bottom: 20px;
}

/**输入框格式**/
.input-text-row {
	border: 1px solid #bbc2c6;
	border-radius: 8px; /**椭圆形边缘**/
	height: 39px;
	line-height: 37px;
	width: 324px;
	margin-top: 20px;
}

.inputtext {
	border: 0 none; /**去除边框**/
	line-height: 15px;
	text-decoration: none;
	outline: medium none;
	margin-top: 10px;
	width: 300px;
	font-size: 14px;
	padding: 0 12px;
	font-family: 微软雅黑;
}

/**中间的线**/
.login-middle {
	background-color: #d8dee1;
	height: 265px;
	margin-top: 45px;
	width: 2px;
	float: left;
}

.login-button {
	text-align: center;
	height: 40px;
	width: 150px;
	margin: 50px 20px 60px 0px;
	border: 1px solid #bbc2c6;
	border-radius: 8px; /**椭圆形边缘**/
	background-color: #7DC92B;
	color: white;
	font-size: 14px;
	font-family: 微软雅黑;
	float: left;
}
</style>

<script type="text/javascript">
			/** **/
			require(['jquery'], function($) {
				   $(function() {
				   })
			});
	</script>
<body>

	<div class="header">
		<div class="header-left">
			<div class="headerLogo"></div>
		</div>
		<div class="header-right">
			<div class="header-word">
				<span>首页|多平台下载</span>
			</div>
		</div>
	</div>
	<form action="${path}/youdao/login.do" method="post">
		<div>
			<div class="middle">
				<div class="bd-login-main">
					<div class="login-form-left">
						<h2>网易通行证账号登录：</h2>
						<div>
							<div class="input-text-row">
								<input class="inputtext" type="text" placeholder="姓名"
									name="humanName">
							</div>
							<div class="input-text-row">
								<input class="inputtext" type="password" placeholder="密码" name="humanPassword">
							</div>
							<button type="submit" class="login-button"
								title="button 用text-align: center;可以居中">登 陆</button>
							<div class="login-button">
								<div style="margin-top: 10px"><a href="${path}/youdao/register.do">注册</a></div>
							</div>
						</div>
					</div>
					<div class="login-middle"></div>
					<div class="login-form-right">
						<h2>网易通行证账号登录：</h2>
					</div>
				</div>
			</div>
		</div>
	</form>


</body>
</html>
