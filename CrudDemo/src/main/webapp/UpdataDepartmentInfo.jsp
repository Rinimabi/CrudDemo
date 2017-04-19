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
	<div class="easyui-panel" title="修改信息"
		style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form accept-charset="utf-8" onsubmit="document.charset='utf-8';"
			id="ff" method="post">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Id" style="width: 100%" id="id"
					data-options="label:'Id:',required:true" readonly="readonly">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Frontid" style="width: 100%"
					id="frontid" data-options="label:'上级ID:',required:true"
					readonly="readonly">
				<p style="color: red;">注意：请点击树节点获取上级ID</p>
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="Cnname" style="width: 100%"
					data-options="label:'中文名称:',required:true" id="cnname">
			</div>
			<div style="margin-bottom: 20px">
				<select id="office" class="easyui-combobox" name="Office"
					style="width: 100%;" data-options="label:'是否为委办局:',required:false">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
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
					<option value="1">是</option>
					<option value="0">否</option>
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
				url : "updata_data.spring",
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
			$('#tt').tabs('close', '更新部门');
			$('#tt').tabs('enableTab', '部门人员');
			$('#tt').tabs('select', '基本资料');
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getSelectedNodes();
			var node = treeObj.getNodeByParam("id", nodes[0].frontid, null);
			treeObj.reAsyncChildNodes(node, "refresh");
		}
		function cancelForm() {
			$('#tt').tabs('close', '更新部门');
			$('#tt').tabs('enableTab', '部门人员');
			$('#tt').tabs('select', '基本资料');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
		window.onload = setDepartmentUpdataInfo(department_information);
	</script>
</body>
</html>