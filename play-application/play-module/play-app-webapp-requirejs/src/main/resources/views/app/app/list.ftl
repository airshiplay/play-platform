<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		应用管理 <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/home"><i class="fa fa-home"></i> Home</a></li>
		<li class="active"><a href="#page/app/app/list"> 应用管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">
			<div class="mailbox-controls">
				<div class="btn-group btn-group-sm">
					<a aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" class="btn btn-primary dropdown-toggle" type="button">
						添加
						<span class="ion-arrow-down-b"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="#/page/app/app/add">
								<i class="glyphicon glyphicon-cloud"></i>添加企业应用
							</a>
						</li>
						<li class="divider" role="separator"></li>
						<li>
							<a href="#/page/app/app/addstore">
								<i class="glyphicon glyphicon-th"></i>添加商店应用
							</a>
						</li>
					</ul>
				</div>
				<div class="btn-group btn-group-sm">
					<a aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" class="btn btn-primary dropdown-toggle" type="button">
						批量
						<span class="ion-arrow-down-b"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:;" id="batchInstallButton">
								<i class="glyphicon glyphicon-send"></i>安装/更新到设备
							</a>
						</li>
						<li class="divider" role="separator"></li>
						<li>
							<a href="javascript:;" id="batchDeleteButton">
								<i class="glyphicon glyphicon-remove"></i>移除
							</a>
						</li>
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
								<th data-column-id="name">名称</th>
								<th data-column-id="platform" data-css-class="hidden-xs" data-header-css-class="hidden-xs">平台</th>
								<th data-column-id="identifier" data-sortable="true" data-css-class="hidden-xs" data-header-css-class="hidden-xs">标识</th>
								<th data-column-id="version" data-sortable="true" data-css-class="hidden-xs" data-header-css-class="hidden-xs">版本</th>
								<th data-formatter="commands" data-css-class="command-column" data-width="160px" data-sortable="false" data-align="center"
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
					activeMenu("app");

					var grid = $("#grid-app")
							.bootgrid(
									{
										url : base + "/app/app/page",
										formatters : {
											"commands" : function(column, row) {
												return "\
								<div class=\"btn-group btn-group-sm\" data-row-id=\""+row.id+"\">\
									<a type=\"button\" class=\"btn btn-primary\" href=\"#/page/app/app/view/"+row.id+"\">\
										<i class=\"fa fa-eye\"></i>\
									</a>\
									<a type=\"button\" class=\"btn btn-primary\" href=\"#/page/app/app/edit/"+row.id+"\">\
										<i class=\"glyphicon glyphicon-edit\"></i>\
									</a>\
									<a aria-expanded=\"false\" aria-haspopup=\"true\" data-toggle=\"dropdown\" \
										class=\"btn btn-primary dropdown-toggle\" type=\"button\">\
										<i class=\"fa fa-cog\"></i>\
										<span class=\"ion-arrow-down-b\"></span>\
									</a>\
									<ul class=\"dropdown-menu pull-right\">\
										<li><a href=\"javascript:;\" class=\"command-installApplication\"><i class=\"glyphicon glyphicon-send\"></i>安装/更新到设备</a></li>\
										<li class=\"divider\" role=\"separator\"></li>\
										<li><a href=\"javascript:;\" class=\"command-delete\"><i class=\"glyphicon glyphicon-remove\"></i>移除</a></li>\
									</ul>\
								</div>";
											}
										}
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
								content : "请至少选择一个应用",
								confirmButton : "关闭"
							});
						}
					}

					var deleteRow = function(grid, ids) {
						$.confirm({
							title : "提示",
							content : "确定删除应用?",
							confirmButton : "确定",
							cancelButton : "关闭",
							icon : 'fa fa-warning',
							confirmButtonClass : 'btn-warning',
							animation : 'zoom',
							confirm : function() {
								$.post(base + "/center/app/delete", {
									ids : ids
								}, function(resp) {
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
							title : "安装应用",
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
									$.post(base + "/mdmcommand/installApplication", {
										appIds : ids.join(),
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

					grid.on("loaded.rs.jquery.bootgrid", function() {
						var $table = $(this);
						$table.find(".dropdown-toggle").dropdown();

						$table.find(".command-installApplication").on("click", function() {
							var app_id = $(this).closest(".btn-group").data("row-id");

							installToDevice($table, [ app_id ]);
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

					$("#refreshButton").on("click", function() {
						refreshGrid();
					});
					$("#searchButton").on("click", function() {
						grid.bootgrid("search", $("#searchInput").val());
					});

				});
			});
</script>