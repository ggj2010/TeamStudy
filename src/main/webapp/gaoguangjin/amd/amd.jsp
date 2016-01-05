<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>amd</title>
</head>
<body>
<script>
	var obj={
		name:"gao",
		getName:function(){
			return this.name;
		}
	};
	
	//alert(obj.name);
	//alert(obj.getName());
	
	
//写法会暴露所有模块成员
	var module1 =new Object({
		count:2,
		m1:function(){
			return "a";
		},
		m2:function(){
			return "b";
		}
	})	
	
//	alert(module1.count);
		
	//使用"立即执行函数"（Immediately-Invoked Function Expression，IIFE），可以达到不暴露私有成员的目的	
			
	//注意方法里面都是分号和等于号
	var module2=(function(){
		var count2=8;
		var m3=function(){
			return "a";
		};
		var m4=function(){
			return "b";
		}
		return{
			m3:m3
		};
	})(module1);
	
	alert(module2.count2);
	alert(module2.m3());

	var hi=function(){
		alert("hi");
	}

</script>
</body>
</html>