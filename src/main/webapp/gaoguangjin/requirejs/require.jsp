<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>object</title>
<!-- async属性表明这个文件需要异步加载，避免网页失去响应。IE不支持这个属性，只支持defer，所以把defer也写上。 -->
<script src="js/require.js"></script>
 <script src="js/main.js" defer async="true"></script>
</head>
<script type="text/javascript">
	
require(['jquery', 'test'], function($, test) {
	  
	   
	   $(function() {
		   $("#init").text("初始化"); 
	   })
	   
	   alert($().jquery);
    alert(123);
    testAdd(1,2);
});
	
</script>

<style>
pre {
	margin: 20px 0;
	font: 12px/20px 'courier new'; background : #4A4A4A; padding : 10px
	20px; color : #F8F8D4;
	border: none;
	background: #4A4A4A;
	padding: 10px 20px;
	color: #F8F8D4;
}
</style>
<body>
	<div style="border: 1px solid red;">
		<span id="init"></span> <br>
		<pre >
		一、为什么要用require.js？
最早的时候，所有Javascript代码都写在一个文件里面，只要加载这一个文件就够了。后来，代码越来越多，一个文件不够了，必须分成多个文件，依次加载。下面的网页代码，相信很多人都见过。
　　script src="1.js"/script
　　script src="2.js"/script
　　script src="3.js /script>
　　script src="4.js" /script
　　script src="5.js" /script
　　script src="6.js" /script
这段代码依次加载多个js文件。
这样的写法有很大的缺点。首先，加载的时候，浏览器会停止网页渲染，加载文件越多，网页失去响应的时间就会越长；其次，由于js文件之间存在依赖关系，因此必须严格保证加载顺序（比如上例的1.js要在2.js的前面），依赖性最大的模块一定要放到最后加载，当依赖关系很复杂的时候，代码的编写和维护都会变得困难。
require.js的诞生，就是为了解决这两个问题：
　　
　　（1）实现js文件的异步加载，避免网页失去响应；
　　（2）管理模块之间的依赖性，便于代码的编写和维护。
		</pre>
	</div>
</body>
</html>