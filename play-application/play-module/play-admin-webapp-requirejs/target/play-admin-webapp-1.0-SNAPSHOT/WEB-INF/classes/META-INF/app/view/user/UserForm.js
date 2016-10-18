Ext.define("app.view.user.UserForm", {
	extend : "Ext.form.Panel",
	alias : "widget.userform",
	requires : [ "app.view.user.UserController", "app.view.user.UserModel" ],
	controller : "user",
	viewModel : "user",
	url : Ext.ctx + "/user/save",
	bodyPadding : 5,
	border : false,
	frame : false,
	scrollable : true,
	layout : "anchor",
	defaults : {
		anchor : "90%"
	},
	fieldDefaults : {
		labelAlign : "right",
		labelWidth : 120
	},
	defaultType : "textfield",
	items : [ {
		xtype : "hiddenfield",
		name : "id"
	}, {
		fieldLabel : "账号",
		name : "username",
		allowBlank : false
	}, {
		fieldLabel : "姓名",
		name : "name",
		allowBlank : false
	}, {
		fieldLabel : "邮箱",
		name : "email",
		vtype : "email"
	}, {
		fieldLabel : "手机",
		name : "mobile",
		regex : /^((\d{3,4}-)*\d{7,8}(-\d{3,4})*|13\d{9})$/
	}, {
		xtype : "checkbox",
		fieldLabel : "是否锁定",
		name : "locked"
	}, {
		xtype : "checkbox",
		fieldLabel : "是否启用",
		name : "enabled"
	}, {
		xtype : "checkbox",
		fieldLabel : "账号过期",
		name : "accountExpired"
	}, {
		xtype : "checkbox",
		fieldLabel : "密码过期",
		name : "credentialsExpired"
	} ],
	buttonAlign : "left",
	buttons : [ {
		text : "保存",
		formBind : true,
		handler : "onFormSave"
	} ],
	listeners : {
		afterRender : function(form, opts) {
			var record = form.getRecord();
			if (record == null || record.get("id") == null) {
				form.insert(2, {
					fieldLabel : "密码",
					name : "newPassword",
					inputType : "password",
					allowBlank : false
				});
			}
		}
	}
});