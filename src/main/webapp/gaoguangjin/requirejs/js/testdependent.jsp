<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>object</title>
<script  src="require.js" data-main="main"></script>
</head>
<script type="text/javascript">


/**
require(['a'], function(c) {
  a();
});
**/

/**
 * 
这样写是出不来效果的，因为c依赖a和b的
require(['c'], function(c) {
c();
});
 */
 require(['a','b','c'], function(aa,bb,cc) {
	 cc.c();
	 });
 
 //function 里面的命名不要和js名字一样。
</script>

<body>
</body>
</html>