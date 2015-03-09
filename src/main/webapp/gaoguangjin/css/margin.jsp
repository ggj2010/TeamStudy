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


/**以下是测试margin的效果**/
.margintest {
	width: 200px;
	background-color: grey;
}

.margintest1 {
	margin: 10px 5px 15px 20px;
	background-color: blue;
}

.margintest2 {
	margin: 10px 5px 15px;
	background-color: orange;
}

.margintest3 {
	margin: 10px 5px;
	background-color: yellow;
}

.margintest4 {
	margin: 10px;
	background-color: pink;
}

.marginquchu {
	background-color: white;
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
	
	<div class="margintest">
		<div class="marginquchu">上边界</div>
		<div class="margintest1"
			title="上外边距是 10px 右外边距是 5px 下外边距是 15px 左外边距是 20px ,4个就是顺时针方向
	">
			margin:10px 5px 15px 20px;</div>
		<div class="margintest2"
			title="上外边距是 10px 右外边距和左外边距是 5px 下外边距是 15px 3个就是 上 右左 下
		">margin:10px
			5px 15px;</div>

		<div class="margintest3"
			title="上外边距和下外边距是 10px 右外边距和左外边距是 5px  上下 右左
		">margin:10px
			5px;</div>

		<div class="margintest4" title="所有 4 个外边距都是 10px">margin:10px;</div>

		<div class="marginquchu">下边界</div>

	</div>


</body>
</html>
