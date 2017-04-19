//记录分页信息
var person_pagenumber = 1;
var person_pagesize = 10;

var person_id = 0; //记录点击的节点ID，用于分页变更时重新导入数据、删除人员、添加和移除人员
var person_name = ""; //记录点击的节点名称，用于删除人员、添加、移除人员。

var department_information; //记录部门数据，用于基本信息注入、修改信息注入。
var person_information; //记录人员数据，用于基本信息注入、修改信息注入。

function LoadDepartments() {
	$().ready(function() {
		$.ajax({
			type : "post",
			url : "data.spring",
			data : {
				"pageid" : select_tree_rows.id
			},
			dataType : "json",
			success : function(data) {
				department_information = data.list[0];
				add_Info_Department(department_information);
			},
			error : function() {
				alert("载入部门信息失败！");
			},
		});
	});
}
function LoadDepartmentsByName(department_name) {
	$().ready(function() {
		$.ajax({
			type : "post",
			url : "data_byname.spring",
			data : {
				"searchname" : department_name
			},
			dataType : "json",
			success : function(data) {
				if (data.list.length <= 0) {
					alert("搜索结果为空！");
				} else {
					department_information = data.list[0];
					InitTreeOnly();
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					$().ready(function() {
						var newNodes = treeObj.addNodes(null, data.list);
						treeObj.expandAll(true);
					});
				}
			},
			error : function() {
				alert("搜索结果为空！");
			},
		});
	});
}
function LoadPersons(person_pagenumber, person_pagesize, person_id) {
	$("#personstable").ready(function() {
		$.ajax({
			type : "post",
			url : "persons.spring",
			data : {
				"pagenumber" : person_pagenumber,
				"pagesize" : person_pagesize,
				"pageid" : person_id
			},
			dataType : "json",
			success : function(data) {
				person_information = data.list[0];
				$("#personstable").datagrid("loadData", data.list);
				if (department_information == null && data.list[0] != null) {
					add_Info_Person(data.list[0]);
				}
				person_LoadPage(data.count);
			},
			error : function() {
				alert("无法载入人员资料！");
			},
		});
	});
}
function LoadPersonsByName(person_pagenumber, person_pagesize, person_name) {
	$("#personstable").ready(function() {
		$.ajax({
			type : "post",
			url : "persons_byname.spring",
			data : {
				"pagenumber" : person_pagenumber,
				"pagesize" : person_pagesize,
				"searchname" : person_name
			}, //要发送给服务端的数据
			dataType : "json",
			success : function(data) {
				$("#personstable").datagrid("loadData", data.list);
				person_information = data.list[0];
				person_LoadPage(data.count);
			},
			error : function() {
				alert("没有搜索到相关人员！");
			},
		});
	});
}

function person_LoadPage(person_count) {
	$('#p_pagelist').pagination({
		total : person_count,
		pageSize : person_pagesize,
		layout : [ 'list', 'first', 'links', 'last', 'manual' ],
		displayMsg : "",
		onChangePageSize : function(pageSize) {
			person_pagesize = pageSize;
			$('#p_pagelist').pagination({
				total : person_count,
				pageSize : person_pagesize
			});
		},
		onSelectPage : function(pageNumber, pageSize) {
			person_pagenumber = pageNumber;
			person_pagesize = pageSize;
			$(this).pagination('loading');
			LoadPersons(person_pagenumber, person_pagesize, person_id);
			$(this).pagination('loaded');
		}
	});
}

function getFrontIdToForm(event, treeId, treeNode) {
	//新增时点击树节点可以改变上级ID
	$('#ff').form('load', {
		Frontid : treeNode.id
	});
	person_id = treeNode.id;
	person_pagenumber = 1;
	LoadDepartments();
	LoadPersons(person_pagenumber, person_pagesize, treeNode.id);
	select_tree_rows = treeNode;
}

function setID() {
	$().ready(function() {
		$.ajax({
			type : "post",
			url : "randomid.spring",
			data : {},
			dataType : "json",
			success : function(data) {
				$('#ff').form('load', {
					Id : data,
					Frontid : select_tree_rows.id
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("获取ID失败！");
			},
		});
	});
}
function setDepartmentUpdataInfo(select_tree_rows) {
	document.getElementById("id").value = select_tree_rows.id;
	document.getElementById("frontid").value = select_tree_rows.frontid;
	document.getElementById("cnname").value = select_tree_rows.cnname;
	document.getElementById("office").value = select_tree_rows.office;
	document.getElementById("used").value = select_tree_rows.used;
	document.getElementById("deleted").value = select_tree_rows.deleted;
	document.getElementById("shortname").value = select_tree_rows.shortname;
	document.getElementById("exname").value = select_tree_rows.exname;
}
function setPersonUpdataInfo(select_tree_rows) {
	document.getElementById("id").value = select_tree_rows.id;
	document.getElementById("frontid").value = select_tree_rows.frontid;
	document.getElementById("loginname").value = select_tree_rows.loginname;
	document.getElementById("cnname").value = select_tree_rows.cnname;
	document.getElementById("sex").value = select_tree_rows.sex;
	document.getElementById("duty").value = select_tree_rows.duty;
	document.getElementById("dutyname").value = select_tree_rows.dutyname;
	document.getElementById("education").value = select_tree_rows.education;
	document.getElementById("age").value = select_tree_rows.age;
	document.getElementById("used").value = select_tree_rows.used;
	document.getElementById("deleted").value = select_tree_rows.deleted;
	document.getElementById("shortname").value = select_tree_rows.shortname;
	document.getElementById("exname").value = select_tree_rows.exname;
}
//载入tree
function LoadTree(isSelectPerson) {
	var setting = {
		check : {
			enable : true,
			nocheckInherit : true
		},
		async : {
			enable : true,
			url : "tree.spring",
			dataType : "json",
			autoParam : [ "id=treenode", "isSelectPerson" ],
			type : "post",
			dataFilter : filter
		},
		callback : {
			onClick : getFrontIdToForm
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "frontid",
				rootPId : 0
			},
			key : {
				name : "cnname"
			}
		}
	};

	var zNodes = [];
	$("#treeDemo").ready(function() {
		$.ajax({
			type : "post",
			url : "tree.spring",
			data : {
				"isSelectPerson" : isSelectPerson,
				"treenode" : "0"
			},
			dataType : "json",
			success : function(data) {
				$.fn.zTree.init($("#treeDemo"), setting, data.list);
				add_Info_Department(data.list[0]);
				Search_Department();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
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
function InitTreeOnly() {
	var setting = {
		check : {
			enable : true,
			nocheckInherit : true
		},
		callback : {
			onClick : getFrontIdToForm
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "frontid",
				rootPId : 0
			},
			key : {
				name : "cnname"
			}
		}
	};

	var zNodes = [];
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}
function Search_Department() {
	$('#search_person_tree').searchbox({
		searcher : function(value, name) {
			LoadDepartmentsByName(value);
		},
		prompt : '按名称查询'
	});
	$('#search_person_input')
			.searchbox(
					{
						searcher : function(value, name) {
							var item = $('#personstable').datagrid('getRows');
							if (item) {
								for (var i = item.length - 1; i >= 0; i--) {
									var index = $('#personstable').datagrid(
											'getRowIndex', item[i]);
									if (index.cnname != value) {
										$('#personstable').datagrid(
												'deleteRow', index);
									}
								}
							}
							LoadPersonsByName(person_pagenumber,
									person_pagesize, value);
						},
						prompt : '按名称查询'
					});
}
function setYesAndNo(value) {
	if (value == 1) {
		return "是";
	} else {
		return "否";
	}
}
function add_Info_Department(value) {
	var tb = document.getElementById("infotable").rows.length;
	for (i = 1; i < tb; i++) {
		document.getElementById("infotable").deleteRow(i);
		tb = tb - 1;
		i = i - 1;
	}
	addTableRow("部门ID", value.id, "table_info");
	addTableRow("部门名称", value.cnname, "table_info1");
	if (value.office == null) {
		value.office = "";
	}
	addTableRow("部门是否为委办局", setYesAndNo(value.office), "table_info");
	if (value.used == null) {
		value.used = "";
	}
	addTableRow("部门是否使用", setYesAndNo(value.used), "table_info1");
	if (value.deleted == null) {
		value.deleted = "";
	}
	addTableRow("部门是否删除", setYesAndNo(value.deleted), "table_info");
	if (value.shortname == null) {
		value.shortname = "";
	}
	addTableRow("部门简称", value.shortname, "table_info1");
	if (value.exname == null) {
		value.exname = "";
	}
	addTableRow("部门曾用名", value.exname, "table_info");
}
function add_Info_Person(value) {
	var tb = document.getElementById("infotable").rows.length;
	for (i = 1; i < tb; i++) {
		document.getElementById("infotable").deleteRow(i);
		tb = tb - 1;
		i = i - 1;
	}
	addTableRow("人员ID", value.id, "table_info");
	addTableRow("登录名称", value.loginname, "table_info1");
	addTableRow("中文名称", value.cnname, "table_info");
	addTableRow("性别", value.sex, "table_info1");
	if (value.duty == null) {
		value.duty = "";
	}
	addTableRow("职位", value.duty, "table_info");
	if (value.dutyname == null) {
		value.dutyname = "";
	}
	addTableRow("职称", value.dutyname, "table_info1");
	if (value.education == null) {
		value.education = "";
	}
	addTableRow("学历", value.education, "table_info");
	if (value.age == null) {
		value.age = "";
	}
	addTableRow("年龄", value.age, "table_info1");
	if (value.used == null) {
		value.used = "";
	}
	addTableRow("是否使用", setYesAndNo(value.used), "table_info");
	if (value.deleted == null) {
		value.deleted = "";
	}
	addTableRow("是否删除", setYesAndNo(value.deleted), "table_info1");
	if (value.shortname == null) {
		value.shortname = "";
	}
	addTableRow("人员简称", value.shortname, "table_info");
	if (value.exname == null) {
		value.exname = "";
	}
	addTableRow("人员曾用名", value.exname, "table_info1");
}

function addTableRow(name, value, cls) {
	var tb = document.getElementById("infotable")
	var tr1 = tb.insertRow(tb.rows.length);
	index = tb.rows.length;
	var td1 = tr1.insertCell();
	var td2 = tr1.insertCell();
	td1.setAttribute("class", cls);
	td2.setAttribute("class", cls);
	td1.innerHTML = "<td>" + name + "</td>";
	td2.innerHTML = "<td>" + value + "</td>";
}

function getIdToPersonTree(event, treeId, treeNode) {
	person_id = treeNode.id;
	person_name = treeNode.cnname;
}

function LoadAllPersonTree(isSelectPerson) {
	var setting = {
		check : {
			enable : true,
			nocheckInherit : true
		},
		callback : {
			onClick : getIdToPersonTree
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "frontid",
				rootPId : 0
			},
			key : {
				name : "cnname"
			}
		}
	};

	var zNodes = [];
	$("#persontree").ready(function() {
		$.ajax({
			type : "post",
			url : "tree.spring",
			data : {
				"isSelectPerson" : "SeachPerson"
			}, //要发送给服务端的数据
			dataType : "json",
			success : function(data) {
				$.fn.zTree.init($("#persontree"), setting, data.list);
			},
			error : function() {
				alert("无法获取人员列表！");
			},
		});
	});
}