<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" href="css/demo.css" type="text/css">
<link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="css/icon.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/easyui.demo.css">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/toolbar.js"></script>
<script type="text/javascript" src="js/loaddata.js"></script>

<title>CRUD展示</title>
</head>
<body class="easyui-layout">
	<!-- 表格 -->
	<div class="easyui-panel" title="填写信息"
		style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form accept-charset="utf-8" onsubmit="document.charset='utf-8';"
			id="ff" method="post">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Id" style="width: 100%" id="id"
					data-options="label:'Id:',required:true" readonly="readonly">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="FrontID" style="width: 100%"
					id="frontid" data-options="label:'上级ID:',required:true"
					readonly="readonly">
				<p style="color: red;">请点击左边树状关系表获取上级ID和上级名称。</p>
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Loginname" style="width: 100%"
					data-options="label:'登录名称:',required:true" id="loginname">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="CnName" style="width: 100%"
					data-options="label:'中文名称:',required:true" id="cnname">
			</div>
			<div style="margin-bottom: 20px">
				<select id="sex" class="easyui-combobox" name="Sex"
					style="width: 100%;" data-options="label:'性别:',required:false">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Duty" style="width: 100%"
					data-options="label:'职位:',required:false" id="duty">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Dutyname" style="width: 100%"
					data-options="label:'职称:',required:false" id="dutyname">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Education" style="width: 100%"
					data-options="label:'学历:',required:false" id="education">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Age" style="width: 100%"
					data-options="label:'年龄:',required:false" value="0" id="age">
			</div>
			<div style="margin-bottom: 20px">
				<select id="used" class="easyui-combobox" name="Used"
					style="width: 100%;" data-options="label:'是否使用:',required:false">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
			<div style="margin-bottom: 20px">
				<select id="deleted" class="easyui-combobox" name="Deleted"
					style="width: 100%;" data-options="label:'是否删除:',required:false">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Shortname" style="width: 100%"
					data-options="label:'简称:',required:false" value="" id="shortname">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Exname" style="width: 100%"
					data-options="label:'曾用名:',required:false" value="" id="exname">
			</div>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()" style="width: 80px">提交</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="cancelForm()" style="width: 80px">取消</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm()" style="width: 80px">清除</a>
			</div>
		</form>
	</div>
	<script>
		function submitForm() {
			$('#ff').form('submit', {
				url : "add_person.spring",
				onSubmit : function() {
					if (document.getElementById("id").value == "") {
						alert('ID不能为空！');
						return false;
					} else if (document.getElementById("frontid").value == "") {
						alert('上级ID不能为空！');
						return false;
					} else {
						return true;
					}
				}
			});
			$('#tt').tabs('close', '新增人员');
			$('#tt').tabs('enableTab', '部门人员');
			$('#tt').tabs('select', '部门人员');
			LoadPersons(person_pagenumber, person_pagesize, select_tree_rows.id);
		}
		function cancelForm() {
			$('#tt').tabs('close', '新增人员');
			$('#tt').tabs('enableTab', '部门人员');
			$('#tt').tabs('select', '部门人员');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
		window.onload = setID();
	</script>
</body>
</html>