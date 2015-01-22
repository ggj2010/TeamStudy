<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>ISO9000代码维护</title>
<script type="text/javascript" src="../../scripts/boot.js"></script>
<link href="../../scripts/miniui/themes/default/miniui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../scripts/jquery-1.6.2.js"></script>
</head>
<body class="htmlBody">
	<div style="background-color: blue; width: 100%;">
		<div
			style="height: 60px; margin-left: 10px; vertical-align: middle; line-height: 30px; text-align: left;">
			<div style="font-size: 50px; color: white;">ORACLE</div>
			<div id="div1" style="color: white;">ISO9000代码维护</div>
		</div>
	</div>
	<div class="WsdSplitTitile">查询条件</div>
	<div id="searchForm"
		style="width: 100% x; text-align: center; overflow: auto; background-color: #edf6ff"
		onbuttonclick="onPanel1ButtonClick">
		<table style="margin: auto;" class="form-table" border="0"
			cellpadding="1" cellspacing="2">
			<tr style="height: 50px">
				<td><input id="wsd_TableName" class="mini-buttonedit"
					name="TableName" label="表名" /></td>
				<td><input id="wsd_BillName" class="mini-buttonedit"
					name="BillName" label="单据名称" /></td>
			</tr>
			<tr style="height: 50px">
				<td><input id="wsd_ISOFlag" class="mini-buttonedit"
					name="ISOFlag" label="ISO标识" /></td>
				<td><input id="wsd_ValidFlag" name="ValidFlag"
					class="mini-radiobuttonlist" label="有效标识"
					data="[{id: 1, text: '有效'}, {id: 2, text: '无效'}]" /></td>
			</tr>
			<tr>
				<td><input id="StartDate" class="mini-datepicker" label="日期"
					format="yyyy-MM-dd" /></td>
				<td><input id="EndDate" class="mini-datepicker" label="到"
					format="yyyy-MM-dd" ondrawdate="onEndDate" /></td>
			</tr>
		</table>
	</div>
	<div
		style="width: 100% x; text-align: center; background-color: #edf6ff">
		<table style="margin: auto;">
			<tr>
				<td
					style="vertical-align: middle; text-align: center; width: 150px;"><a
					class="mini-button" onclick="onSearch" style="width: 90px">查找</a></td>
				<td
					style="vertical-align: middle; text-align: center; width: 150px;"><a
					class="mini-button" onclick="onClear" style="width: 90px">清除</a></td>
			</tr>
		</table>
	</div>

	<div class="WsdSplitTitile">ISO9000代码</div>
	<div style="background-color: #edf6ff; width: 100%;">
		<div
			style="height: 30px; margin-left: 10px; vertical-align: middle; line-height: 30px; text-align: left;">
			<a class="mini-button" onclick="onAdd" style="width: 90px">新增</a>
		</div>
	</div>
	<!--撑满页面-->
	<div class="mini-fit">
		<div id="gridMain" class="mini-datagrid"
			style="width: 100%; height: 100%;"
			url="${pageContext.request.contextPath}/ajax/cux/CuxISOCodeService.jsp?method=SearchCuxISOcodeMaintainPage"
			idField="ID" sizeList="[5,10,20,50]" pageSize="10"
			multiSelect="flase" onselectionchanged="onSelectionChanged"
			selectOnLoad="true" sortField="ENTRYDATE" showSummaryRow="true">
			<div property="columns">
				<div field="TABLENAME" width="120" headerAlign="center"
					allowSort="true">表名</div>
				<div field="BILLNAME" width="120" headerAlign="center"
					allowSort="true">单据名称</div>
				<div field="ISOFLAG" width="120" headerAlign="center"
					allowSort="true">ISO标识</div>
				<div field="ENTRYDATE" width="120" headerAlign="center"
					dateFormat="yyyy-MM-dd" allowSort="true">日期</div>
				<div field="REMARK" width="120" headerAlign="center"
					allowSort="true">备注</div>
				<div field="VALIDFLAG" type="checkboxcolumn" trueValue="1"
					falseValue="0" width="60" headerAlign="center">有效标识</div>
				<div name="action1" width="60" headerAlign="center" align="center"
					renderer="onActionRendererUpdate" cellStyle="padding:0;">修改</div>
				<div name="action1" width="60" headerAlign="center" align="center"
					renderer="onActionRendererDelete" cellStyle="padding:0;">删除</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    	mini.parse();
    	var grid = mini.get("gridMain");
    	grid.load();
    	var searchForm = new mini.Form("#searchForm");
    	onSearch();
    	
    	
    </script>
</body>
</html>
