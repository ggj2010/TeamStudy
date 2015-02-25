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
		$("#object1").text(
				"【创建对象方式一】var objectTest={} " + object1.name
						+ object1.testfunction());

		//调用方式二
		$("#object2").text(
				"【创建对象方式二】var objectTest=new Object() " + object2.name
						+ object2.objectFunction());

		//调用方式三  object3必须要new  之后才会成为对象
		var object = new object3();
		$("#object3").text(
				"【创建对象方式三】var objectTest=new Object() " + object.name
						+ object.functiontest());
		$("#object4").text(
				"【创建对象方式四】var objectTest=new Object({}) " + object4.name
						+ object4.function1()+object4.function2());
	}

	var test = {
		init : function() {
			//初始化
			$("#init").text("初始化");
		}
	}

	var object1 = {
		testfunction : function() {
			return "是个帅哥啊！";
		},
		name : "gaoguangjin",
		age : "18"

	/** functionB 和testfunction 这两种格式不能写在一起
	var functionB=function(){
		return "是个帅哥啊！";
	}
	
	 **/
	}

	var object2 = new Object();
	object2.name = "高";
	object2.objectFunction = function() {
		return "是个帅哥啊！";
	}

	var object3 = function() {
		this.name = "高广金";
		this.functiontest = function() {
			return "是个帅哥啊！";
		};
	}
	
	//js模块化写法
	var object4=new Object({
		function1 : function() {
			return "是1个帅哥啊！";
		},
		function2 : function() {
			return "是2个帅哥啊！";
		},
		name : "gaoguangjin",
		age : "18"
	})
	
	
</script>
<body>
	<div style="border: 1px solid red;">
		<span id="init"></span> <br> <span id="object1"></span>
		<textarea style="width: 400px; height: 110px">
		var object1 = {
			 testfunction:function(){
				return "是个帅哥啊！";
			},
			name : "gaoguangjin",
			age : "18"
		}</textarea>
		<br> <span id="object2"></span>
		<textarea style="width: 400px; height: 100px">
	var object2=new Object();
		object2.name="高";
		object2.objectFunction=function(){
			return "是个帅哥啊！";
		}</textarea>
		<br> <span id="object3"></span>
		<textarea style="width: 400px; height: 100px">
		var object3=function(){
			this.name="高广金";
			this.functiontest=function(){
				return "是个帅哥啊！";
			};
		}</textarea>
		
		<br> <span id="object4"></span>
		<textarea style="width: 400px; height: 100px">
		var object4=new Object({
		function1 : function() {
				return "是1个帅哥啊！";
			},
			function2 : function() {
				return "是2个帅哥啊！";
			},
			name : "gaoguangjin",
			age : "18"
		})
		</textarea>
	</div>
</body>
</html>