<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/youdao/common/header.jsp"%>
<%@ include file="/youdao/common/taglibs.jsp"%>
<html>
<head>
<title>有道云笔记主页面</title>
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
/**头背景色是白色的**/
.header {
	height: 72px;
	background: #fefefe;
	width: 100%;
}

.headerleft {
	height: 72px;
	margin: 0 auto;
	width: 961px;
}
/**background-position: 0 -10px; 距离待截图位置为10px  图片向左浮动**/
.homelogo {
	background: url("${path}/youdao/image/header.png") no-repeat;
	background-position: 0 -10px;
	float: left;
	height: 37px;
	margin: 18px 73px 17px 1px;
	width: 152px;
}
/**因为图片的背景色是空白的，所以需要用用a的颜色来填充，不然看不到**/
.headerleft a {
	color: #7c808c;
	display: block;
	text-align: center;
	text-decoration: none;
}
/**去除li的点标记**/
ul {
	list-style: outside none none;
	margin-left: 0px;
}
/**让所有的li都向左浮动**/
.nvlMenu {
	float: left;
	font-size: 0;
	height: 72px;
}
/**设置li的样式，默认的下划线为白色**/
li {
	border-bottom: 3px solid #fefefe;
	display: inline-block;
	font-size: 18px;
	margin: 0 12px;
	padding: 0 7px;
}

/**选中之后增加下划线**/
.nav-current {
	border-bottom: 2px solid #3086f2;
}

/**连接点击之后的效果**/
.nav-current a {
	color: #3086f2;
}

.nvlMenu a {
	line-height: 70px;
	text-decoration: none;
}


/**fixed绝对定位，当你拉动滚动条的时候 这个始终不动**/
#download-bar {
	background-color: #f4f1f1;
	height: 70px;
	width: 100%;
	margin-top: 480px;
	position: fixed;
	border: 1 solid grey;
}

/**电脑下载**/
.windows-download {
	background: url("${path}/youdao/image/download.png");
	background-position: 0 -346px;
	float: left;
	height: 24px; margin : 23.5px 70px;
	width: 100px;
	margin: 23.5px 70px;
}

/**电脑下载图标**/
.mac-download {
	background: url("${path}/youdao/image/download.png");
	background-position: 0 -216px;
	float: left;
	height: 29px;
	margin: 21px 62px;
	width: 68px;
}

.android-download {
	background: url("${path}/youdao/image/download.png");
	background-position: 0 0;
	cursor: pointer;
	float: left;
	height: 31px;
	margin: 20px 50px;
	width: 92px;
}

.iphone-download {
	background: url("${path}/youdao/image/download.png");
	background-position: 0 -146px;
	cursor: pointer;
	float: left;
	height: 30px;
	margin: 20.5px 55.5px;
	width: 81px;
}

.ipad-download {
	background: url("${path}/youdao/image/download.png");
	background-position: 0 -72px;
	float: left;
	height: 32px;
	margin: 19.5px 62.5px;
	width: 67px;
}

/**右边登陆的div**/
.headerright {
	float: right;
	width: 190px;
	margin: 17px 0 15px;
}

/**登陆的图标**/
.loginon {
	border: 1px solid #2982f1;
	width: 92px;
	height: 37px;
	padding: 5 14px;
	border-radius: 2px;
	cursor: pointer;
	background-color: #599ef4;
	/**设置color字体颜色， font-size大小,line-height行距离，text-indent首行缩进**/
	color: white;
	font-size: 14px;
	line-height: 35px;
	text-indent: 10px;
	text-align: left;
}


</style>
<script type="text/javascript">
			/** **/
			require(['jquery'], function($) {
				   $(function() {
					   for(var i=0;i<1000;i++){
						   $("#append").append("<span></span><br>"); 
					   }
				   })
			});
	</script>
<body>
	<div class="header">
		<div class="headerleft">
			<a class="homelogo" title="logo" href="#"></a>
			<ul class="nvlMenu">
				<li class="nav-current"><a>首页</a></li>
				<li><a>云笔记</a></li>
				<li><a>云协作</a></li>
				<li><a>下载</a></li>
				<li><a>博客</a></li>
				<li><a>会员</a></li>
			</ul>

			<div class="headerright">
				<div class="loginon"><a href="${path}/youdao/login.do">登录网页版</a></div>
			</div>
		</div>
	</div>

	<div id="download-bar">
		<ul>
			<li class="windows-download"></li>
			<li class="mac-download"></li>
			<li class="android-download"></li>
			<li class="iphone-download"></li>
			<li class="ipad-download"></li>
			<li>绝对定位 fixed</li>
		</ul>
	</div>

	
	<div id="append"
		style="overflow: scroll; width: 100%; height: 300px; background-color: grey" title="测试滚动条的overflow: scroll">
	</div>


	<div style="height: 1000px;"></div>
</body>
</html>
