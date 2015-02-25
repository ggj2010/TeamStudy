<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>object</title>
<script src="http://code.jquery.com/jquery-1.10.2.js">
	
</script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js">
	
</script>
</head>
<script type="text/javascript">
	$(function() {
		test.init();
		//创建对象有两种方式，一种是直接new Object(),一种是直接var objectTest={};
		//创建函数也有两种方式是var aaa=function(){...}  2是function aaa(){...};
		////为function类型 ,new了之后为object

		testObjectFunction();
	})

	function testObjectFunction() {
		//调用方式一
		$("#fuction1").text(
				"	var function1=(function(){ " + function1.name
						+ function1.m1());
	}

	var test = {
		init : function() {
			//初始化
			$("#init").text("初始化function");
		}
	}
	
	
	//使用"立即执行函数"，可以达到不暴露私有成员的目的。
	var function1=(function(){
		var m1=function(){
			return '高广金';
		};
		var m2=function(){
			return '帅哥啊';
		};
		
		var name="gaoguangjin";
		return {
　　　　		m1 : m1,
			name:name,
　　　　　　	m2 : m2
		　};
	})();
	
	
	
</script>
<body>
	<div style="border: 1px solid red;">
		<span id="init"></span> <br> <span id="fuction1"></span>
		<textarea style="width: 400px; height: 250px">
		var function1=(function(){
		var m1=function(){
			return '高广金';
		};
		var m2=function(){
			return '帅哥啊';
		};
		var name="gaoguangjin";
		return {
　　　　		m1 : m1,
			name:name,
			m2 : m2
        };
	})();
	   </textarea>
		<br>

	</div>
</body>
</html>