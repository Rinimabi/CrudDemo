function add_Department_Panel() {
	$('#p2').panel({
		width : 500,
		height : 150,
		title : 'My Panel',
		cls : 'easyui-panel',
		tools : [ {
			iconCls : 'icon-add',
			handler : function() {
				alert('new')
			}
		} ]
	}); 
}