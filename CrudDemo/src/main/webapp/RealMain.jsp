<%@page import="java.sql.Statement"%>
<!-- 注意：这个包是 java.sql.*而不是com.mysql.jdbc.Connection，还有记得import这些包-->
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.sun.org.glassfish.gmbal.IncludeSubclass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" href="css/body.css" type="text/css">
<link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="css/icon.css" type="text/css">
<!-- EasyUI CSS -->
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/easyui.demo.css">
<link rel="stylesheet" type="text/css" href="css/tabs.css">
<link rel="stylesheet" type="text/css" href="css/pagination.css">
<link rel="stylesheet" type="text/css" href="css/searchbox.css">
<link rel="stylesheet" type="text/css" href="css/combo.css">
<link rel="stylesheet" type="text/css" href="css/combobox.css">
<link rel="stylesheet" type="text/css" href="css/themes/window.css">

<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/toolbar.js"></script>
<script type="text/javascript" src="js/loaddata.js"></script>
<%
	if (request.getAttribute("result") != null) {
		String x = request.getAttribute("result").toString();
		request.removeAttribute("result");
		out.println("<script>alert('操作结果:" + x + "')</script>");
	}
%>
<script type="text/javascript">
	/* 载入数据配置。初始化数据。 */
	window.onload = LoadTree();
	window.onload = function resetTags() {
		$('#tt').tabs({
			onClose : function(title, index) {
				if (title == "新增部门" || title == "更新部门") {
					$('#tt').tabs('enableTab', '部门人员');
					$('#tt').tabs('select', '基本资料');
				} else if (title == "添加人员") {
					$('#tt').tabs('enableTab', '部门人员');
					LoadTree();
				} else {
					$('#tt').tabs('enableTab', '部门人员');
					$('#tt').tabs('select', '部门人员');
				}
			}
		});
	}
</script>
<title>CRUD展示</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" class="body_background"
		style="height: 60px; background: url 'images/background.jpg'; padding: 10px;"></div>
	<div data-options="region:'west',split:true,title:'树状图'"
		style="width: 200px; padding: 10px; background-color: #f7f7f7">
		<input id="search_person_tree" style="width: 100px"
			class="easyui-searchbox"></input> <a href="#"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true"
			onclick="LoadTree()">刷新树</a>
		<ul id="treeDemo" class="ztree" style="height: auto;"></ul>
	</div>
	<div id="p2" data-options="region:'center',title:'图表'">
		<!-- 表格 -->
		<div class="easyui-tabs"
			data-options="fit:true,border:false,plain:true" id="tt"
			style="background-color: #dbe7f7;">
			<div title="基本资料" data-options="" style="padding: 10px;">
				<div id="info"></div>
				<div id="tool"
					style="padding: 5px; height: auto; margin-top: -10px; margin-left: -10px; background-color: #e7effa; width: 110%;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="addDepartment()">新增部门</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-edit" plain="true"
						onclick="updataDepartment()">编辑</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-cut" plain="true"
						onclick="deleteDepartment()">删除</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-reload" plain="true"
						onclick="LoadDepartments()">刷新数据</a>
				</div>
				<table width="80%" border="1px" bordercolor="#c0c0c0" class="table1"
					id="infotable">
					<tr>
						<th colspan="2" class="table_info">基本资料</th>
					</tr>
				</table>
			</div>
			<div title="部门人员" data-options="" style="padding: 10px">
				<div id="toolbar"
					style="padding: 5px; height: auto; margin-top: -10px; margin-bottom: 0px; margin-left: -10px; background-color: #e7effa; width: 110%;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-man"
						plain="true" onclick="addPerson()">新增人员</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-add" plain="true"
						onclick="inputPerson()">添加人员</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-cut" plain="true"
						onclick="removePerson()">移除人员</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-reload" plain="true"
						onclick="LoadPersons(person_pagenumber,person_pagesize,person_id)">刷新</a>
					<input id="search_person_input" style="width: 100px"
						class="easyui-searchbox"></input>
				</div>
				<div id="person_window" closed="true" collapsible="false"
					minimizable="false" maximizable="false" closable="false"
					class="easyui-window" title="请选择你要添加的人员"
					data-options="width:150,height:300,left:250,top:150,inline:true,border:'thin',cls:'c5'">
					<div style="width: 100%; height: 100%;">
						<div style="width: 100%; height: 91%; overflow: auto;">
							<ul id="persontree" class="ztree" style="height: auto;"></ul>
						</div>
						<div style="background-color: #d9d9ff;">
							<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
								plain="true" onclick="ok_inputperson()">确定</a> <a href="#"
								class="easyui-linkbutton" iconCls="icon-no" plain="true"
								onclick="exit_inputperson()">取消</a>
						</div>
					</div>
				</div>
				<div style="margin-left: -10px; width: 100%; height: 89%;"
					id="person_table_div">
					<table class="easyui-datagrid" id="personstable" title="部门人员表"
						style="width: 102%; height: 100%;"
						data-options="rownumbers:true,singleSelect:true,method:'post',fitColumns:'true'">
						<thead>
							<tr>
								<th data-options="field:'id',width:120,align:'center'">ID</th>
								<th data-options="field:'loginname',width:120,align:'center'">登录名</th>
								<th data-options="field:'cnname',width:120,align:'center'">中文名称</th>
								<th data-options="field:'sex',width:80,align:'center'">性别</th>
								<th data-options="field:'duty',width:120,align:'center'">职位</th>
								<th data-options="field:'dutyname',width:120,align:'center'">职称</th>
								<th data-options="field:'education',width:120,align:'center'">学历</th>
								<th data-options="field:'age',width:60,align:'center'">年龄</th>
								<th
									data-options="field:'used',width:60,align:'center',formatter: function(value,row,index){return setYesAndNo(value)}">是否使用</th>
								<th
									data-options="field:'deleted',width:60,align:'center',formatter: function(value,row,index){return setYesAndNo(value)}">是否删除</th>
								<th data-options="field:'shortname',width:80,align:'center'">简称</th>
								<th data-options="field:'exname',width:120,align:'center'">曾用名</th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="p_pagelist"
					style="background: #efefef; border: 1px solid #ccc; width: 103%; margin-left: -10px; margin-top: 0%"></div>
			</div>
		</div>
	</div>
</body>
</html>