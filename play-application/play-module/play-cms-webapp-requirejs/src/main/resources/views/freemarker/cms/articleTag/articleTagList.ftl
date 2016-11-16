<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		文章标签列表 <small>开始管理文章标签信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li class="active">文章标签管理</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">
			<div class="mailbox-controls">
				<div class="btn-group btn-group-sm">
					<a type="button" class="btn btn-primary" href="#/page/cms/articleTag/create">
						<i class="fa fa-plus"></i> 添加
					</a>
					<a aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" class="btn btn-primary btn-sm dropdown-toggle" type="button">
						批量
						<span class="ion-arrow-down-b"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" id="batchChangeTagButton">
								<i class="glyphicon glyphicon-erase"></i>更改广告位
							</a></li>
						<li class="divider" role="separator"></li>
						<li><a href="javascript:;" id="batchDeleteButton">
								<i class="glyphicon glyphicon-remove"></i>移除
							</a></li>
					</ul>
				</div>
				<!-- /.btn-group -->
				<a type="button" class="btn btn-warning btn-sm" id="refreshButton">
					<i class="fa fa-refresh"></i>
				</a>
				<div class="pull-right">
					<div class="input-group input-group-sm" style="width: 120px;">
						<input type="text" id="searchInput" class="form-control pull-right" placeholder="搜索">
						<div class="input-group-btn">
							<button id="searchButton" type="button" class="btn btn-default">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<!-- /.btn-group -->
				</div>
				<!-- /.pull-right -->
			</div>
			<div class="box box-primary">
				<div class="box-body no-padding">
					<table id="grid-app" class="table table-hover">
						<thead>
							<tr>
								<th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
								<th data-column-id="name" data-sortable="false" >名称</th>
								<th data-column-id="memo" data-sortable="false" >备注</th>
								
								<th data-formatter="commands" data-css-class="command-column" data-width="150px" data-sortable="false" data-align="center"
									data-header-align="center">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>
</section>
<script type="text/javascript">
	require(
			[ "domReady" ],
			function(domReady) {
				domReady(function() {
					activeMenu("cms_articleTag_list");

					var grid = $("#grid-app")
							.bootgrid(
									{
										url : base + "/cms/articleTag/page",
										formatters : {
											"commands" : function(column, row) {
												return "\
								<div class=\"btn-group btn-group-sm\" data-row-id=\""+row.id+"\">\
									<a type=\"button\" class=\"btn btn-primary\" href=\"#/page/area/edit/"+row.id+"\">\
										<i class=\"glyphicon glyphicon-edit\"></i>\
									</a>\
									<a aria-expanded=\"false\" aria-haspopup=\"true\" data-toggle=\"dropdown\" \
										class=\"btn btn-primary dropdown-toggle\" type=\"button\">\
										<i class=\"fa fa-cog\"></i>\
										<span class=\"ion-arrow-down-b\"></span>\
									</a>\
									<ul class=\"dropdown-menu pull-right\">\
										<li><a href=\"javascript:;\" class=\"command-installAppLock\"><i class=\"glyphicon glyphicon-send\"></i>安装/更新到设备</a></li>\
										<li><a href=\"javascript:;\" class=\"command-removeAppLock\"><i class=\"glyphicon glyphicon-erase\"></i>从设备移除</a></li>\
										<li class=\"divider\" role=\"separator\"></li>\
										<li><a href=\"javascript:;\" class=\"command-delete\"><i class=\"glyphicon glyphicon-remove\"></i>移除</a></li>\
									</ul>\
								</div>";
											}
										},
										templates:{search:"<div class=\"{{css.search}}\"><div class=\"input-group\"><span class=\"{{css.icon}} input-group-addon {{css.iconSearch}}\"></span> <input type=\"text\" class=\"{{css.searchField}}\" placeholder=\"11111\" /></div></div>"}
									});

					var refreshGrid = function() {
						grid.bootgrid("reload")
					}

					var actionWithSelectedRows = function(action) {
						var selectedRows = grid.bootgrid("getSelectedRows");
						if (selectedRows && selectedRows.length > 0) {
							action(grid, selectedRows);
						} else {
							$.alert({
								title : false,
								content : "请至少选择一个应用锁定",
								confirmButton : "关闭"
							});
						}
					}

					var deleteRow = function(grid, ids) {
						$.confirm({
							title : "提示",
							content : "确定删除文章标签?",
							confirmButton : "确定",
							cancelButton : "关闭",
							icon : 'fa fa-warning',
							confirmButtonClass : 'btn-warning',
							animation : 'zoom',
							confirm : function() {
								$.post(base + "/cms/articleTag/delete", $.param({
									ids : ids
								},true), function(resp) {
									if (resp.success) {
										$.alert({
											title : false,
											content : "操作成功",
											confirmButton : "关闭"
										});
										grid.bootgrid("reload")
									} else {
										$.alert({
											title : false,
											content : resp.msg,
											confirmButton : "关闭"
										});
									}
								}, "json");
							}
						});
					};
					
					var installToDevice = function(grid, ids) {
						$.confirm({
							title : "推送应用锁定",
							content : "url:" + base + "/center/dialog/deviceList",
							columnClass : "col-md-8 col-md-offset-2",
							confirmButton : "确定",
							cancelButton : "取消",
							confirmButtonClass : 'btn-primary',
							cancelButtonClass : 'btn-danger',
							confirm : function() {
								var me = this;
								var $table = me.$b.find("table");

								var selectedRows = $table.bootgrid("getSelectedRows");
								if (selectedRows && selectedRows.length > 0) {
									$.post(base + "/mdmcommand/installAppLock", {
										appLockIds : ids.join(),
										deviceIds : selectedRows.join()
									}, function(resp) {
										if (resp.success) {
											$.alert({
												title : false,
												content : "操作成功",
												confirmButton : "关闭"
											});
										} else {
											$.alert({
												title : false,
												content : resp.msg,
												confirmButton : "关闭"
											});
										}
									}, "json");

									return true;
								} else {
									$.alert({
										title : false,
										content : "请至少选择一个设备",
										confirmButton : "关闭"
									});
								}

								return false;
							}
						});
					}
					
					var removeToDevice = function(grid, ids) {
						$.confirm({
							title : "移除应用锁定",
							content : "url:" + base + "/center/dialog/deviceList",
							columnClass : "col-md-8 col-md-offset-2",
							confirmButton : "确定",
							cancelButton : "取消",
							confirmButtonClass : 'btn-primary',
							cancelButtonClass : 'btn-danger',
							confirm : function() {
								var me = this;
								var $table = me.$b.find("table");

								var selectedRows = $table.bootgrid("getSelectedRows");
								if (selectedRows && selectedRows.length > 0) {
									$.post(base + "/mdmcommand/removeAppLock", {
										appLockIds : ids.join(),
										ids : selectedRows.join()
									}, function(resp) {
										if (resp.success) {
											$.alert({
												title : false,
												content : "操作成功",
												confirmButton : "关闭"
											});
										} else {
											$.alert({
												title : false,
												content : resp.msg,
												confirmButton : "关闭"
											});
										}
									}, "json");

									return true;
								} else {
									$.alert({
										title : false,
										content : "请至少选择一个设备",
										confirmButton : "关闭"
									});
								}

								return false;
							}
						});
					}

					grid.on("loaded.rs.jquery.bootgrid", function() {
						var $table = $(this);
						$table.find(".dropdown-toggle").dropdown();

						$table.find(".command-installAppLock").on("click", function() {
							var appLock_id = $(this).closest(".btn-group").data("row-id");

							installToDevice($table, [ appLock_id ]);
						});
						
						$table.find(".command-removeAppLock").on("click", function() {
							var appLock_id = $(this).closest(".btn-group").data("row-id");

							removeToDevice($table, [ appLock_id ]);
						});
						
						$table.find(".command-delete").on("click", function(e) {
							var app_id = $(this).closest(".btn-group").data("row-id");

							deleteRow($table, [ app_id ]);
						});
					});

					$("#batchDeleteButton").on("click", function() {
						actionWithSelectedRows(function(grid, selectedRows) {
							deleteRow(grid, selectedRows);
						});
					});
					
					$("#batchInstallButton").on("click", function() {
						actionWithSelectedRows(function(grid, selectedRows) {
							installToDevice(grid, selectedRows);
						});
					});
					
					$("#batchRemoveButton").on("click", function() {
						actionWithSelectedRows(function(grid, selectedRows) {
							removeToDevice(grid, selectedRows);
						});
					});

					$("#refreshButton").on("click", function() {
						refreshGrid();
					});
					$("#searchButton").on("click", function() {
						var arr=new Array();
						var fullName=new Object();
						fullName.property="fullName";
						fullName.value=$("#searchInput").val();
						arr.push(fullName);
						grid.bootgrid("search", JSON.stringify(arr));
					});

				});
			});
</script>
