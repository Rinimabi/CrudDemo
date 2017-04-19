var select_tree_rows; // 用来记录点击树的节点ID，以查询部门和人员信息

function deleteDepartment() {
	if (select_tree_rows == null) {
		alert('请在表中选择你要删除的数据');
	} else if (select_tree_rows.isParent == false) {
		deletePerson();
	} else if (select_tree_rows.id == 0) {
		alert('不能删除根节点！');
	} else {
		$().ready(
				function() {
					var tdata = select_tree_rows.id;
					$.ajax({
						type : "post",
						url : "delete_department.spring",
						data : {
							"tdata" : tdata
						},
						// dataType: "text", //由于返回了一个string，所以这个不能设类型
						success : function(data) {
							alert(data);
							var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
							var nodes = treeObj.getSelectedNodes();
							var node = treeObj.getNodeByParam("id",
									nodes[0].frontid, null);
							treeObj.reAsyncChildNodes(node, "refresh");
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(XMLHttpRequest.status);
							alert(XMLHttpRequest.readyState);
							alert(textStatus);
						},
					});
				});
	}
}
function addDepartment() {
	if (select_tree_rows == null) {
		alert('请选择部门所属的上级部门，否则将加入主列表');
		select_tree_rows = {
			"id" : "0"
		};
	}
	$('#tt').tabs('add', {
		title : '新增部门',
		selected : true,
		href : 'AddDepartmentInfo.jsp',
		iconCls : 'icon-add',
		closable : true,
		id : 'InputDepartment'
	});
	$('#tt').tabs('disableTab', '部门人员');
}
function updataDepartment() {
	if (select_tree_rows == null) {
		alert('请选择你要修改的数据');
	}
	// 当部门和人员都显示在树上，需要此来区分
	else if (select_tree_rows.isParent == false) {
		updataPerson();
	} else {
		$('#tt').tabs('add', {
			title : '更新部门',
			selected : true,
			href : 'UpdataDepartmentInfo.jsp',
			iconCls : 'icon-edit',
			closable : true,
			id : 'UpdataDepartment'
		// ...
		});
		$('#tt').tabs('disableTab', '部门人员');
	}
}
function addPerson() {
	if (select_tree_rows == null) {
		alert('请先选择人员要加入的部门');
		return;
	}
	$('#tt').tabs('add', {
		title : '新增人员',
		selected : true,
		href : 'AddPersonInfo.jsp',
		iconCls : 'icon-add',
		closable : true,
		id : 'AddPerson'
	});
	$('#tt').tabs('disableTab', '部门人员');
}
function deletePerson() {
	if (select_tree_rows == null) {
		alert('请选择你要删除的数据');
	}
	$("#datatable").ready(function() {
		var tdata = select_tree_rows.id;
		$.ajax({
			type : "post",
			url : "delete_person.spring",
			data : {
				"tdata" : tdata
			},
			success : function(data) {
				alert(data);
				LoadPersons(person_pagenumber, person_pagesize, person_id);
				window.navigate("RealMain.jsp");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
	});
}
function updataPerson() {
	LoadPersonsByName(person_pagenumber, person_pagesize,
			select_tree_rows.cnname);
	select_tree_rows = person_information;
	if (select_tree_rows == null) {
		alert('请选择你要修改的数据');
	} else {
		$('#tt').tabs('add', {
			title : '更新人员',
			selected : true,
			href : 'UpdataPersonInfo.jsp',
			iconCls : 'icon-edit',
			closable : true,
			id : 'UpdataPerson'
		// ...
		});
		$('#tt').tabs('disableTab', '部门人员');
	}
}
function inputPerson() {
	if (select_tree_rows == null) {
		alert('请先选择部门');
	} else {
		$('#person_window').window('open');
		LoadAllPersonTree(select_tree_rows.id);
	}
}
function exit_inputperson() {
	$('#person_window').window('close');
}
function ok_inputperson() {
	$.ajax({
		type : "post",
		url : "input_person.spring",
		data : {
			"personid" : person_id,
			"personname" : person_name,
			"departmentid" : select_tree_rows.id
		},
		success : function(data) {
			$('#person_window').window('close');
			LoadPersons(person_pagenumber, person_pagesize,
					select_tree_rows.id);
		},
		error : function() {
			alert("添加人员失败！");
		},
	});
}
function removePerson() {
	var rows = $("#personstable").datagrid('getSelected');
	if (rows == null) {
		alert('请先选择要移除的人员！');
	} else {
		$.ajax({
			type : "post",
			url : "remove_person.spring",
			data : {
				"personid" : rows.id,
				"departmentid" : select_tree_rows.id
			},
			success : function(data) {
				LoadPersons(person_pagenumber, person_pagesize,
						select_tree_rows.id);
			},
			error : function() {
				alert("移除人员失败！");
			},
		});
	}
}