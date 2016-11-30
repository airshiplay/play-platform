<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		***列表 <small>开始管理***</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#page/center/home"><i class="fa fa-home"></i> Home</a></li>
		<li class="active">***管理</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">
			<div class="mailbox-controls">
				<div class="btn-group btn-group-sm">
					<a type="button" class="btn btn-primary" href="#/page/${module}/${'${shortClassName}'?uncap_first}/create">
						<i class="fa fa-plus"></i> 添加
					</a>
					<a aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" class="btn btn-primary btn-sm dropdown-toggle" type="button">
						批量
						<span class="ion-arrow-down-b"></span>
					</a>
					<ul class="dropdown-menu">
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
					<table id="grid-${'${shortClassName}'?lower_case}" class="table table-hover">
						<thead>
							<tr>
								<th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false">ID</th>
								<#list fields as field>
								<th <#if field.fieldType=='Date'>data-column-id="${field.fieldName}" data-type="date" <#else>data-column-id="${field.fieldName}" </#if>data-sortable="false" >${field.fieldName}</th>
								</#list>
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
					activeMenu("${module}_${'${shortClassName}'?lower_case}_list");

					var grid = $("#grid-${'${shortClassName}'?lower_case}")
							.bootgrid(
									{
										url : base + "/${module}/${'${shortClassName}'?uncap_first}/page",
										formatters : {
											"icon" : function(column,row){
												return "<img src=\""+row.icon+"\"  alt=\"图标\" width=\"50\" height=\"50\"/>";
											},
											"commands" : function(column, row) {
												return "\
								<div class=\"btn-group btn-group-sm\" data-row-id=\""+row.id+"\">\
									<a type=\"button\" class=\"btn btn-primary\" href=\"#/page/${module}/${'${shortClassName}'?uncap_first}/view/"+row.id+"\">\
										<i class=\"fa fa-eye\"></i>\
									</a>\
									<a type=\"button\" class=\"btn btn-primary\" href=\"#/page/${module}/${'${shortClassName}'?uncap_first}/edit/"+row.id+"\">\
										<i class=\"glyphicon glyphicon-edit\"></i>\
									</a>\
									<a aria-expanded=\"false\" aria-haspopup=\"true\" data-toggle=\"dropdown\" \
										class=\"btn btn-primary dropdown-toggle\" type=\"button\">\
										<i class=\"fa fa-cog\"></i>\
										<span class=\"ion-arrow-down-b\"></span>\
									</a>\
									<ul class=\"dropdown-menu pull-right\">\
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
								content : "请至少选择一个",
								confirmButton : "关闭"
							});
						}
					}

					var deleteRow = function(grid, ids) {
						$.confirm({
							title : "提示",
							content : "确定删除***?",
							confirmButton : "确定",
							cancelButton : "关闭",
							icon : 'fa fa-warning',
							confirmButtonClass : 'btn-warning',
							animation : 'zoom',
							confirm : function() {
								$.post(base + "/${module}/${'${shortClassName}'?uncap_first}/delete", {
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
					
					grid.on("loaded.rs.jquery.bootgrid", function() {
						var $table = $(this);
						$table.find(".dropdown-toggle").dropdown();

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
					
					$("#refreshButton").on("click", function() {
						refreshGrid();
					});
					$("#searchButton").on("click", function() {
						var arr=new Array();
						var search=new Object();
						search.property="name";
						search.value=$("#searchInput").val();
						arr.push(search);
						grid.bootgrid("search", JSON.stringify(arr));
					});

				});
			});
</script>
