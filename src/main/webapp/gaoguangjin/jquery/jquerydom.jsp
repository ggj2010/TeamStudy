<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
request.setAttribute("path",path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>"><title>My JSP 'get_rec_list.jsp' starting page</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.10.2.js">
        </script>
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js">
        </script>
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js">
        </script>
        <style>
            ul {
                border: 1px solid red;
            }
        </style>
        <script>
            $(function(){
                $('#dialog').dialog({
                    autoOpen: false,//如果设置为true，则默认页面加载完毕后，就自动弹出对话框；相反则处理hidden状态。 
                    bgiframe: true, //解决ie6中遮罩层盖不住select的问题  
                    width: 400,
                    modal: true,//这个就是遮罩效果 
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    },
                    buttons: {
                        "Ok": function(){
                        
                            $('#dialog').dialog("close");
                        },
                        "Cancel": function(){
                            $('#dialog').dialog("close");
                        }
                    }
                });
                
                $('.onclickButton button').bind('click', function(){
                    //清空值
                    var str;
                    if ($(this).attr('id') == 'first') {
                        //alert($('li:first').text());
                        str = $('li:first').text();
                    }
                    else 
                        if ($(this).attr('id') == 'eq') {
                            //alert($('li:eq(1)').text());
                            str = $('li:eq(1)').text();
                        }
                        else 
                            if ($(this).attr('id') == 'last') {
                                //alert($('li:last').text());
                                str = $('li:last').text();
                            }
                    $('#alertString').text(str);
                    $('#dialog').dialog('open');
                });
                
                //
                $("#table").style("block", "none")
                
            });
            
            function getAjaxReclist(){
				var date=new Date();
				$.ajax({
					type: "get",//使用get方法访问后台
				dataType: "json",////jquery调用servlert ajax返回json格式的数据.
				url: "${path}/ggj/getAjaxRecList.do",//要访问的后台地址
				data: "param=" + "高广金&date="+new Date(),//要发送的数据
				success: function(data){//msg为返回的数据，在这里做数据绑定
						alert(data.address);
                      //alert(data);
				},
				complete: function(){//AJAX请求完成时隐藏loading提示
				}
				})
				
				
            }
			
			
			
        </script>
    </head>
    <body>
        <ul>
            <li>
                first 获取第一个li的值 li:first
            </li>
            <li>
                eq  获取第二个li:eq(1)
            </li>
            <li>
                list item 3
            </li>
            <li>
                list item 4
            </li>
            <li>
                last 获取 li:last
            </li>
        </ul>
        <div class="onclickButton">
            <button id="first">
                :first
            </button>
            <button id="last">
                :last
            </button>
            <button id="eq">
                :eq(1)
            </button>
            <a href="javascript:getAjaxReclist()">获取ajax列表</a>
        </div>
        <div id="dialog" title="弹出框">
            <p id="alertString">
            </p>
        </div>
        <div class="table" style="display:none">
            <table id="tb">
            </table>
        </div>
    </body>
</html>
