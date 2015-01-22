<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'robot.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js">
</script>

<script src="${path}/gaoguangjin/js/jqueryui/ui/jquery.ui.core.js"></script>

	<script src="${path}/gaoguangjin/js/jqueryui/ui/jquery.ui.datepicker.js"></script>

<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js">
	
</script>
<style>
.title {
	height: 30px;
	width: 100%;
	background
}
</style>
<script type="text/javascript">
	//这里面写jquery事件
	$(function() {
		
		//设置为当前日期,初始化日期默认值
		var today=new Date();
		var year=1990+today.getYear()+"-";
		var month=today.getMonth()>10?today.getMonth()+1:"0"+(today.getMonth()+1)+"-";
		var date=today.getDate()>10?today.getDate():"0"+today.getDate();
		var dateStr=year+month+date;
		$('#beginDate').val(dateStr);
		$('#endDate').val(dateStr);
	
		$("#beginDate").datepicker({"dateFormat" : "yy-mm-dd"});
		$("#endDate").datepicker({"dateFormat" : "yy-mm-dd"});
		

		//清空输入框字段事件
		$("#clear").on("click", function() {
			//让对应的输入框值为空
			$("#tableName").val("");
			$("#billName").val("");
			$("#isoFlag").val("");
			$("#beginDate").val("");
			$("#endDate").val("");
			//清空时候 让有效复选框为默认选中状态
			$("#validFlagY").prop("checked", true);
			//初始化日期为当天
			$('#beginDate').val(dateStr);
			$('#endDate').val(dateStr);
			
			//先清空
			$("tr").nextAll().empty();

		})

		//查询事件
		$("#query").on("click", function() {
			getData();
		})

		//新增事件
		$("#addSave").on("click", function() {
			//点击保存事件，调用ajax
			alert("点击保存事件，调用ajax,ajax响应完之后 再关闭这个窗口");
			$('#myModal').modal('hide');
			
		})
		

		function getData() {
			//1、首先获取所有输入框的值

			var tableName = $("#tableName").val();
			var billName = $("#billName").val();
			var isoFlag = $("#isoFlag").val();
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			//复选框特殊处理
			var validFlag = $("input[type='radio']:checked").val();

			//组装传到后台需要查询的参数，参数一般都是  name=value&name=value 这样的格式
			var queryContent = "tableName=" + tableName + "&" + "billName="
					+ billName;
			queryContent += "&isoFlag=" + isoFlag + "&" + "beginDate="
					+ beginDate;
			queryContent += "&endDate=" + endDate + "&" + "validFlag="
					+ validFlag;
			//调用jquerAjax
			$.ajax({
				type : "get",//使用get方法访问后台
				dataType : "json",////jquery调用servlert ajax返回json格式的数据.
				url : "${path}/ggj/QueryServlet",//要访问的后台地址
				data : queryContent,//要发送的数据
				success : function(data) {//调用后台成功返回的jason参数
					appendTableHtml(data);
				},
				complete : function() {//AJAX请求完成时隐藏loading提示
				}
			})
		}

		//处理查询之后 返回的jason结果集
		function appendTableHtml(data) {
			//先清空
			$("tr").nextAll().empty();
			$(data).each(function(i, item) {
				addRow(item);
			});

		}

		function addRow(data) {
			var tableName = data.tableName;
			var billName = data.billName;
			var isoFlag = data.isoFlag;
			var entryDate = data.entryDate;
			var validFlag = data.validFlag;
			var remark = data.remak;
			//更新时候用到
			var id = data.id;
			var html = "";

			html += "<tr class=\"active\">";
			html += "<td>" + tableName + "</td>";
			html += "<td>" + billName + "</td>";
			html += "<td>" + isoFlag + "</td>";
			html += "<td>" + entryDate + "</td>";
			html += "<td>" + remark + "</td>";

			if (validFlag == "1") {
				html += "<td><input type=\"radio\" checked=\"checked\" value=\"1\" disabled></td>";
			} else {
				html += "<td><input type=\"radio\"  value=\"0\" disabled></td>";
			}

			html += "<td><a href=\"javaScript: update(" + id
					+ ")\">修改</a></td>";
			html += "</tr>";
			$("#row").append(html);
		}
		
		
	

	})
</script>
</head>
<body>

	<div style="margin-top: 50px; border: 1 solid #000;">
		<blockquote class="bg-primary">查询条件</blockquote>
		<div style="margin-top: 20px">
			<div class="form-horizontal">
				<div class="form-group has-success has-feedback">
					<label class="col-xs-1 control-label">表名</label>
					<div class="col-xs-5">
						<input type="text" id="tableName" class="form-control"> <span
							class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>
					<label class="col-xs-1 control-label">单据名称</label>
					<div class="col-xs-5">
						<input type="text" id="billName" class="form-control"> <span
							class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>
					<label class="col-xs-1 control-label">ISO标识</label>
					<div class="col-xs-5">
						<input type="text" id="isoFlag" class="form-control"> <span
							class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>
					<label class="col-xs-1 control-label">有效标识</label>
					<div class="col-xs-5">
						<label class="form-control""> <input type="radio"
							id="validFlagY" name="validFlag" checked="checked" value="1">有效
							<input type="radio" id="validFlagN" name="validFlag" value="0">无效
						</label> <span class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>

					<label class="col-xs-1 control-label">日期</label>
					<div class="col-xs-5">
						<input type="text" id="beginDate" tagname="dateInputYear" class="form-control"
							> <span
							class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>
					<label class="col-xs-1 control-label">到</label>
					<div class="col-xs-5">
						<input type="text" id="endDate" class="form-control"> <span
							class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>

					<div style="clear: both; margin-left: 400px; float: left">
						<button id="query" class="btn btn-danger">查询</button>
					</div>
					<div style="margin-left: 400px; float: left">
						<button id="clear" class="btn btn-danger">清空</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="margin-top:20px">
	<blockquote class="bg-info">ISO9000代码维护</blockquote>
	<div style="margin-left:20px">
	<button  class="btn btn-info"  data-toggle="modal" data-target="#myModal">新增</button>
	</div>
	
		<table class="table table-hover" id="row">
			<tr>
				<th>表名</th>
				<th>单据名称</th>
				<th>IOS表示</th>
				<th>日期</th>
				<th>备注</th>
				<th>有效标志</th>
				<th>修改</th>
			</tr>
			
		</table>
	</div>
	

<!-- 弹出框新增 -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title " id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
           	这里面可以写 输入框的值。
         	  然后用ajax 去保存到后台
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="addSave">保存</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
