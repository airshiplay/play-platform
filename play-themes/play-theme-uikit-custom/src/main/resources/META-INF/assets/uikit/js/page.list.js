(function($) {
	"use strict";
	var namespace = ".rs.jquery.pagelist";

	function init() {
		this.element.trigger("initialize" + namespace);

		this.$table = this.element.find("#" + this.options.tableId);
		this.$searchForm = this.element.find("#" + this.options.searchFormId);
		this.$batchDeleteButton = this.element.find("#" + this.options.batchDeleteButtonId);
		this.$searchButton = this.element.find("#" + this.options.searchButtonId);
		this.$refreshButton = this.element.find("#" + this.options.refreshButtonId);

		initTable.call(this);
		initButtonBar.call(this);
		initSearchForm.call(this);

		this.element.trigger("initialized" + namespace);
	}

	function initButtonBar() {
		var that = this;
		if (this.$batchDeleteButton) {
			this.$batchDeleteButton.on("click", function() {
				var selectedRows = that.$table.bootgrid("getSelectedRows");
				if (selectedRows && selectedRows.length > 0) {
					UIkit.modal.confirm("确定要删除吗？").then(function() {
						$.post(that.options.baseUrl + "/delete", {
							ids : selectedRows
						}, function(resp) {
							if (resp.success) {
								UIkit.notification("操作成功");
								that.$table.bootgrid("reload");
							} else {
								UIkit.notification(resp.msg);
							}
						}, "json");
					});
				} else {
					UIkit.notification("请至少选择一条记录");
				}
			});
		}
		if (this.$refreshButton) {
			this.$refreshButton.on("click", function() {
				that.$table.bootgrid("reload");
			});
		}
	}

	function initTable() {
		var that = this;

		var formatters = that.options.formatters;
		if (that.options.showOperatingColumn) {
			if (!formatters) {
				formatters = {};
			}
			formatters["commands"] = function(column, row) {
				var content = "";
				if (that.options.rowEditable) {
					content += "<li><a uk-icon=\"icon: file-edit\" href=\"" + that.options.baseUrl + "/edit/" + row.id + "\"></a></li>";
				}
				if (that.options.rowDeleteable) {
					content += "<li><a uk-icon=\"icon: trash\" class=\"command-delete\" href=\"javascript:;\"></a></li>";
				}
				return "<ul data-row-id=\"" + row.id + "\" class=\"uk-iconnav\">" + content + "</ul>";
			};
		}

		var tableOptions = $.extend(true, {
			url : that.options.baseUrl + "/page",
			post : function() {
				if (that.$searchForm) {
					var property = that.$searchForm.find(".uk-select").val();
					if (property && property != "") {
						var searchValue = that.$searchForm.find(".uk-search-input").val();
						if (searchValue && searchValue != "") {
							var ret = {};
							ret[property] = searchValue;
							return ret;
						}
					}
				}
				return null;
			},
			formatters : formatters
		}, that.options.tableOptions);
		that.$table.bootgrid(tableOptions);
		that.$table.on("loaded.rs.jquery.bootgrid", function() {
			if (that.options.rowDeleteable) {
				that.$table.find(".command-delete").on("click", function(e) {
					var entity_id = $(this).closest("ul").data("row-id");
					UIkit.modal.confirm("确定要删除吗？").then(function() {
						$.post(that.options.baseUrl + "/delete", {
							ids : [ entity_id ]
						}, function(resp) {
							if (resp.success) {
								UIkit.notification("操作成功");
								that.$table.bootgrid("reload");
							} else {
								UIkit.notification(resp.msg);
							}
						}, "json");
					});
				});
			}
		});
	}

	function initSearchForm() {
		var that = this;
		if (this.$searchForm) {
			this.$searchForm.on("submit", function() {
				that.$table.bootgrid("reload");
				return false;
			});
		}
		if (this.$searchButton) {
			this.$searchButton.on("click", function() {
				that.$table.bootgrid("reload");
			});
		}
	}

	var PageList = function(element, options) {
		this.element = $(element);
		this.options = $.extend(true, {}, PageList.defaults, this.element.data(), options);
	};

	PageList.defaults = {
		tableId : "main_table",
		tableOptions : {},
		showOperatingColumn : true,
		rowDeleteable : true,
		rowEditable : true,
		rowViewable : false,
		searchFormId : "search-form",
		searchButtonId : "search-button",
		refreshButtonId : "refresh-button",
		batchDeleteButtonId : "delete-button"
	};

	$.fn.pagelist = function(option) {
		var args = Array.prototype.slice.call(arguments, 1), returnValue = null, elements = this.each(function(index) {
			var $this = $(this), instance = $this.data(namespace), options = typeof option === "object" && option;

			if (!instance && option === "destroy") {
				return;
			}
			if (!instance) {
				$this.data(namespace, (instance = new PageList(this, options)));
				init.call(instance);
			}
			if (typeof option === "string") {
				if (option.indexOf("get") === 0 && index === 0) {
					returnValue = instance[option].apply(instance, args);
				} else if (option.indexOf("get") !== 0) {
					return instance[option].apply(instance, args);
				}
			}
		});
		return (typeof option === "string" && option.indexOf("get") === 0) ? returnValue : elements;
	};

})(window.jQuery);
