
<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<section class="content-header">
	<h1>
		角色列表 <small>开始管理系统角色</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:;"> <i class="fa fa-home"></i> Home
		</a></li>
		<li><a href="#/page/user/list"> <i class="fa fa-gavel"></i>角色列表
		</a></li>
		<li class="active">修改权限</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-xs-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">修改权限</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<span class="input-group-btn">
									<button class="btn btn-primary" type="button" id="getStroreAppButton">获取</button>
									<button class="btn btn-success" type="button" id="chooseMenuButton">搜索</button>
				</span>
				<form role="form" class="role-form" action="/center/role/menu/save">
					<input type="hidden" name="id" value="${role.id}" />
					<input type="hidden" name="checkedInput" id="checkedInput" value="" />
					<div class="box-body">
						
						 
						<#list allMenuTree as menuTree>
							<label>${menuTree.text}</label>
							<div class="form-group">
							<#list menuTree.children as menu>					
								<label> <input type="checkbox" id="${menu.data.id}" name="${menu.data.code}" class="minimal" >${menu.text}</label>
							</#list>
							</div>
						</#list>
						 
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-default"
							onclick="location.href='#/page/center/role/list'">返回列表</button>
						<button type="submit" class="btn btn-primary pull-right">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
	require(
			[ "domReady" ],
			function(domReady) {
				domReady(function() {
					activeMenu("book");
					
					
					$("#chooseMenuButton").on("click", function() {
						$.confirm({
							title : "菜单列表",
							content : "url:" + base + "/center/menu/dialog/tree",
							columnClass : "col-md-10 col-md-offset-1",
							confirmButton : "确定",
							cancelButton : "取消",
							confirmButtonClass : 'btn-primary',
							cancelButtonClass : 'btn-danger',
							confirm : function() {
								var me = this;
								var $orgTree = me.$b.find("#org_tree");
								
								console.log("");
								var ids="";
							      var nodes=$orgTree.jstree("get_checked"); //使用get_checked方法 
							      $.each(nodes, function(i, n) { 
							      ids += $(n)+",";
							      }); 
							      alert(nodes);

								return true;
							}
						});
					});
					
					
					
					var $form = $(".role-form");

					$(".role-form").bootstrapValidator()
							.on('success.form.bv',
									function(e) {
										e.preventDefault();
										var $form = $(e.target);
										var bv = $form
												.data('bootstrapValidator');
										console.log("");
										var noCheckValue = '';
									    $('.role-form :checkbox').each(function(){
									        if($(this).is(':checked')) noCheckValue = noCheckValue + ',' + $(this).attr('id');
									    })
									    $('#checkedInput').val(noCheckValue);
									    
										$.post(
												$form.attr('action'),
												$form.serialize(),
													function(result) {
														if (result.success) {
																	$.alert({
																			title : false,
																			content : "保存成功",
																			confirmButton : "返回列表",
																			confirm : function() {
																				location.href = "#/center/role/list";
																			}
																		});

															} else {
																$form.bootstrapValidator('disableSubmitButtons',false);
															}
														}, 'json');
									});
				});
			});
</script>


