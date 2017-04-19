<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
<title>Loading</title>
</head>
<body>
<script type="text/javascript">
function LoadTree(){
	var setting = {
			async: {
				enable:true,
				url : "tree.spring",
				//contentType: "application/json",
				dataType: "json",
				autoParam : [ "id=treenode","isSelectPerson"],
				type:"post",
				dataFilter : filter
			},
			check: {
				enable: true,
				nocheckInherit: true
			},
			callback: {
				onAsyncSuccess :zTreeOnAsyncSuccess,
				beforeClick : beforeClick,
				onAsyncError: zTreeOnAsyncError
			},
			data: {
				simpleData: {
					enable: true,
		            idKey: "id",
		            pIdKey: "frontid",
		            rootPId: 0
				},
			    key: {
				    name: "cnname"
			    }
			}
		};
		var zNodes =[
		 		];
		$("#treeDemo").ready(function(){
			var dasd;
			 $.ajax({
				 type: "post",
				 url: "tree.spring",
	             data: {
	            	 "isSelectPerson":dasd,
	            	 "treenode":"0"
	             },
	             dataType: "json",
	             success: function(data){
	            	 $.fn.zTree.init($("#treeDemo"), setting, data.list);
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown) {
                     alert(XMLHttpRequest.status);
                     alert(XMLHttpRequest.readyState);
                     alert(textStatus);
                 },
			 });
		});
}
function filter(treeId, parentNode, childNodes) {
    if (!childNodes)
        return null;
    childNodes = eval(childNodes.list);
    return childNodes;
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
	alert('s');
}
function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown){
	alert('f');
}
function beforeClick(treeId, treeNode) {
    if (!treeNode.isParent) {
        alert("请选择父节点");
        return false;
    } else {
        return true;
    }
}
window.onload = LoadTree();
</script>
 <div>
	<ul id="treeDemo" class="ztree" style="height:auto;"></ul>
</div>

</body>
</html>